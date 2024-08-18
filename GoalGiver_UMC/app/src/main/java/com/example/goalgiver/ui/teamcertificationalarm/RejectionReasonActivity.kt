package com.example.goalgiver.ui.teamcertificationalarm

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.goalgiver.databinding.ActivityFillrejectionreasonBinding

class RejectionReasonActivity: AppCompatActivity() {

    lateinit var binding: ActivityFillrejectionreasonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFillrejectionreasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var str: String = binding.etRejectionReason.text.toString()

        binding.btnComplete.setOnClickListener {
            val str = binding.etRejectionReason.text.toString()
            if (str.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("거부 사유를 입력해주세요.")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->

                        })

                builder.show()
            } else {
                val intent = Intent()
                intent.putExtra("isChecked", true)
                intent.putExtra("position", intent.getIntExtra("position", -1))
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }
}