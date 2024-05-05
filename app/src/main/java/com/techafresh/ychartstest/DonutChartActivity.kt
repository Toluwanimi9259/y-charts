package com.techafresh.ychartstest

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import co.yml.charts.ui.piechart.utils.proportion
import com.techafresh.timbertrack.TimberTrack
import com.techafresh.ychartstest.ui.theme.YchartsTestTheme

class DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YchartsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    LazyColumn(content = {
                        items(1) { item ->
                            when (item) {
                                0 -> {
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "Donut Chart showing the market share of major brands of laptops in the world",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
//                                            .padding(it)
                                            .fillMaxWidth()
                                    ) {
                                        Spacer(modifier = Modifier.height(20.dp))
                                        SimpleDonutChart(context)
                                    }
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview(showBackground = true)
private fun SimpleDonutChart(
    context: Context
) {

    val data = PieChartData(
        slices = listOf(
            PieChartData.Slice("Dell", 15f, generateRandomColor()),
            PieChartData.Slice("Acer", 6.4f, generateRandomColor()),
            PieChartData.Slice("HP", 19.8f, generateRandomColor()),
            PieChartData.Slice("Lenovo", 23.5f, generateRandomColor()),
            PieChartData.Slice("Apple", 10.6f, generateRandomColor()),
            PieChartData.Slice("Asus", 7.1f, generateRandomColor()),
            PieChartData.Slice("Others", 17.6f, generateRandomColor())
        ),
        plotType = PlotType.Donut
    )
    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    val proportions = data.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            strokeWidth = 120f,
            labelColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            labelType = PieChartConfig.LabelType.PERCENTAGE,
            isAnimationEnable = true,
            chartPadding = 25,
            labelFontSize = 42.sp,
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 4))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            data,
            pieChartConfig
        ) { slice ->
            TimberTrack.log(message = slice.label + " ${slice.value}%", showToast = true, context = context, toastLength = Toast.LENGTH_SHORT)
        }
    }
}
