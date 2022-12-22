package com.tappp.library.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.panel.library.R

class ImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_main,
            container, false)
        //Log.e("JS : ", CallJavaScript.callFunction(this).toString());
        //Toast.makeText(this, CallJavaScript.callFunction(this).toString(), Toast.LENGTH_SHORT)
    }
}