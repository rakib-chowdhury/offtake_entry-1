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
}