package com.example.goalgiver.ui.certification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goalgiver.R
import com.example.goalgiver.databinding.ActivityMapcertificationBinding
import com.example.goalgiver.ui.main.goal.AddGoalMain
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

internal class MapCertificationActivity: AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val TAG = "MapCertificationActivity"
    }

    lateinit var binding: ActivityMapcertificationBinding

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapcertificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.mapView = binding.mvMapcertification
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@MapCertificationActivity)

        binding.btnCertificateComplete.setOnClickListener {
            val dialog = CertificationDialog(
                context = this,
                imageResId = R.drawable.icn_check,
                messageResId = R.string.certification_success,
                targetActivity = AddGoalMain::class.java
            )
            dialog.show()
        }
    }

    /**
     * onMapReady()
     * Map이 사용할 준비가 되었을 때 호출
     * @param googleMap
     */
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        currentMarker = setupMarker(LatLngEntity(37.5562, 126.9724)) // default 서울
        currentMarker?.showInfoWindow()
    }

    /**
     * setupMarker()
     * 선택한 위치의 marker 표시
     * @param locationLatLngEntity
     * @return
     */
    private fun setupMarker(locationLatLngEntity: LatLngEntity): Marker? {

        val positionLatLng = LatLng(locationLatLngEntity.latitude!!,locationLatLngEntity.longitude!!)
        val markerOption = MarkerOptions().apply {
            position(positionLatLng)
            title("위치")
            snippet("서울역 위치")
        }

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL  // 지도 유형 설정
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, 15f))  // 카메라 이동
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15f))  // 줌의 정도 - 1 일 경우 세계지도 수준, 숫자가 커질 수록 상세지도가 표시됨
        return googleMap.addMarker(markerOption)

    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }


    /**
     * LatLngEntity data class
     *
     * @property latitude 위도 (ex. 37.5562)
     * @property longitude 경도 (ex. 126.9724)
     */
    data class LatLngEntity(
        var latitude: Double?,
        var longitude: Double?
    )
}