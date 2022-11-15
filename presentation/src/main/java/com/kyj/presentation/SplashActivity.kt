package com.kyj.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kyj.presentation.common.constant.PROGRESS_END_TIME_MILLIS
import com.kyj.presentation.common.constant.PROGRESS_MIDDLE_TIME_MILLIS
import com.kyj.presentation.common.constant.PROGRESS_START_TIME_MILLIS
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
    private var progressDuration = PROGRESS_START_TIME_MILLIS
    private val initProgress: Job by lazy {
        lifecycleScope.launch {
            startAnimation(PROGRESS_MIDDLE_TIME_MILLIS)
        }
    }
    private val continueProgress: Job by lazy {
        lifecycleScope.launch {
            initProgress.cancel()
            startAnimation(PROGRESS_END_TIME_MILLIS)
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
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })
    }

    private fun startDownload() {
        initProgress.start()
        lifecycleScope.launch {
            if (splashViewModel.getAllCoronaCenters(10)) {
                continueProgress.start()
            } else {
                stopAnimation()
            }
        }
    }

    private fun stopAnimation() {
        initProgress.cancel()
        continueProgress.cancel()
    }

    private suspend fun startAnimation(end: Int) {
        while (progressDuration <= end) {
            binding.loadingBar.progress = progressDuration / 20
            delay(1L)
            progressDuration++
        }
    }

    private fun navigateMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

}