package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemProductCatBinding
import com.nuveq.data_entry.model.porduct_data.Product

class ProductCatAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProductCatBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_cat,
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

    class VocationalTrainingHolder constructor(private val interaction: Interaction?, private val binding: ItemProductCatBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.data = item

            itemView.setOnClickListener {
                interaction!!.onItemSelected(layoutPosition)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int)
    }
}