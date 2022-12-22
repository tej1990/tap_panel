package com.tappp.library.view.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.panel.library.R
import com.tappp.library.`interface`.CommonInterface


class WebFragment() : Fragment() {

    var mWebView: WebView? = null
    var mValue: Int? =null
    val WEB_VALUE = "WEB_VALUE"

    companion object {
        private var callback: OnPanelDataListener? = null
        const val WEB_VALUE = "WEB_VALUE"
        fun newInstance(mValue: Int, callback: OnPanelDataListener?): WebFragment {
            this.callback = callback
            val fragment = WebFragment()
            val bundle = Bundle().apply {
                putInt(WEB_VALUE, mValue)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater?.inflate(R.layout.fragment_web, container, false)
        mValue = arguments?.getInt(WEB_VALUE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mWebView = view?.findViewById(R.id.webView)
        mWebView!!.setBackgroundColor(Color.TRANSPARENT)
        loadUrls()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrls() {
        mWebView!!.settings.setJavaScriptEnabled(true)
        mWebView!!.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        mWebView!!.addJavascriptInterface(CommonInterface(context, callback), "Android")
        //mWebView!!.addJavascriptInterface(callback!!, "Android")
        var file = "file:android_asset/sample.html"
        /*if (mValue==2){//S3_WEB_VIEW
           file = "http://tappp-web-build.s3-website.us-east-2.amazonaws.com/"
        }else if(mValue==3){//LOCAL_VANILA_CALENDER
            file = "file:android_asset/calender/index.html"
        }else if(mValue==5){//LOCAL_S3_PANEL
            file = "file:android_asset/panel/index.html"
        }*/
        mWebView!!.loadUrl(file)
    }

    interface OnPanelDataListener{
        fun showToast(toast: String?) {}
    }

}