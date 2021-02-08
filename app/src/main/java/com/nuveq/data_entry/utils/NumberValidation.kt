package com.nuveq.data_entry.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.regex.Matcher
import java.util.regex.Pattern

object NumberValidation {

    open fun checkMobileNumber(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s!!.length < 10) {
                    editText.setError("")
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.equals("")) {
                    return
                }
                if (s.length == 11) {
                    if (!isValid(s)) {
                        editText.setError("Invalid Number")
                    }
                } else if (s!!.length > 11) {
                    editText.getText().delete(s!!.length - 1, s!!.length);
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    open fun isValid(s: CharSequence?): Boolean {

        val PHONE_PATTERN = "^(013|014|105|016|017|018|019)\\d{8}$"

        val pattern: Pattern = Pattern.compile(PHONE_PATTERN)
        val matcher: Matcher = pattern.matcher(s)
        return matcher.matches()
    }
}