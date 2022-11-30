package com.test.featue_phone_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.test.android_utils.viewBinding
import com.test.featue_phone_detail.adapters.ImageViewPagerAdapter
import com.test.featue_phone_detail.adapters.PagerAdapter
import com.test.featue_phone_detail.databinding.FragmentPhoneDetailBinding
import com.test.repository_phones.domain.InfoAboutPhoneResult
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class PhoneDetailFragment : Fragment(R.layout.fragment_phone_detail) {

    val binding by viewBinding<FragmentPhoneDetailBinding>()
    val viewModel by viewModels<PhoneDetailViewModel>()

    private val vpAdapter by lazy {
        ImageViewPagerAdapter(requireContext(), LayoutInflater.from(requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.infoAboutPhone()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageViewpager()
        initClickListeners()
        initObservers()
    }

    private fun initProductDetailsViewPager(infoAboutPhoneResult: InfoAboutPhoneResult) {
        val adapter = PagerAdapter(requireActivity())
        adapter.infoAboutPhone = infoAboutPhoneResult
        binding.productDetailsViewPager.adapter = adapter
        TabLayoutMediator(binding.productFeatures, binding.productDetailsViewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(com.test.core_resources.R.string.shop)
                1 -> tab.text = getString(com.test.core_resources.R.string.details)
                else -> tab.text = getString(com.test.core_resources.R.string.features)
            }
        }.attach()

    }

    private fun initImageViewpager() {
        binding.productImagesViewpager.adapter = vpAdapter
        binding.productImagesViewpager.clipToPadding = false
        binding.productImagesViewpager.setPadding(10, 0, 10, 0)

        binding.productImagesViewpager.offscreenPageLimit = 1

        val nextItemVisiblePx =
            resources.getDimension(com.test.core_resources.R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(com.test.core_resources.R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))

        }
        binding.productImagesViewpager.setPageTransformer(pageTransformer)
    }


    private fun initClickListeners(){

        binding.buttonFavorite.setOnClickListener {
            viewModel.setFavorite(!viewModel.getFavorite())
        }

        binding.addToCartButton.addToCartButtonContainer.setOnClickListener{
            viewModel.navigateToCart()
        }

        binding.include.productDetailShoppingCart.setOnClickListener {
            viewModel.navigateToCart()
        }

        binding.include.productDetailBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun initObservers() {
        viewModel.infoAboutPhoneLD.observe(viewLifecycleOwner) { info ->
            vpAdapter.updateList(info.images)
            initProductDetailsViewPager(info)
            binding.productLabel.text = info.title
            binding.ratingBar.rating = info.rating.toFloat()

            val formatter: NumberFormat = NumberFormat.getInstance(Locale.US)
            formatter.maximumFractionDigits = 2
            formatter.minimumFractionDigits = 2

            binding.addToCartButton.buttonCartPrice.text =
                getString(
                    com.test.core_resources.R.string.phone_price,
                    formatter.format(info.price)
                )

        }

        viewModel.isFavoriteLd.observe(viewLifecycleOwner) {

            if (it) {
                binding.buttonFavorite.setBackgroundResource(com.test.core_resources.R.drawable.dark_blue_rectangle)
                binding.buttonFavorite.setPadding(25, 25, 25, 25)
            } else {
                binding.buttonFavorite.setBackgroundResource(com.test.core_resources.R.drawable.orange_rectangle)
                binding.buttonFavorite.setPadding(25, 25, 25, 25)
            }

        }
    }

}
