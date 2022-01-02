package com.example.checkoutpointapplication.views.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.checkoutpointapplication.R
import com.example.checkoutpointapplication.databinding.ActivityHistoryBinding
import com.example.checkoutpointapplication.dataprovider.HistoryResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var historyAdapter:HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupList()
        setupView()
    }

    private fun setupView() {

        lifecycleScope.launch {
            viewModel.listData.collectLatest { pagingData ->
                    //viewModel.setProgress(false)
                    historyAdapter.submitData(pagingData as PagingData<HistoryResponse>)
            }
        }
    }

    private fun setupList() {
        historyAdapter = HistoryAdapter(HistoryClickListener {
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("detailData", it)
//            startActivity(intent)
        })
        binding.historyRecycler.adapter = historyAdapter
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        viewModel =
            ViewModelProvider(
                this,
                HistoryViewModelFactory(this)
            )[HistoryViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}