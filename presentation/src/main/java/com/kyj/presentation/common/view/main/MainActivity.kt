package com.kyj.presentation.common.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kyj.domain.model.CoronaCenter
import com.kyj.presentation.R
import com.kyj.presentation.common.util.getColor
import com.kyj.presentation.databinding.ActivityMainBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
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

    private val markerIcon: OverlayImage by lazy { OverlayImage.fromResource(com.naver.maps.map.R.drawable.navermap_default_marker_icon_green) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //콜백을 통해 NaverMap 가져오기
        mapFragment = supportFragmentManager.findFragmentById(R.id.naverMap) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.naverMap, it).commit()
            }
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        observeDatas()
    }

    private fun observeDatas() {
        mainViewModel.coronaCenters.observe(this) { coronaCenters ->
            coronaCenters.forEach { coronaCenter ->
                setMarkerInMap(coronaCenter)
            }
        }
    }

    private fun setMarkerInMap(
        coronaCenter: CoronaCenter,
    ) {
        lifecycleScope.launch {
            val marker = withContext(Dispatchers.Default) {
                setOverlays(coronaCenter)
            }
            marker.map = naverMap
        }
    }

    private fun setOverlays(coronaCenter: CoronaCenter): Marker {
        return Marker().apply {
            position = LatLng(coronaCenter.lat, coronaCenter.lng)
            onClickListener = overlayClickListener
            tag = coronaCenter.id
            icon = markerIcon
            iconTintColor = coronaCenter.centerType.getColor()
        }
    }

    private val overlayClickListener = Overlay.OnClickListener { overlay ->
        moveCamera(overlay.tag as Int)
        false
    }

    private fun moveCamera(id: Int) {
        mainViewModel.getMarkerLatLng(id)?.let { (lat, lng) ->
            val cameraUpdate = CameraUpdate.scrollTo(
                LatLng(
                    lat,
                    lng
                )
            ).animate(CameraAnimation.Easing)
            naverMap.moveCamera(cameraUpdate)
        }
    }
}