package com.example.checkoutpointapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.checkoutpointapplication.R
import timber.log.Timber
import java.util.*

@BindingAdapter("background")
fun setBackground(textView: TextView,status:String){
    when(status.lowercase(Locale.getDefault())){
        "takeaway"->{
            textView.setBackgroundResource(R.drawable.curved_purple)
        }
        "walk-in"->{
            textView.setBackgroundResource(R.drawable.curved_maroon)
        }
        "dinein"->{
            textView.setBackgroundResource(R.drawable.curved_green)
        }
        else->{
            textView.setBackgroundResource(R.drawable.curved_yellow)
        }
    }
    Timber.d("statusValue $status->")
    textView.text=status.trim().lowercase(Locale.getDefault()). replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}