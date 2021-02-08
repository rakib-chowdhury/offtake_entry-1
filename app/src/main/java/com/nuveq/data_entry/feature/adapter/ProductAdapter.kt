package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemProductBinding
import com.nuveq.data_entry.model.data_post.Product

class ProductAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.skuId == newItem.skuId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.skuId == newItem.skuId
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product,
                parent,
                false
        )
        return VocationalTrainingHolder(interaction, binding)
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

    fun submitList(list: List<Product>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class VocationalTrainingHolder constructor(private val interaction: Interaction?, private val binding: ItemProductBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.data = item

            binding.cross.setOnClickListener {
                interaction!!.onItemProductSelected(layoutPosition)
            }

        }
    }

    interface Interaction {
        fun onItemProductSelected(position: Int)
    }
}