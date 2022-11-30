package com.test.featue_phone_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.test.android_utils.viewBinding
import com.test.featue_phone_detail.adapters.PhoneCapacityAdapter
import com.test.featue_phone_detail.adapters.PhoneColorChangeAdapter
import com.test.featue_phone_detail.databinding.FragmentProductDetailsShopBinding
import com.test.repository_phones.domain.InfoAboutPhoneResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailsShop(val infoAboutPhoneResult: InfoAboutPhoneResult?) :
    Fragment(R.layout.fragment_product_details_shop) {


    val binding by viewBinding<FragmentProductDetailsShopBinding>()
    val viewModel by viewModels<PhoneDetailViewModel>()

    private val colorAdapter by lazy {
        PhoneColorChangeAdapter(LayoutInflater.from(requireContext())) {
            viewModel.phoneColorClick(it)
        }
    }

    private val capacityAdapter by lazy{
        PhoneCapacityAdapter(requireContext(),LayoutInflater.from(requireContext())){
            viewModel.phoneCapacityClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoAboutPhoneResult?.let {
            viewModel.phoneColors(it.color)
            viewModel.phoneCapacity(it.capacity)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayPhoneInfo()
        initColorRecycler()
        initCapacityRecycler()
        initObservers()
    }

    private fun displayPhoneInfo() {
        if (infoAboutPhoneResult != null) {
            binding.cameraInfo.text = infoAboutPhoneResult.camera
            binding.cpuInfo.text = infoAboutPhoneResult.cpu
            binding.ramInfo.text = infoAboutPhoneResult.ssd
            binding.memoryInfo.text = infoAboutPhoneResult.sd
        }
    }

    fun initColorRecycler() {
        binding.phoneColorRv.adapter = colorAdapter
    }

    fun initCapacityRecycler(){
        binding.phoneCapacityRv.adapter = capacityAdapter
    }

    fun initObservers(){
        viewModel.colorsListLd.observe(viewLifecycleOwner){
            colorAdapter.updateList(it)
        }
        viewModel.phoneCapacityListLd.observe(viewLifecycleOwner){
            capacityAdapter.updateList(it)
        }
    }

}