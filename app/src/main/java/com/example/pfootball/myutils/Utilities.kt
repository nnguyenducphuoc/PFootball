package com.example.pfootball.myutils

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utilities {
    fun showToast(context: Context?, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLog(t: String) {
        Log.d("bug", t)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertISO8601ToLocalDate(utcDateString: String): String? {
        val iso8601DateTime = ZonedDateTime.parse(utcDateString)

        val utc7TimeZone = iso8601DateTime.withZoneSameInstant(java.time.ZoneOffset.ofHours(7))
        val formatter = DateTimeFormatter.ofPattern("MMM, dd")

        return utc7TimeZone.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertISO8601ToLocalTime(utcDateString: String): String? {
        val iso8601DateTime = ZonedDateTime.parse(utcDateString)

        val utc7TimeZone = iso8601DateTime.withZoneSameInstant(java.time.ZoneOffset.ofHours(7))
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return utc7TimeZone.format(formatter)
    }

    fun extractNumber(input: String): String {
        val regex = Regex("\\d+")
        val matchResult = regex.find(input)
        return matchResult?.value ?: ""
    }

    fun incrementSeason(input: String): String {
        val regex = Regex("(\\D+)(\\d+)")
        val matchResult = regex.find(input)

        return if (matchResult != null) {
            val prefix = matchResult.groupValues[1]
            val currentNumber = matchResult.groupValues[2].toInt()
            val incrementedNumber = currentNumber + 1
            "$prefix$incrementedNumber"
        } else {
            return ""
        }
    }
}