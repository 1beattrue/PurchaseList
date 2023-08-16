package edu.mirea.onebeattrue.purchaselist.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import edu.mirea.onebeattrue.purchaselist.R

/** fragment_shop_item */
@BindingAdapter("nameError")
fun bindNameError(textInputLayout: TextInputLayout, errorInputName: Boolean) {
    val message = if (errorInputName) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("countError")
fun bindCountError(textInputLayout: TextInputLayout, errorInputCount: Boolean) {
    val message = if (errorInputCount) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("textCount")
fun bindTextCount(textView: TextView, textCount: Int) {
    textView.text = if (textCount == 0) null else textCount.toString()
}