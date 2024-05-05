package com.techafresh.ychartstest

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.components.Legends
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.StackedBarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.GroupBarChartData
import co.yml.charts.ui.barchart.models.GroupSeparatorConfig
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import com.techafresh.timbertrack.TimberTrack
import com.techafresh.ychartstest.ui.theme.YchartsTestTheme
import kotlin.random.Random

class BarChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YchartsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(2) { item ->
                                when (item) {
                                    0 ->{   Text(
                                        modifier=Modifier.padding(12.dp),
                                        text = "Bar chart showing the the PH of common household substances",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                        CustomVerticalBarChart()
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                    1 -> { Text(
                                        modifier=Modifier.padding(12.dp),
                                        text = "Horizontal Bar Chart showing the favourite pets of students at an elementary school",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                        CustomHorizontalBarChart()
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

@Preview(showBackground = true, device = "id:pixel_5",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CustomVerticalBarChart(){
    val maxRange = 14 // 600 Students
//    TimberTrack.log(tag = "Raw BarData", message = barData.toString())
//    TimberTrack.log(tag = "Raw BarData Item 1", message = barData[1].toString())

    val yStepSize = 14

    // To make a horizontal bar chart switch the x values with the y values
    val barData2 = listOf(
        BarData(point = Point(1f, 2.2f), label = "Lemon Juice", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(2f, 2.8f), label = "Vinegar", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(3f, 3.0f), label = "Apples", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(4f, 4.0f), label = "Wine", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(5f, 4.5f), label = "Tomatoes", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(6f, 6.4f), label = "Saliva", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(7f, 6.5f), label = "Rain Water", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(8f, 6.8f), label = "Milk", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(9f, 7.4f), label = "Blood", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(10f, 7.8f), label = "Egg", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
        BarData(point = Point(11f, 7.4f), label = "Tears", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData2.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(40.dp)
        .labelData { index -> barData2[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

//    val legendsConfig = LegendsConfig(
//        legendLabelList = getLegendDataFromBarChart(barData2),
//        gridColumnCount = 3,
//        legendsArrangement = Arrangement.Start,
//        textStyle = TextStyle()
//    )

    val barChartData = BarChartData(
        chartData = barData2,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 40.dp,
//            isGradientEnabled = true,
            barWidth = 35.dp,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Red,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { x, y -> " PH = $y " }
            )
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
    )

    Column(
        Modifier.height(580.dp)
    ) {
        BarChart(modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(), barChartData = barChartData)

        Spacer(modifier = Modifier.height(10.dp))

//        Legends(
//            legendsConfig = legendsConfig
//        )
    }
}


@Preview(showBackground = true)
@Composable
fun CustomHorizontalBarChart(){
    val maxRange = 600 // 600 Students
//    TimberTrack.log(tag = "Raw BarData", message = barData.toString())
//    TimberTrack.log(tag = "Raw BarData Item 1", message = barData[1].toString())

    val xStepSize = 5
    val barData2 = listOf(
        BarData(point = Point(25f, 1f), label = "Lizard", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(250f, 2f), label = "Dog", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(115f, 3f), label = "Cat", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(50f, 4f), label = "Bird", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(30f, 5f), label = "Guinea Pig", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(45f, 6f), label = "Hamster", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(75f, 7f), label = "Fish", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
        BarData(point = Point(10f, 8f), label = "Lizard", color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)), dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)),
    )

    val xAxisData = AxisData.Builder()
        .steps(xStepSize)
        .bottomPadding(12.dp)
        .endPadding(40.dp)
        .labelData { index -> (index * (maxRange / xStepSize)).toString() }
        .build()

    val yAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData2.size - 1)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .setDataCategoryOptions(
            DataCategoryOptions(
                isDataCategoryInYAxis = true,
                isDataCategoryStartFromBottom = false
            )
        )
        .startDrawPadding(48.dp)
        .labelData { index -> barData2[index].label }
        .build()


//    val legendsConfig = LegendsConfig(
//        legendLabelList = getLegendDataFromBarChart(barData2),
//        gridColumnCount = 3,
//        legendsArrangement = Arrangement.Start,
//        textStyle = TextStyle()
//    )

    val barChartData = BarChartData(
        chartData = barData2,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Red,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { x, y -> " Value : $x " },
                barChartType = BarChartType.HORIZONTAL
            )
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
        barChartType = BarChartType.HORIZONTAL
    )

    Column(
        Modifier.height(650.dp)
    ) {
        BarChart(modifier = Modifier.height(460.dp).fillMaxWidth(), barChartData = barChartData)

        Spacer(modifier = Modifier.height(10.dp))

//        Legends(
//            legendsConfig = legendsConfig
//        )
    }
}
