package com.harsh.databindingwithrecyclerview.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Return formatted string date
 * sample input 2021-02-12T04:57:51+00:00
 */
fun String.getFormattedDate(): String {
    val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val format2 = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
    val date: Date = format1.parse(this)!!
    return format2.format(date)
}