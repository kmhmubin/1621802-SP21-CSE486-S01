package com.example.nsucpcadmin.ui.activities.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.nsucpcadmin.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*

open class BaseActivity : AppCompatActivity() {

    /*
    * Show snack bar message function
     */

    fun showSnackBar(message: String, errorMessage: Boolean) {

        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            // for error
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this, R.color.snackBar_error)
            )
        } else {
            // for success
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this, R.color.snackBar_success)
            )
        }
        // show the snack bar
        snackBar.show()
    }
}