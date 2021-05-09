package com.example.studentinfofbdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * add [navController] and Change the name of the action bar
         */
        val navController = this.findNavController(R.id.fragmentContainerView)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /*
    * Navigation back button
     */

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp()
    }
}