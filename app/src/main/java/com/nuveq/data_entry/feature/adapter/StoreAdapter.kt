package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemShopBinding
import com.nuveq.data_entry.model.store.Store

class StoreAdapter (private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {

        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.storeId == newItem.storeId
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.storeId == newItem.storeId
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemShopBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_shop,
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

    fun submitList(list: List<Store>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class VocationalTrainingHolder constructor(private val interaction: Interaction?, private val binding: ItemShopBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Store) {
            binding.data = item

            itemView.setOnClickListener {
                interaction!!.onItemSelected(item)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(item: Store)
    }
}