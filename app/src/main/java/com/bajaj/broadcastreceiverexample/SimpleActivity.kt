package com.bajaj.broadcastreceiverexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val buttonIm:Button=findViewById(R.id.buttonimp)
        val buttonExp:Button=findViewById(R.id.buttonex)

        var msg=intent.getStringExtra("course")
        Log.i(TAG,"Value from MainActivity"+msg)

        buttonIm.setOnClickListener(View.OnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("course", "Android Training for Bajaj")
            Log.i(TAG,"Calling MainActivity Again from Second")
            startActivity(intent)
        })
        buttonExp.setOnClickListener(View.OnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.google.com/"))
            Log.i(TAG,"Calling Browser Implicitely.")
            startActivity(intent)
        })
    }
}