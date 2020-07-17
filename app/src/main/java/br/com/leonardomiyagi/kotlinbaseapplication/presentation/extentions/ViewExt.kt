package br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions

import android.view.View

/**
 * Created by lmiyagi on 19/12/18.
 */
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}