package com.example.goalgiver.ui.goaldetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.databinding.FragmentGoaldetailIndividualprogressBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.tabs.TabLayout

class IndividualProgressFragment: Fragment() {

    lateinit var binding: FragmentGoaldetailIndividualprogressBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoaldetailIndividualprogressBinding.inflate(inflater, container, false)

        binding.progressTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> initWeeklyBarChart()
                    1 -> initMonthlyBarChart()
                    2 -> initYearlyBarChart()
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })

        initWeeklyBarChart()

        return binding.root
    }

    private fun initWeeklyBarChart() {
        val barChart = binding.chartGoaldetailProgressBar
        barChart.setTouchEnabled(false)

        // Create a list of BarEntry
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, floatArrayOf(50f, 0f))) // 일
        entries.add(BarEntry(1f, floatArrayOf(70f, 0f))) // 월
        entries.add(BarEntry(2f, floatArrayOf(30f, 0f))) // 화
        entries.add(BarEntry(3f, floatArrayOf(80f, 0f))) // 수
        entries.add(BarEntry(4f, floatArrayOf(50f, 0f))) // 목
        entries.add(BarEntry(5f, floatArrayOf(60f, 0f))) // 금
        entries.add(BarEntry(6f, floatArrayOf(90f, 0f))) // 토

//        entries.add(BarEntry(0f, 5f)) // 일
//        entries.add(BarEntry(1f, 7f)) // 월
//        entries.add(BarEntry(2f, 3f)) // 화
//        entries.add(BarEntry(3f, 8f)) // 수
//        entries.add(BarEntry(4f, 5f)) // 목
//        entries.add(BarEntry(5f, 6f)) // 금
//        entries.add(BarEntry(6f, 9f)) // 토

        val dataSet = BarDataSet(entries, "Days")
//        dataSet.colors = listOf(R.color.brand_orange_400, R.color.brand_orange_100)
        dataSet.colors = listOf(
            Color.parseColor("#FFA530"), // yellow color
            Color.parseColor("#FFDFB4")  // blue color
        )
        dataSet.setDrawValues(false)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 16f

        val data = BarData(dataSet)
        barChart.data = data

        // Hide the description
        barChart.description.isEnabled = false

        // Hide the legend
        barChart.legend.isEnabled = false

        // Customizing the X-axis
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("일", "월", "화", "수", "목", "금", "토"))
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        // Customizing the Y-axis (left)
        val leftAxis = barChart.axisLeft
        leftAxis.isEnabled = false // Hides left Y-axis

        // Customizing the Y-axis (right)
        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false // Hides right Y-axis

        // Hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.setDrawGridBackground(false)

        //barChart.renderer = RoundedBarChartRenderer(barChart, barChart.animator, barChart.viewPortHandler)

        // Refresh the chart
        barChart.invalidate()

    }

    private fun initMonthlyBarChart() {
        val barChart = binding.chartGoaldetailProgressBar
        barChart.setTouchEnabled(false)

        // Create a list of BarEntry
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, floatArrayOf(50f, 0f))) // 1
        entries.add(BarEntry(1f, floatArrayOf(70f, 0f))) // 2
        entries.add(BarEntry(2f, floatArrayOf(30f, 0f))) // 3
        entries.add(BarEntry(3f, floatArrayOf(80f, 0f))) // 4

        val dataSet = BarDataSet(entries, "Weeks")
//        dataSet.colors = listOf(R.color.brand_orange_400, R.color.brand_orange_100)
        dataSet.colors = listOf(
            Color.parseColor("#FFA530"), // yellow color
            Color.parseColor("#FFDFB4")  // blue color
        )
        dataSet.setDrawValues(false)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 16f

        val data = BarData(dataSet)
        barChart.data = data

        // Hide the description
        barChart.description.isEnabled = false

        // Hide the legend
        barChart.legend.isEnabled = false

        // Customizing the X-axis
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("1주", "2주", "3주", "4주"))
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        // Customizing the Y-axis (left)
        val leftAxis = barChart.axisLeft
        leftAxis.isEnabled = false // Hides left Y-axis

        // Customizing the Y-axis (right)
        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false // Hides right Y-axis

        // Hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.setDrawGridBackground(false)

        //barChart.renderer = RoundedBarChartRenderer(barChart, barChart.animator, barChart.viewPortHandler)

        // Refresh the chart
        barChart.invalidate()

    }

    private fun initYearlyBarChart() {
        val barChart = binding.chartGoaldetailProgressBar
        barChart.setTouchEnabled(false)

        // Create a list of BarEntry
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, floatArrayOf(50f, 0f))) // 1
        entries.add(BarEntry(1f, floatArrayOf(70f, 0f))) // 2
        entries.add(BarEntry(2f, floatArrayOf(30f, 0f))) // 3
        entries.add(BarEntry(3f, floatArrayOf(80f, 0f))) // 4
        entries.add(BarEntry(4f, floatArrayOf(50f, 0f))) // 5
        entries.add(BarEntry(5f, floatArrayOf(60f, 0f))) // 6
        entries.add(BarEntry(6f, floatArrayOf(50f, 0f))) // 7
        entries.add(BarEntry(7f, floatArrayOf(70f, 0f))) // 8
        entries.add(BarEntry(8f, floatArrayOf(30f, 0f))) // 9
        entries.add(BarEntry(9f, floatArrayOf(80f, 0f))) // 10
        entries.add(BarEntry(10f, floatArrayOf(50f, 0f))) // 11
        entries.add(BarEntry(11f, floatArrayOf(60f, 0f))) // 12

        val dataSet = BarDataSet(entries, "Months")
//        dataSet.colors = listOf(R.color.brand_orange_400, R.color.brand_orange_100)
        dataSet.colors = listOf(
            Color.parseColor("#FFA530"), // yellow color
            Color.parseColor("#FFDFB4")  // blue color
        )
        dataSet.setDrawValues(false)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 16f

        val data = BarData(dataSet)
        barChart.data = data

        // Hide the description
        barChart.description.isEnabled = false

        // Hide the legend
        barChart.legend.isEnabled = false

        // Customizing the X-axis
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"))
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        // Customizing the Y-axis (left)
        val leftAxis = barChart.axisLeft
        leftAxis.isEnabled = false // Hides left Y-axis

        // Customizing the Y-axis (right)
        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false // Hides right Y-axis

        // Hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.setDrawGridBackground(false)

        //barChart.renderer = RoundedBarChartRenderer(barChart, barChart.animator, barChart.viewPortHandler)

        // Refresh the chart
        barChart.invalidate()

    }
}