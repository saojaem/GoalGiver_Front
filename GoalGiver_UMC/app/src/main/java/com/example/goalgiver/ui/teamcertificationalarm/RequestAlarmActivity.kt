package com.example.goalgiver.ui.teamcertificationalarm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.ActivityRequestalarmBinding

class RequestAlarmActivity: AppCompatActivity() {

    companion object {
        const val REJECT_REQUEST_CODE = 1001
    }

    lateinit var binding: ActivityRequestalarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestalarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAlarmRV()

    }

    private fun setAlarmRV() {
        val rv_alarm = binding.rvCertificationAlarm
        val itemList = ArrayList<CertificationAlarmItem>()

        itemList.add(CertificationAlarmItem(false, "오늘", "홍길동님이 ‘파이썬 6주차 과제’ 인증을 요청했습니다."))
        itemList.add(CertificationAlarmItem(true, "24.05.22", "홍길동님이 ‘파이썬 6주차 과제’ 인증을 승인했습니다."))

        val alarmAdapter = RequestAlarmAdapter(itemList)
        alarmAdapter.notifyDataSetChanged()

        rv_alarm.adapter = alarmAdapter
        rv_alarm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REJECT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val isChecked = data.getBooleanExtra("isChecked", false)
            val position = data.getIntExtra("position", -1)

            if (position != -1 && isChecked) {
                binding.rvCertificationAlarm.adapter?.let {
                    val itemList = (it as RequestAlarmAdapter).itemList
                    itemList[position].isChecked= true
                    it.notifyItemChanged(position)
                }
            }

        }
    }
}