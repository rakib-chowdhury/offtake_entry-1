package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemProductHistoryBinding
import com.nuveq.data_entry.model.history.History

class HistoryAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var adapter: ProductHistoryAdapter
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {

        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProductHistoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_history,
                parent,
                false
        )
        adapter = ProductHistoryAdapter()
        return VocationalTrainingHolder(interaction, binding, adapter)
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

    fun submitList(list: List<History>) {
        differ.submitList(list)

        notifyDataSetChanged()
    }

    class VocationalTrainingHolder constructor(private val interaction: Interaction?, private val binding: ItemProductHistoryBinding, private val adapter: ProductHistoryAdapter) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: History) {
            binding.data = item
            binding.adapter = adapter
            adapter.submitList(item.skus!!)
            itemView.setOnClickListener { interaction!!.onItemProductSelected(layoutPosition) }
        }
    }

    interface Interaction {
        fun onItemProductSelected(position: Int)
    }
}