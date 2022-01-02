package com.example.checkoutpointapplication.views.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.checkoutpointapplication.databinding.HistoryItemBinding
import com.example.checkoutpointapplication.dataprovider.HistoryResponse
import timber.log.Timber

class HistoryAdapter(private val clickListener:HistoryClickListener) : PagingDataAdapter<HistoryResponse, HistoryAdapter.ViewHolder>(DataDifferntiator) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it,clickListener) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(
            item: HistoryResponse,
            clickListener: HistoryClickListener,

            ) {
            Timber.d("itemToBind $item")
            binding.viewModel=item
            binding.clickListener=clickListener

        }
    }


    object DataDifferntiator : DiffUtil.ItemCallback<HistoryResponse>() {

        override fun areItemsTheSame(oldItem: HistoryResponse, newItem: HistoryResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HistoryResponse, newItem: HistoryResponse): Boolean {
            return oldItem == newItem
        }
    }

}
class HistoryClickListener(val clickListener: (data: HistoryResponse) -> Unit) {
    fun onClick(data: HistoryResponse) {
        clickListener(data)
    }
}