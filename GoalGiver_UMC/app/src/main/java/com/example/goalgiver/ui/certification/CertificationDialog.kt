package com.example.goalgiver.ui.certification

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.goalgiver.MainActivity
import com.example.goalgiver.R
import com.example.goalgiver.ui.main.people.PeopleFragment

class CertificationDialog(
    private val activity: Activity,
    private val imageResId: Int,
    private val messageResId: Int
): Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_certification_success)

//        window?.setLayout(
//            ConstraintLayout.LayoutParams.MATCH_PARENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        )

        val imageView: ImageView = findViewById(R.id.icon_check)
        val textView: TextView = findViewById(R.id.tv_certification)

        imageView.setImageResource(imageResId)
        textView.setText(messageResId)

        setOnDismissListener {
            activity.finish()
        }
    }
}