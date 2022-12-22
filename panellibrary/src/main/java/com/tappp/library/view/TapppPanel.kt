package com.tappp.library.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tappp.library.constant.Constants
import com.tappp.library.model.PanelSettingModel
import com.tappp.library.view.fragment.ImageFragment
import com.tappp.library.view.fragment.WebFragment
import org.json.JSONObject

/**
 * Created by Tejas A. Prajapati on 30/11/22.
 */
class TapppPanel : FrameLayout {

    private val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
    private var mFragment: Fragment = ImageFragment()
    private var supportFragmentManager: FragmentManager? = null
    private var tapppViewContainer: Int = 0
    private var fragmentInit: Boolean = false
    private var panelView: View? = null
    private lateinit var panelData: JSONObject
    private var callback: WebFragment.OnPanelDataListener? = null
    private var eventList : ArrayList<String> = arrayListOf()

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    /**
     *
     */
    fun init(panelData: JSONObject, panelSetting: PanelSettingModel) {
        panelDataListener()
        this.supportFragmentManager = panelSetting.supportManager as FragmentManager?
        this.tapppViewContainer = panelSetting.containerView as Int
        this.panelView = panelSetting.panelView

        this.panelData = panelData
        if (panelData.length() > 0) {
            val mViewEnum = panelData.get("DISPLAY_OPTION")
            if (mViewEnum.equals(Constants.LOCAL_WEB_VIEW)) {
                mFragment = WebFragment.newInstance(1, callback)
            } else if (mViewEnum.equals(Constants.S3_WEB_VIEW)) {
                mFragment = WebFragment.newInstance(2, callback)
            } else if (mViewEnum.equals(Constants.LIBRARY_CALENDER)) {
                mFragment = WebFragment.newInstance(3, callback)
            } else if (mViewEnum.equals(Constants.LIBRARY_PANEL)) {
                mFragment = WebFragment.newInstance(5, callback)
            }
        } else {
            mFragment = WebFragment.newInstance(5, callback)
        }

    }

    fun start() {
        if (supportFragmentManager != null) {
            if (fragmentInit){
                panelView!!.visibility = View.VISIBLE
            }else{
                fragmentInit = true
                supportFragmentManager!!
                    .beginTransaction()
                    .add(tapppViewContainer, mFragment, TAG_FLUTTER_FRAGMENT)
                    .commit()
            }
        }
    }

    fun stop() {
        if (supportFragmentManager != null) {
            supportFragmentManager!!.beginTransaction().remove(mFragment).commit()
        }
    }
    
    fun hide() {
        if (supportFragmentManager != null) {
            panelView!!.visibility = View.GONE
        }
    }

    private fun panelDataListener() {
        callback = object: WebFragment.OnPanelDataListener{
            override fun showToast(toast: String?) {
                super.showToast(toast)
                if (eventList.contains(Constants.EVENT_RECEIVE_MESSAGE)){
                    //Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
                    mPanelDataListener!!.onDataReceived(Constants.EVENT_RECEIVE_MESSAGE, toast)
                }
            }
        }
    }

    fun subscribe(event: String, mPanelDataListener: DataListener?) {
        eventList.add(event)
        this.mPanelDataListener = mPanelDataListener
    }

    fun unsubscribe(event: String) {
        eventList.remove(event)
    }

    interface DataListener {
        fun onDataReceived(event: String?, result: Any?)
    }

    private var mPanelDataListener: DataListener? = null
}