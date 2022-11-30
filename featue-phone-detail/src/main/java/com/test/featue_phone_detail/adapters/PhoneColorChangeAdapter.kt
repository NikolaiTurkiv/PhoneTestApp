package com.test.featue_phone_detail.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.featue_phone_detail.ColorItem
import com.test.featue_phone_detail.databinding.ColorItemBinding

class PhoneColorChangeAdapter(
    private val inflater: LayoutInflater,
    private val itemClickListener: (item: ColorItem) -> Unit
) :
    RecyclerView.Adapter<PhoneColorChangeAdapter.PhoneColorChangeViewHolder>() {

    private val colorItems = mutableListOf<ColorItem>()

    fun updateList(list: List<ColorItem>) {
        colorItems.clear()
        colorItems.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneColorChangeViewHolder {
        return PhoneColorChangeViewHolder(ColorItemBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: PhoneColorChangeViewHolder, position: Int) {
        val item = colorItems[position]
        holder.bind(item, itemClickListener)
    }

    override fun getItemCount(): Int = colorItems.size

    class PhoneColorChangeViewHolder(
        val binding: ColorItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(colorItem: ColorItem, itemClickListener: (item: ColorItem) -> Unit) {
            binding.chekedIcon.isVisible = colorItem.isClicked
            binding.shapeableImageView.setBackgroundColor(Color.parseColor(colorItem.color))
            binding.colorItemContainer.setOnClickListener {
                itemClickListener.invoke(colorItem)
            }
        }
    }
}
