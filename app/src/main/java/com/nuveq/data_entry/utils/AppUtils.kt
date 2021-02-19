package com.nuveq.data_entry.utils

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    fun hideSoftInput(activity: Activity) {
        val view = activity.findViewById<View>(R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    ?: return
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun message(
            view: View?,
            msg: String?
    ) {
        try {
            if (view == null) return
            val snack = Snackbar.make(view, msg!!, Snackbar.LENGTH_SHORT)
            val snackBarView = snack.view
            snackBarView.setBackgroundColor(Color.RED)
            /*    val snackBarText = snackBarView.findViewById<TextView>(R.id.accessibilityActionContextClick)
                snackBarText.setTextColor(Color.WHITE)*/
            snack.show()
        } catch (e: Exception) {
            return
        }
    }


    fun successMessage(
            view: View?,
            msg: String?
    ) {
        try {
            if (view == null) return
            val snack = Snackbar.make(view, msg!!, Snackbar.LENGTH_SHORT)
            val snackBarView = snack.view
            snackBarView.setBackgroundColor(Color.BLACK)
            /*    val snackBarText = snackBarView.findViewById<TextView>(R.id.accessibilityActionContextClick)
                snackBarText.setTextColor(Color.WHITE)*/
            snack.show()
        } catch (e: Exception) {
            return
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context).load(imageUrl).into(view)
    }

    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        return formatter.format(date)
    }

    fun getCurrentDateInEnglish(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
        val date = Date()
        return formatter.format(date)
    }


    fun changeDateFormat(date: String): String {
        val OLD_FORMAT = "yy/mm/dd"
        val NEW_FORMAT = "dd/MM/yyyy"

// August 12, 2010

// August 12, 2010
        val oldDateString = date

        val sdf = SimpleDateFormat(OLD_FORMAT)
        val d = sdf.parse(oldDateString)
        sdf.applyPattern(NEW_FORMAT)
        return sdf.format(d)
    }

    fun changeDateFormatIOnEnglish(date: String): String {
        val OLD_FORMAT = "yy/mm/dd"
        val NEW_FORMAT = "dd/MM/yyyy"

// August 12, 2010

// August 12, 2010
        val oldDateString = date

        val sdf = SimpleDateFormat(OLD_FORMAT,Locale.ENGLISH)
        val d = sdf.parse(oldDateString)
        sdf.applyPattern(NEW_FORMAT)
        return sdf.format(d)
    }

    fun String.englishNumberChangeToBanglaNumber(): String {
        val hash = HashMap<Int, String>()
        hash[0] = "০"
        hash[1] = "১"
        hash[2] = "২"
        hash[3] = "৩"
        hash[4] = "৪"
        hash[5] = "৫"
        hash[6] = "৬"
        hash[7] = "৭"
        hash[8] = "৮"
        hash[9] = "৯"
/*
        hash[০] = "0"
        hash[১] = "1"
        hash[২] = "2"
        hash[৩] = "3"
        hash[৪] = "4"
        hash[৫] = "5"
        hash[৬] = "6"
        hash[৭] = "7"
        hash[৮] = "8"
        hash[৯] = "9"*/
        var banglaDate = ""
        this.forEach {
            try {
                val digit = it.toString().toInt()
                banglaDate += hash[digit]
            } catch (e: Exception) {
                banglaDate += it
            }
        }
        return banglaDate

    }

}