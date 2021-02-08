package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemHistoryProductBinding
import com.nuveq.data_entry.model.history.Sku

class ProductHistoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Sku>() {

        override fun areItemsTheSame(oldItem: Sku, newItem: Sku): Boolean {
            return oldItem.skuId == newItem.skuId
        }

        override fun areContentsTheSame(oldItem: Sku, newItem: Sku): Boolean {
            return oldItem.skuId == newItem.skuId
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemHistoryProductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_history_product,
                parent,
                false
        )
        return VocationalTrainingHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VocationalTrainingHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Sku>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class VocationalTrainingHolder constructor(private val binding: ItemHistoryProductBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Sku) {
            binding.data = item
        }
    }


}