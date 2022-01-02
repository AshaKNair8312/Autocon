package com.example.checkoutpointapplication.views.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.checkoutpointapplication.R
import com.example.checkoutpointapplication.databinding.ActivityMainBinding
import com.example.checkoutpointapplication.views.history.HistoryActivity
import com.example.checkoutpointapplication.views.print.PrintActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getClicked().observe(this,{
            if(it!=0) {
                var intent = Intent()
                when (it) {
                    1 ->
                        intent = Intent(this, HistoryActivity::class.java)
                    2 ->
                        intent = Intent(this, PrintActivity::class.java)
                }
                startActivity(intent)
            }
        })
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProvider(
                this
            )[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}