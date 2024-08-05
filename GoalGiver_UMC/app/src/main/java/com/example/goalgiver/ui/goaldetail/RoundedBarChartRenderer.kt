package com.example.goalgiver.ui.goaldetail

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class RoundedBarChartRenderer(
    chart: BarChart,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler
) : BarChartRenderer(chart, animator, viewPortHandler) {
    private val mRadius = 20f // Adjust this value to set the corner radius

    override fun drawDataSet(c: Canvas, dataSet: com.github.mikephil.charting.interfaces.datasets.IBarDataSet, index: Int) {
        val trans = mChart.getTransformer(dataSet.axisDependency)
        mBarBorderPaint.color = dataSet.barBorderColor
        mBarBorderPaint.strokeWidth = com.github.mikephil.charting.utils.Utils.convertDpToPixel(dataSet.barBorderWidth)

        val drawBorder = dataSet.barBorderWidth > 0f

        val buffer = mBarBuffers[index]
        buffer.setPhases(mAnimator.phaseX, mAnimator.phaseY)
        buffer.setDataSet(index)
        buffer.setInverted(mChart.isInverted(dataSet.axisDependency))
        buffer.setBarWidth(mChart.barData.barWidth)

        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)

        for (j in 0 until buffer.size() step 4) {
            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) continue

            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])) break

            val isStacked = dataSet.isStacked
            if (isStacked) {
                val color = dataSet.getColor(j / 4)
                mRenderPaint.color = color

                val rectF = RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3])
                val path = android.graphics.Path()
                path.addRoundRect(rectF, mRadius, mRadius, android.graphics.Path.Direction.CW)
                c.drawPath(path, mRenderPaint)

                if (drawBorder) {
                    c.drawPath(path, mBarBorderPaint)
                }
            }
        }
    }
}