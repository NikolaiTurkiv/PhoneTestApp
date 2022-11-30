package com.test.feature_home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.*
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.test.android_utils.viewBinding
import com.test.feature_home.databinding.BestSellerItemBinding
import com.test.feature_home.databinding.FragmentHomeBinding
import com.test.repository_phones.domain.FullInfoResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    val binding by viewBinding<FragmentHomeBinding>()
    val viewModel by viewModels<HomeViewModel>()

    private val adapter by lazy {
        BestSellersAdapter(requireContext(), LayoutInflater.from(requireContext()), {
            viewModel.navigateToPhoneDetails()
        }, {
            viewModel.setFavorites(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
         window.statusBarColor = resources.getColor(com.test.core_resources.R.color.main_gray)
        viewModel.getBestSellers()
        viewModel.getCartPhones()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers()
        initClickListeners()
        initFilterAdapters()
        initRecycler()
    }

    private fun initClickListeners() {
        binding.include.filter.setOnClickListener {
            viewModel.filtersVisibility(!binding.filtersLayout.root.isVisible)
        }
        binding.filtersLayout.doneButton.setOnClickListener {
            viewModel.filtersVisibility(false)

        }
        binding.filtersLayout.closeFilters.setOnClickListener {
            viewModel.filtersVisibility(false)
        }

        binding.includeExplorerLt.cart.setOnClickListener{
            viewModel.navigateToCart()
        }

    }

    private fun initFilterAdapters() {
        initBrandSpinnerAdapter()
        initPriceSpinner()
        initSizeSpinner()
    }

    private fun initRecycler() {
        binding.bestSellerRv.adapter = adapter
    }

    private fun initBrandSpinnerAdapter() {
        val adapter = SpinnerAdapter(
            resources.getStringArray(com.test.core_resources.R.array.brands),
            requireContext()
        )
        binding.filtersLayout.brandSpinner.adapter = adapter
    }

    private fun initPriceSpinner() {
        val adapter = SpinnerAdapter(
            resources.getStringArray(com.test.core_resources.R.array.price),
            requireContext()
        )
        binding.filtersLayout.priceSpinner.adapter = adapter
    }

    private fun initSizeSpinner() {
        val adapter = SpinnerAdapter(
            resources.getStringArray(com.test.core_resources.R.array.size),
            requireContext()
        )
        binding.filtersLayout.sizeSpinner.adapter = adapter
    }


    private fun initObservers() {
        viewModel.filtersVisibility.observe(viewLifecycleOwner) { visible ->
            if (visible) {
                binding.filtersLayout.root.visibility = View.VISIBLE
            } else {
                binding.filtersLayout.root.visibility = View.GONE

            }
        }

        viewModel.bestSellersLD.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        viewModel.cartPhoneCount.observe(viewLifecycleOwner){
            if(it == 0){
                binding.includeExplorerLt.countBgCircle.isVisible = false
                binding.includeExplorerLt.counter.isVisible = false
            }else{
                binding.includeExplorerLt.counter.text = it.toString()
            }

        }
    }

    private class SpinnerAdapter(val list: Array<String>, val context: Context) : BaseAdapter() {

        override fun getCount(): Int = list.size

        override fun getItem(p0: Int): Any = list[p0]


        override fun getItemId(p0: Int): Long = p0.toLong()


        @SuppressLint("ViewHolder")
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView =
                LayoutInflater.from(context).inflate(R.layout.filter_adapter_item, p2, false)

            val text = rootView.findViewById<TextView>(R.id.spinner_item)
            text.text = list[p0]

            return rootView

        }

    }

    private class BestSellersAdapter(
        private val context: Context,
        private val inflater: LayoutInflater,
        private val itemClickListener: () -> Unit,
        private val itemFavoriteClickListener: (item: FullInfoResult.BestSellerResult) -> Unit
    ) : RecyclerView.Adapter<BestSellersAdapter.BestSellersViewHolder>() {

        val bestSellersList = mutableListOf<FullInfoResult.BestSellerResult>()

        fun updateList(list: List<FullInfoResult.BestSellerResult>) {
            bestSellersList.clear()
            bestSellersList.addAll(list)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellersViewHolder {
            return BestSellersViewHolder(
                context,
                BestSellerItemBinding.inflate(inflater, parent, false)
            )
        }

        override fun onBindViewHolder(holder: BestSellersViewHolder, position: Int) {
            val item = bestSellersList[position]
            holder.bind(item, itemClickListener, itemFavoriteClickListener)
        }

        override fun getItemCount(): Int = bestSellersList.size


        class BestSellersViewHolder(
            private val context: Context,
            private val binding: BestSellerItemBinding
        ) : ViewHolder(binding.root) {
            fun bind(
                item: FullInfoResult.BestSellerResult,
                itemClickListener: () -> Unit,
                itemFavoriteClickListener: (item: FullInfoResult.BestSellerResult) -> Unit
            ) {

                Picasso.get().load(item.picture).into(binding.itemPhoneImage)
                binding.actualPrice.text = context.getString(
                    com.test.core_resources.R.string.phone_price,
                    item.priceWithoutDiscount
                )

                val discountPrice = context.getString(
                    com.test.core_resources.R.string.phone_price,
                    item.discountPrice
                )
                val stringSpannable = SpannableString(discountPrice)
                stringSpannable.setSpan(
                    StrikethroughSpan(),
                    0,
                    discountPrice.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                binding.originalPrice.text = stringSpannable


                binding.phoneLabel.text = item.title

                if (item.isFavorites) {
                    binding.favoriteHeartImage.setImageResource(com.test.core_resources.R.drawable.completed_heart)
                } else {
                    binding.favoriteHeartImage.setImageResource(com.test.core_resources.R.drawable.empty_heart)
                }

                binding.favoriteHeartImage.setOnClickListener {
                    itemFavoriteClickListener.invoke(item)
                }

                binding.bestSellerContainer.setOnClickListener {
                    itemClickListener.invoke()
                }
            }

        }
    }

}