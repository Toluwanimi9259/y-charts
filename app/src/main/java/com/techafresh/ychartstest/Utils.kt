package com.techafresh.ychartstest

import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.ui.barchart.models.BarData
import kotlin.random.Random

fun generateRandomColor() : Color =
    Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

fun generateRandomNumber() : Int = Random.nextInt(1, 11)

fun getLegendDataFromBarChart(barData: List<BarData>) : List<LegendLabel>{
    val legendsList = mutableListOf<LegendLabel>()
    barData.forEach { data ->
        legendsList.add(LegendLabel(data.color, data.label))
    }
    return legendsList
}