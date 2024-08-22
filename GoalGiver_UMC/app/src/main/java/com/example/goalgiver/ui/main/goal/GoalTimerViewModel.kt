package com.example.goalgiver.ui.main.goal

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoalTimerViewModel : ViewModel() {

    private val _goalTimers = MutableLiveData<MutableMap<GoalSetItem, Long>>()
    val goalTimers: LiveData<MutableMap<GoalSetItem, Long>> = _goalTimers

    private val timers = mutableMapOf<GoalSetItem, CountDownTimer>()

    // goalList 상태를 ViewModel에서 유지
    val goalList = MutableLiveData<ArrayList<GoalSetItem>>()

    init {
        _goalTimers.value = mutableMapOf()
        goalList.value = arrayListOf()
    }

    fun isTimerRunning(goal: GoalSetItem): Boolean {
        return timers.containsKey(goal)
    }

    fun startTimer(goal: GoalSetItem) {
        if (isTimerRunning(goal)) return

        if (goal.remainingTime > 0) {
            val timer = object : CountDownTimer(goal.remainingTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateTimer(goal, millisUntilFinished)
                }

                override fun onFinish() {
                    updateTimer(goal, 0)
                    timers.remove(goal)
                }
            }
            timers[goal] = timer
            timer.start()
        }
    }

    private fun updateTimer(goal: GoalSetItem, millisUntilFinished: Long) {
        val updatedTimers = _goalTimers.value ?: mutableMapOf()
        updatedTimers[goal] = millisUntilFinished
        _goalTimers.postValue(updatedTimers)

        goal.remainingTime = millisUntilFinished
    }

    override fun onCleared() {
        super.onCleared()
        timers.values.forEach { it.cancel() }
    }
}