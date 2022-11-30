package com.test.feature_hotsales

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.test.android_utils.viewBinding
import com.test.feature_hotsales.databinding.FragmentHotSalesBinding
import com.test.feature_hotsales.databinding.HotsalesItemLayoutBinding
import com.test.repository_phones.domain.FullInfoResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotSalesFragment : Fragment(R.layout.fragment_hot_sales) {

    val binding by viewBinding<FragmentHotSalesBinding>()
    val viewModel by viewModels<HotSalesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHotSales()
    }

    private val adapter by lazy{
        HotSalesAdapter(requireContext(),LayoutInflater.from(requireContext())){

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers()
        initRecycler()
    }

    fun initObservers(){
        viewModel.hotSalesLD.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    fun initRecycler(){
        binding.vp.adapter = adapter
    }

    private class HotSalesAdapter(
        private val context: Context,
        private val inflater: LayoutInflater,
        private val itemClickListener: (item: FullInfoResult.HomeStoreResult) -> Unit,
    ) : RecyclerView.Adapter<HotSalesAdapter.HotSalesViewHolder>() {

        val hotSalesList = mutableListOf<FullInfoResult.HomeStoreResult>()

        fun updateList(list: List<FullInfoResult.HomeStoreResult>) {
            hotSalesList.clear()
            hotSalesList.addAll(list)

            notifyDataSetChanged()
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesViewHolder {
            return HotSalesViewHolder(HotsalesItemLayoutBinding.inflate(inflater,parent,false),context)
        }

        override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
            val item = hotSalesList[position]
            holder.bind(item,itemClickListener)
        }

        override fun getItemCount(): Int = hotSalesList.size

        private class HotSalesViewHolder(
            private val binding: HotsalesItemLayoutBinding,
            private val context: Context
        ) : ViewHolder(binding.root) {
            fun bind(
                item: FullInfoResult.HomeStoreResult,
                itemClickListener: (item: FullInfoResult.HomeStoreResult) -> Unit
            ) {
                //TODO defaultPicture to Picasso
                binding.newPhone.root.isVisible = item.isNew
                binding.pholeLabel.text = item.title
                if(item.title == PHONE_NAME) binding.pholeLabel.isVisible = false
                binding.phoneDescription.text = item.subtitle
                Picasso.get().load(item.picture).into(binding.phoneImage)
                binding.buttonBuy.setOnClickListener {
                    itemClickListener.invoke(item)
                }

            }
        }
    }
}
/*
* константа нужна чтоб не перекрывать текст на картинке. Костыль
* */

private const val PHONE_NAME = "Samsung Galaxy A71"