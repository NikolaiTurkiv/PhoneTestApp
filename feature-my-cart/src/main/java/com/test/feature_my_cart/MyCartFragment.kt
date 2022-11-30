package com.test.feature_my_cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.test.android_utils.viewBinding
import com.test.feature_my_cart.databinding.CartItemBinding
import com.test.feature_my_cart.databinding.FragmentMyCartBinding
import com.test.repository_phones.domain.CartInfoResult
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class MyCartFragment : Fragment(R.layout.fragment_my_cart) {

    private val viewModel by viewModels<MyCartViewModel>()
    private val binding by viewBinding<FragmentMyCartBinding>()

    private val adapter by lazy {
        MyCartAdapter(requireContext(), LayoutInflater.from(requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.cartInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initClickListeners()
        initObservers()
    }

    private fun initRecycler() {
        binding.cartInfoRv.adapter = adapter
    }

    private fun initClickListeners() {
        binding.cartToolbar.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initObservers() {
        viewModel.cartInfoLD.observe(viewLifecycleOwner) {
            adapter.updateList(it.basket)
            binding.deliveryInfo.text = it.delivery
            val formatter: NumberFormat = NumberFormat.getInstance(Locale.US)
            formatter.maximumFractionDigits = 2
            binding.totalInfo.text = getString(com.test.core_resources.R.string.phone_price_us,formatter.format(it.total))
        }

    }


    private class MyCartAdapter(
        private val context: Context,
        private val inflater: LayoutInflater,
    ) : RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder>() {

        val cartPhones = mutableListOf<CartInfoResult.Basket>()

        fun updateList(list: List<CartInfoResult.Basket>) {
            cartPhones.clear()
            cartPhones.addAll(list)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
            return MyCartViewHolder(
                context,
                CartItemBinding.inflate(inflater, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
            val item = cartPhones[position]

            holder.bind(item)
        }

        override fun getItemCount(): Int = cartPhones.size

        class MyCartViewHolder(
            private val context: Context,
            private val binding: CartItemBinding
        ) : ViewHolder(binding.root) {
            fun bind(
                item: CartInfoResult.Basket,
            ) {
                binding.counterLayout.counterInfo.text = "2"
                Picasso.get().load(item.images).into(binding.shapeableImageView)
                binding.phoneModel.text = item.title
                binding.price.text = context.getString(com.test.core_resources.R.string.phone_price,
                    item.price.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toString())
            }
        }
    }


}