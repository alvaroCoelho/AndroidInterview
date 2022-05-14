package com.eventbrite.androidchallenge.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(
        requireContext(),
        message,
        duration
    ).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun formatDateToString(date: Date): String{
    val pattern = "yyyy-MM-dd"
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(date)
}


