package com.kyj.presentation.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyj.domain.model.CoronaCenter
import com.kyj.presentation.databinding.ItemInfoWindowBinding
import com.naver.maps.map.overlay.InfoWindow

class InfoWindowAdapter(
    context: Context,
    private val parent: ViewGroup,
    private val coronaCenter: CoronaCenter,
) : InfoWindow.DefaultViewAdapter(context) {
    override fun getContentView(p0: InfoWindow): View {
        val binding = ItemInfoWindowBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        bindViews(binding)
        return binding.root
    }

    private fun bindViews(binding: ItemInfoWindowBinding) {
        binding.nameTextView.text = coronaCenter.centerName
        binding.addressTextView.text = coronaCenter.address
        binding.facilityNameTextView.text = coronaCenter.facilityName
        binding.phoneNumberTextView.text = coronaCenter.phoneNumber
        binding.updatedAtTextView.text = coronaCenter.updatedAt
    }
}