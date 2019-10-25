package com.zubair.geniusplaza.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zubair.geniusplaza.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().
                add(R.id.main_activity_container, ListFragment()).
                commit()
        }
    }
}
