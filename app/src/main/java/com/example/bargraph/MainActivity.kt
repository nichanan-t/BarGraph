package com.example.bargraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bargraph.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.XAxis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        val barWidth = 0.05f
        val values1 = ArrayList<BarEntry>()
        values1.add(BarEntry(0F, floatArrayOf(14f, 14f, 14f)))
        val set1: BarDataSet

        val xAxis: XAxis = binding.chart.xAxis
        xAxis.setDrawLabels(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        var yAxis: YAxis = binding.chart.axisLeft
        yAxis.setDrawGridLines(false)
        yAxis.setDrawLabels(false)
        yAxis.setDrawAxisLine(false)

        yAxis = binding.chart.axisRight
        yAxis.setDrawGridLines(false)
        yAxis.setDrawLabels(false)
        yAxis.setDrawAxisLine(false)

        binding.chart.axisRight.granularity = 1f
        binding.chart.axisRight.isGranularityEnabled = true
        binding.chart.axisRight.setCenterAxisLabels(false)
        binding.chart.description.isEnabled = false
        binding.chart.setFitBars(true)

        if (binding.chart.data != null && binding.chart.data.dataSetCount > 0) {
            set1 = binding.chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values1
            binding.chart.data.notifyDataChanged()
            binding.chart.notifyDataSetChanged()
        }

        else {
            set1 = BarDataSet(values1, null)
            set1.setDrawIcons(false)
            set1.colors = getColors()
            val dataSets: ArrayList<IBarDataSet> = ArrayList()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = barWidth
            binding.chart.data = data
        }


    }

    private fun getColors(): List<Int> {
    // have as many colors as stack-values per entry
        val colors = IntArray(3)
        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3)
        return colors.toMutableList()
    }
}