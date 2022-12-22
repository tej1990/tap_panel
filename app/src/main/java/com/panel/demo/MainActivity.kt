package com.panel.demo

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.panel.demo.databinding.ActivityMainBinding
import com.tappp.library.constant.Constants
import com.tappp.library.model.PanelSettingModel
import com.tappp.library.view.TapppPanel
import org.json.JSONObject

class MainActivity : AppCompatActivity(), TapppPanel.DataListener{
    private var binding: ActivityMainBinding? = null
    private lateinit var tapppPanel: TapppPanel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //panelData, panelSetting
        val panelSetting=PanelSettingModel()
        panelSetting.supportManager = supportFragmentManager
        panelSetting.containerView=R.id.fragment_container
        panelSetting.panelView= binding!!.fragmentContainer

        val panelData = JSONObject()
        panelData.put("DISPLAY_OPTION", Constants.LIBRARY_PANEL)
        panelData.put("bookId", "1348923539")
        panelData.put("gameId", "900585")
        panelData.put("locationCheck", true)
        panelData.put("language", "en")

        tapppPanel = TapppPanel(this)
        tapppPanel.init(panelData, panelSetting)
        tapppPanel.start()
        tapppPanel.subscribe(Constants.EVENT_RECEIVE_MESSAGE, this)

        binding!!.btnSendDataToFlutter.setOnClickListener {
            tapppPanel.hide()
            //tapppPanel.stop()
            //PassData.message = "From Native"
            //tapppPanel.unsubscribe(Constants.EVENT_RECEIVE_MESSAGE)
        }
    }

    override fun onDataReceived(event: String?, result: Any?) {
        showToastMessage(result as String)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}