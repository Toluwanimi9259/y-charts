package com.techafresh.ychartstest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.ui.barchart.models.BarData
import com.techafresh.ychartstest.ui.theme.YchartsTestTheme
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YchartsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        
                        Button(onClick = { startActivity(Intent(this@MainActivity, BarChartActivity::class.java)) }) {
                            Text(text = "Bar Chart")
                        }

                        Button(onClick = { startActivity(Intent(this@MainActivity, PieChartActivity::class.java)) }) {
                            Text(text = "Pie Chart")
                        }

                        Button(onClick = { startActivity(Intent(this@MainActivity, DonutChartActivity::class.java)) }) {
                            Text(text = "Donut Chart")
                        }

                        Button(onClick = { startActivity(Intent(this@MainActivity, LineChartActivity::class.java)) }) {
                            Text(text = "Line Chart")
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
    YchartsTestTheme {
        Greeting("Android")
    }
}
