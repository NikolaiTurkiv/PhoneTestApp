package com.test.feature_select_category

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.test.android_utils.viewBinding
import com.test.feature_select_category.databinding.FragmentSelectCategoryBinding
import com.test.feature_select_category.databinding.SelectCategoryItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCategoryFragment : Fragment(R.layout.fragment_select_category) {

    val binding by viewBinding<FragmentSelectCategoryBinding>()
    val viewModel by viewModels<SelectCategoryViewModel>()

    private val adapter by lazy {
        SelectCategoryFragmentAdapter(requireContext(),LayoutInflater.from(requireContext())){
            viewModel.updateList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
        initRecycler()
    }

    private fun initRecycler(){
        binding.selectCategoryRv.adapter = adapter
    }

    private fun initObserver(){
        viewModel.categoryListLD.observe(viewLifecycleOwner){
            adapter.updateItems(it)
        }
        adapter.updateItems(viewModel.categoryList)
    }

    private class SelectCategoryFragmentAdapter(
        private val context: Context,
        private val inflater: LayoutInflater,
        private val itemClickListener: (item: CategoryItem) -> Unit,
    ) : RecyclerView.Adapter<SelectCategoryFragmentAdapter.SelectCategoryViewHolder>() {

        private val items = mutableListOf<CategoryItem>()

        fun updateItems(categoryItem: List<CategoryItem>) {
            items.clear()
            items.addAll(categoryItem)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SelectCategoryViewHolder {
            return SelectCategoryViewHolder(
                SelectCategoryItemBinding.inflate(
                    inflater,
                    parent,
                    false
                ), context
            )
        }

        override fun onBindViewHolder(holder: SelectCategoryViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item, itemClickListener)
            holder.itemView.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }


        private class SelectCategoryViewHolder(
            private val binding: SelectCategoryItemBinding,
            private val context: Context
        ) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(categoryItem: CategoryItem, itemClickListener: (item: CategoryItem) -> Unit) {
                if (categoryItem.isClick) {
                    binding.background.setBackgroundResource(com.test.core_resources.R.drawable.orange_circle)
                    binding.icon.setBackgroundResource(categoryItem.iconClick)
                    binding.categoryTitle.setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.test.core_resources.R.color.main_orange
                        )
                    )
                } else {
                    binding.background.setBackgroundResource(com.test.core_resources.R.drawable.white_cirlce)
                    binding.icon.setBackgroundResource(categoryItem.iconNotClick)
                    binding.categoryTitle.setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.test.core_resources.R.color.main_dark_blue
                        )
                    )
                }
                binding.categoryTitle.text = categoryItem.title

                binding.categoryItemContainer.setOnClickListener {
                    itemClickListener.invoke(categoryItem)
                }
            }
        }

    }

}