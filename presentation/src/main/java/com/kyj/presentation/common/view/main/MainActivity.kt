package com.kyj.presentation.common.view.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kyj.domain.model.CoronaCenter
import com.kyj.presentation.R
import com.kyj.presentation.common.util.getColor
import com.kyj.presentation.common.util.getNaverMapErrorMessage
import com.kyj.presentation.databinding.ActivityMainBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var naverMap: NaverMap
    private lateinit var mapFragment: MapFragment
    private val locationSource: FusedLocationSource by lazy {
        FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }
    private val infoWindow by lazy { InfoWindow() }
    private val markerIcon: OverlayImage by lazy {
        OverlayImage.fromResource(com.naver.maps.map.R.drawable.navermap_default_marker_icon_green)
    }
    private val overlayClickListener: Overlay.OnClickListener by lazy {
        Overlay.OnClickListener { overlay ->
            mainViewModel.updateSelectedCoronaCenter(overlay.tag as Int)
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNaverMapForSDK()
    }

    private fun getNaverMapForSDK() {
        //콜백을 통해 NaverMap 가져오기
        try {
            mapFragment = supportFragmentManager.findFragmentById(R.id.naverMap) as MapFragment?
                ?: MapFragment.newInstance().also {
                    supportFragmentManager.beginTransaction().add(R.id.naverMap, it).commit()
                }
            mapFragment.getMapAsync(this)
        } catch (e: Exception) {
            Snackbar.make(binding.root, e.getNaverMapErrorMessage(), Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        requestLocationPermissions()
        initViews()
    }

    private fun initViews() {
        observeDatas()
        initMap()
        initButton()
    }

    private fun observeDatas() {
        mainViewModel.coronaCenters.observe(this) { coronaCenters ->
            lifecycleScope.launch {
                coronaCenters.forEach { (_, coronaCenterUi) ->
                    setMarkerInMap(coronaCenterUi)
                }
            }
        }
        mainViewModel.selectedCoronaCenter.observe(this) { coronaCenterUi ->
            val marker = coronaCenterUi.marker
            moveCamera(marker.position)
            showInfoWindow(
                marker,
                coronaCenterUi.coronaCenter
            )
        }
    }

    private fun initMap() {
        naverMap.locationSource = locationSource

        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.setOnMapClickListener { _, _ ->
            if (infoWindow.isAdded) {
                infoWindow.close()
            }
        }
    }

    private fun initButton() {

        binding.currentPositionButton.setOnClickListener {
            if (infoWindow.isAdded) {
                infoWindow.close()
            }
            locationSource.lastLocation?.let {
                moveCamera(
                    LatLng(
                        it.latitude,
                        it.longitude
                    )
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)
        ) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    private fun showInfoWindow(
        marker: Marker,
        coronaCenter: CoronaCenter,
    ) {
        infoWindow.adapter = InfoWindowAdapter(
            this,
            binding.root as ViewGroup,
            coronaCenter
        )
        if (marker.infoWindow == null) {
            infoWindow.open(marker)
        } else {
            infoWindow.close()
        }
    }

    private suspend fun setMarkerInMap(
        coronaCenterUi: CoronaCenterUi,
    ) {
        withContext(Dispatchers.Default) {
            setOverlay(
                coronaCenterUi.marker,
                coronaCenterUi.coronaCenter
            )
        }
        coronaCenterUi.marker.map = naverMap
    }

    private fun setOverlay(
        marker: Marker,
        coronaCenter: CoronaCenter,
    ) {
        with(marker) {
            position = LatLng(coronaCenter.lat, coronaCenter.lng)
            onClickListener = overlayClickListener
            tag = coronaCenter.id
            icon = markerIcon
            iconTintColor = coronaCenter.centerType.getColor()
        }
    }

    private fun moveCamera(latLng: LatLng) {
        val cameraUpdate = CameraUpdate.scrollTo(
            latLng
        ).animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}