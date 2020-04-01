package com.pandey.shubham.githubtrends.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

object KeyboardUtils {

    @JvmStatic
    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
//            val imm =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}