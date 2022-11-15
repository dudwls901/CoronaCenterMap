package com.kyj.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kyj.presentation.databinding.ActivitySplashBinding
import com.kyj.presentation.util.event.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()
    private var duration = 0
    private val initProgress: Job by lazy {
        lifecycleScope.launch {
            startAnim(1600)
        }
    }
    private val continueProgress: Job by lazy {
        lifecycleScope.launch {
            initProgress.cancel()
            startAnim(2000)
            navigateMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startDownload()
        observeDatas()
    }

    private fun observeDatas() {
        splashViewModel.errorMessage.observe(this, EventObserver {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun startDownload() {
        initProgress.start()
        lifecycleScope.launch {
            if (splashViewModel.getAllCoronaCenters(10)) {
                continueProgress.start()
            }
        }
    }

    private suspend fun startAnim(end: Int) {
        while (duration <= end) {
            binding.loadingBar.progress = duration / 20
            delay(1L)
            duration++
        }
    }

    private fun navigateMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

}