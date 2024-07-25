package com.example.goalgiver.ui.goaldetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goalgiver.R
import com.example.goalgiver.databinding.ActivityGoaldetailBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.tabs.TabLayout

class GoalDetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityGoaldetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoaldetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.goaldetail_frm, IndividualProgressFragment())
                .commit()
        }

        initPieChart()
        setTab()
    }

    private fun initPieChart() {
        val percentRatio = listOf(PieEntry(60f), PieEntry(40f))

        val pieColors = listOf(resources.getColor(R.color.brand_orange, null), resources.getColor(R.color.brand_orange_50, null))

        val dataSet = PieDataSet(percentRatio, "")

        dataSet.colors = pieColors

        dataSet.setDrawValues(false)

        binding.chartGoaldetailPercent.apply {
            data = PieData(dataSet)

            description.isEnabled = false
            legend.isEnabled = false
            isRotationEnabled = true
            holeRadius = 60f
            setTouchEnabled(false)
            animateY(1000, Easing.EaseInOutCubic)

            animate()
        }
    }

    private fun setTab() {
        binding.tablayoutGoaldetail.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab) {
                val fragment = when (p0.position) {
                    0 -> IndividualProgressFragment()
                    1 -> PhotoCertificationFragment()
                    else -> null
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.goaldetail_frm, fragment)
                        .commit()
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }
}