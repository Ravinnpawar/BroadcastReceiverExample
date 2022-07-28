package com.bajaj.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
const val TAG="BROADCASTEVENT"
class MainActivity : AppCompatActivity() {
    lateinit var wifiSwitch: Switch
    lateinit var wifiManager: WifiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonIm: Button =findViewById(R.id.button)
        title = "KotlinApp"
        wifiSwitch = findViewById(R.id.wifiSwitch)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            /*if (isChecked) {
                wifiManager.isWifiEnabled = true
                wifiSwitch.text = "WiFi is ON"
            } else {
                wifiManager.isWifiEnabled = false
                wifiSwitch.text = "WiFi is OFF"
            }*/
        }

        buttonIm.setOnClickListener(View.OnClickListener {
            intent = Intent(this, SimpleActivity::class.java)
            intent.putExtra("course", "Android Training for Bajaj")
            startActivity(intent)
        })
    }
    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiStateReceiver, intentFilter)
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver)
    }
    private val wifiStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN)) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    wifiSwitch.isChecked = true
                    wifiSwitch.text = "WiFi is ON"
                    Toast.makeText(this@MainActivity, "Wifi is On", Toast.LENGTH_SHORT).show()

                    Log.i(TAG,"Wifi is Enabled")
                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    wifiSwitch.isChecked = false
                    wifiSwitch.text = "WiFi is OFF"
                    Toast.makeText(this@MainActivity, "Wifi is Off", Toast.LENGTH_SHORT).show()
                    Log.i(TAG,"Wifi is Disabled")
                }
            }
        }
    }
}