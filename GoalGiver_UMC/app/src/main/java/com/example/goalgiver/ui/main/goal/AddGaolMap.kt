package com.example.goalgiver.ui.main.goal;

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goalgiver.R
import com.example.goalgiver.databinding.KakaoMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

internal class AddGaolMap : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val TAG = "AddGaolMap"
        const val RESULT_LOCATION = "result_location"
    }

    private lateinit var binding: KakaoMapBinding

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var currentMarker: Marker? = null
    private var selectedAddress: String? = null // 사용자가 선택한 주소를 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = KakaoMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.mapView = binding.addGoalMap
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@AddGaolMap)

        findViewById<Button>(R.id.add_goal_map_cencel).setOnClickListener {
            finish() // 현재 액티비티 종료
        }

        findViewById<Button>(R.id.add_goal_map_complete).setOnClickListener {
            if (selectedAddress != null) {
                val resultIntent = Intent()
                resultIntent.putExtra(RESULT_LOCATION, selectedAddress)
                setResult(RESULT_OK, resultIntent)
                finish() // 현재 액티비티 종료 및 결과 반환
            } else {
                // 주소가 선택되지 않았을 경우의 처리 (예: Toast 메시지 표시)
                Toast.makeText(this, "위치를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        val seoul = LatLng(37.5562, 126.9724)
        currentMarker = setupMarker(seoul)
        currentMarker?.showInfoWindow()

        googleMap.setOnMapClickListener { latLng ->
            currentMarker?.remove()
            currentMarker = setupMarker(latLng)
            currentMarker?.showInfoWindow()

            // Geocoder를 사용하여 LatLng을 주소로 변환
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            selectedAddress = addresses?.get(0)?.getAddressLine(0) ?: "주소를 찾을 수 없습니다."
        }
    }

    private fun setupMarker(latLng: LatLng): Marker? {
        val markerOptions = MarkerOptions().apply {
            position(latLng)
            title("선택한 위치")
            snippet("위치 정보")
        }

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        return googleMap.addMarker(markerOptions)
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
}
