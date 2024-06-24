package com.jfranco.catan.companion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jfranco.catan.companion.ui.theme.CatanCompanionTmpTheme
import kotlin.time.measureTimedValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = measureTimedValue {
            val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, this, "test.db")
            val database = Database(driver)
            val playerQueries: PlayerQueries = database.playerQueries
            playerQueries.selectAll().executeAsList()
        }

        enableEdgeToEdge()
        setContent {
            CatanCompanionTmpTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {},
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        LazyColumn {
                            item {
                                Greeting(
                                    name = "Duration: ${list.duration.inWholeMilliseconds}",
                                )
                            }

                            items(list.value) { player ->
                                Greeting(
                                    name = player.name,
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatanCompanionTmpTheme {
        Greeting("Android")
    }
}