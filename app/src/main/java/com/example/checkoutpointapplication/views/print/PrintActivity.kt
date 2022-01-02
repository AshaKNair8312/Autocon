package com.example.checkoutpointapplication.views.print

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.checkoutpointapplication.R
import com.example.checkoutpointapplication.databinding.ActivityPrintBinding


class PrintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrintBinding
    private lateinit var viewModel: PrintViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }


    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_print)
        viewModel =
            ViewModelProvider(
                this,
                PrintViewModelFactory(this)
            )[PrintViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}