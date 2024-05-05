package com.techafresh.ychartstest

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.techafresh.timbertrack.TimberTrack
import com.techafresh.ychartstest.ui.theme.YchartsTestTheme

class PieChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YchartsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val ctx = LocalContext.current
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
//                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(1) { item ->
                                when (item) {
                                    0 -> {
                                        Text(
                                            modifier=Modifier.padding(12.dp),
                                            text = "Population of countries in the European Union in 2021",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        CustomPieChart(ctx)
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
private fun CustomPieChart(context: Context) {
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Germany", 18.6f, generateRandomColor()),
            PieChartData.Slice("France", 15.2f, generateRandomColor()),
            PieChartData.Slice("Italy", 13.2f, generateRandomColor()),
            PieChartData.Slice("Spain", 10.6f, generateRandomColor()),
            PieChartData.Slice("Poland", 8.4f, generateRandomColor()),
            PieChartData.Slice("Romania", 4.3f, generateRandomColor()),
            PieChartData.Slice("Netherlands", 3.9f, generateRandomColor()),
            PieChartData.Slice("Rest of the EU", 25.7f, generateRandomColor())

        ),
        plotType = PlotType.Pie
    )
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            isAnimationEnable = true,
            chartPadding = 30,
            labelFontSize = 30.sp,
            backgroundColor = Color.White,
            showSliceLabels = true,
            sliceLabelTypeface = Typeface.defaultFromStyle(Typeface.ITALIC),
            animationDuration = 1500
        )
    Column(modifier = Modifier.height(500.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 2))
        PieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            pieChartData,
            pieChartConfig
        ) { slice ->
            TimberTrack.log(message = slice.label + " ${slice.value}%", showToast = true, context = context, toastLength = Toast.LENGTH_SHORT)
        }
    }
}




