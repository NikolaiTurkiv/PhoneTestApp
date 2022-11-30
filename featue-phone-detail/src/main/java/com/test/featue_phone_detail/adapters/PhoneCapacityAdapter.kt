package com.test.featue_phone_detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.featue_phone_detail.CapacityItem
import com.test.featue_phone_detail.ColorItem
import com.test.featue_phone_detail.R
import com.test.featue_phone_detail.databinding.CapacityItemBinding

class PhoneCapacityAdapter(
    private val context: Context,
    private val inflater: LayoutInflater,
    private val itemClick: (item: CapacityItem) -> Unit,
) :
    RecyclerView.Adapter<PhoneCapacityAdapter.PhoneCapacityViewHolder>() {

    private val capacitiesSize = mutableListOf<CapacityItem>()

    fun updateList(list: List<CapacityItem>) {
        capacitiesSize.clear()
        capacitiesSize.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneCapacityViewHolder {
        return PhoneCapacityViewHolder(
            CapacityItemBinding.inflate(inflater, parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: PhoneCapacityViewHolder, position: Int) {
        val item = capacitiesSize[position]
        holder.bind(item, itemClick)
    }

    override fun getItemCount(): Int = capacitiesSize.size


    class PhoneCapacityViewHolder(val binding: CapacityItemBinding, private val context: Context) :
        ViewHolder(binding.root) {
        fun bind(item: CapacityItem, itemClick: (item: CapacityItem) -> Unit) {
            binding.capacity.text = context.getString(com.test.core_resources.R.string.phone_capacity,item.capacity)
            binding.capacity.setOnClickListener {
                itemClick.invoke(item)
            }
            if (item.isClicked) {

                with(binding.capacity) {
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.test.core_resources.R.color.main_white
                        )
                    )
                    setBackgroundResource(com.test.core_resources.R.drawable.orange_rectangle)
                }
            }else{
                with(binding.capacity){
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.test.core_resources.R.color.main_dark_gray
                        )
                    )

                    setBackgroundResource(com.test.core_resources.R.drawable.white_cirlce)
                }


            }

        }
    }
}