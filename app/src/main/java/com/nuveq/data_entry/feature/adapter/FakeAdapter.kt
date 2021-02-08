package com.nuveq.data_entry.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.nuveq.data_entry.R
import com.nuveq.data_entry.databinding.ItemHomeBinding
import com.nuveq.data_entry.model.FakeData

class FakeAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FakeData>() {

        override fun areItemsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
            return oldItem.name == newItem.name
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemHomeBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home,
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

    fun submitList(list: List<FakeData>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class VocationalTrainingHolder constructor(private val interaction: Interaction?, private val binding: ItemHomeBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FakeData) {
            binding.data = item

            binding.item.setOnClickListener {
                interaction!!.onItemSelected(layoutPosition, item)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, fakeData: FakeData)
    }
}