package com.koushikjoshi.glaukous_androidtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private lateinit var BottomNavigationView: BottomNavigationView
    private lateinit var frameLayout: FrameLayout
    private val homeFragment = HomeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        calling replaceFragment() function in the beginning to ensure that home screen is on by default
        replaceFragment(homeFragment)

//        initializing bottomNavigationView
        BottomNavigationView = findViewById(R.id.bottonNavBar)

//        ItemSelectedListener to perform specific actions when an item is selected
        BottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
//                add homeFragment to FrameLayout when home icon is pressed
                R.id.home -> replaceFragment(homeFragment)

//                For the rest of the icons, just show a toast
                R.id.dataset -> Toast.makeText(this, "Dataset Fragment", Toast.LENGTH_SHORT).show()
                R.id.barcode -> Toast.makeText(this, "Scan Barcode", Toast.LENGTH_SHORT).show()
                R.id.info -> Toast.makeText(this, "Info Fragment", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "Profile Fragment", Toast.LENGTH_SHORT).show()

            }
            true
        }

    }

//  Functiion to add a fragment to a framelayout
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }

    // Implement press back twice to exit
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}