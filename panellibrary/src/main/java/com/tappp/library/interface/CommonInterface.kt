package com.tappp.library.`interface`

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.tappp.library.model.PassData
import com.tappp.library.view.fragment.WebFragment

/**
 * Created by Tejas A. Prajapati on 16/12/22.
 */
class CommonInterface(var context: Context?, private var callback: WebFragment.OnPanelDataListener?) {

    @JavascriptInterface
    fun showToast(toast: String?) {
        if (this.callback!=null){
            this.callback!!.showToast(toast)
        }
    }

    @JavascriptInterface
    fun getMessage(): String? {
        return PassData.message
    }

    @JavascriptInterface
    fun onClicked() {
        Toast.makeText(context, "Help button clicked", Toast.LENGTH_SHORT).show()
    }
}