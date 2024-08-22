package com.example.goalgiver.ui.goaldetail

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.goalgiver.MainActivity
import com.example.goalgiver.R
import com.example.goalgiver.databinding.ActivityGoaldetailBinding
import com.example.goalgiver.ui.certification.CertificationDialog
import com.example.goalgiver.ui.certification.MapCertificationActivity
import com.example.goalgiver.ui.main.goal.AddGoalMain
import com.example.goalgiver.ui.main.goal.GoalSetItem
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.tabs.TabLayout

class GoalDetailActivity: AppCompatActivity() {

    private var isTeam: Boolean = false
    private var isPhoto: Boolean = true
    private var goalItem: GoalSetItem? = null

    // 카메라 임시코드
    private val REQUEST_IMAGE_CAPTURE = 101
    private val CAMERA_PERMISSION_CODE = 102
    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>

    lateinit var binding: ActivityGoaldetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoaldetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        goalItem = intent.getParcelableExtra("goalItem")

//        goalList = arrayListOf(
//            GoalSetItem("🎯", "Goal 1", "D-10", "100", "Progress 50%", 10)
//        )

        goalItem?.let {
            if (it.goalPersonCheck == 1) {
                isTeam = false
            } else {
                isTeam = true
            }
            binding.tvGoaldetailPercent.text = "${it.goalProgress}% 달성"
            binding.tvGoaldetailMaintext.text = "${it.goalIcon}"
            binding.tvGoaldetailTitle.text = it.goalTitle
            binding.tvGoaldetailDeadline.text = it.goalDDay
            binding.tvGoaldetailPoint.text = it.goalPoints
            binding.tvGoaldetailDonation.text = "기부단체: ${it.goalDonation}"
            initPieChart(it.goalProgress.toFloat())
        }

        if (savedInstanceState == null) {
            if (isTeam) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.goaldetail_frm, TeamProgressFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.goaldetail_frm, IndividualProgressFragment())
                    .commit()
            }
        }

        //initPieChart(goalItem?.goalProgress!!.toFloat())
        setTab()
        //카메라 임시코드
        takePictureLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                // handle the image here
                // 사진 찍기 완료 후 결과 이미지를 ImageView에 설정
                Log.d("blabla", "success")
                val imageBitmap = data?.extras?.get("data") as Bitmap
                //imageView.setImageBitmap(imageBitmap)
                //binding.ivGoaldetailMainphoto.setImageBitmap(imageBitmap)

                val dialog = CertificationDialog(
                    context = this,
                    imageResId = R.drawable.icn_check,
                    messageResId = R.string.certification_success,
                    targetActivity = MapCertificationActivity::class.java
                )
                dialog.show()

            }
        }
        binding.btnTemp.setOnClickListener {
            checkCameraPermission()
        }

        binding.btnGoaldetailBack.setOnClickListener {
            finish()
        }
    }

    private fun checkCameraPermission() {
        // 카메라 권한 부여 확인
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한 없다면 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            // 권한 이미 허용되었다면 촬영 진행
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        // 카메라 앱 실행 위한 Intent 생성
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        takePictureLauncher.launch(takePictureIntent)
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되면 사진 촬영 진행
                dispatchTakePictureIntent()
            } else {
                // 권한이 거부되었을 때 처리
                // (예: 사용자에게 권한이 필요하다는 메시지 표시)
            }
        }
    }

    // 찍은 사진 결과 표시
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            // 사진 찍기 완료 후 결과 이미지를 ImageView에 설정
//            Log.d("blabla", "success")
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            //imageView.setImageBitmap(imageBitmap)
//            binding.ivGoaldetailMainphoto.setImageBitmap(imageBitmap)
//
//            val dialog = CertificationDialog(
//                context = this,
//                imageResId = R.drawable.icn_check,
//                messageResId = R.string.app_name
//            )
//            dialog.show()
        }
    }

    private fun initPieChart(percent: Float) {
        val percentRatio = listOf(PieEntry(percent), PieEntry(100 - percent))

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
                val fragment = when {
                    isTeam -> when (p0.position) {
                        0 -> TeamProgressFragment()
                        1 -> CalendarCertificationFragment()
                        else -> null
                    }
                    isPhoto -> when (p0.position) {
                        0 -> IndividualProgressFragment.newInstance(goalItem)
                        1 -> PhotoCertificationFragment()
                        else -> null
                    }
                    else -> when (p0.position) {
                        0 -> IndividualProgressFragment.newInstance(goalItem)
                        1 -> CalendarCertificationFragment()
                        else -> null
                    }
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