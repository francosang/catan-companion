package com.jfranco.catan.companion.sessions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.jfranco.catan.companion.Session
import com.jfranco.catan.companion.state.Result
import kotlinx.coroutines.launch

@Composable
fun Item(session: Session) {
    Card {
        ListItem(
            headlineContent = { Text("Session ${session.id}") },
            supportingContent = { Text(session.created) }
        )
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    positiveText: String,
    negativeText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(positiveText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(negativeText)
            }
        }
    )
}

@Composable
fun BoxScope.Sessions(state: SessionsScreenState, action: (Action) -> Unit) {
    if (state.openDialog) AlertDialogExample(
        onDismissRequest = { action(Action.Cancel) },
        onConfirmation = { action(Action.Confirm) },
        dialogTitle = "Create new session?",
        positiveText = "Yes",
        negativeText = "No"
    )

    if (state.loading) CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center)
    )
    else LazyColumn {
        items(state.sessions) { player ->
            Item(player)
        }
    }

}

class SessionsScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SessionsScreenModel>()
        val resultState by screenModel.state.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Sessions")
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                screenModel.action(Action.NewSession)
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Create new session"
                                )
                            }
                        )
                    }
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                val state = resultState.let {
                    when (it) {
                        // TODO: move the error handling to a reusable generic component
                        is Result.Err -> {
                            val scope = rememberCoroutineScope()

                            scope.launch {
                                snackbarHostState.showSnackbar("Error: ${it.uuid}")
                            }

                            it.previous
                        }

                        is Result.Ok -> it.value
                    }
                }

                Sessions(
                    state = state,
                    action = screenModel::action
                )
            }
        }
    }
}