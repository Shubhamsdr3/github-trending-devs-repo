package com.pandey.shubham.githubtrends.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object KeyboardUtils {

    @JvmStatic
    fun showSoftKeyboard(activity: Activity?, view: View) {
        if (view.requestFocus()) {
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    @JvmStatic
    fun hideKeyboard(activity: Activity?, view: View) {
        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

}