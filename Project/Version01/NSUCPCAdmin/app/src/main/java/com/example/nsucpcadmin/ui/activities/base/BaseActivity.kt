package com.example.nsucpcadmin.ui.activities.base

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nsucpcadmin.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var mProgressDialog: Dialog


    /*
    * show progressbar indicator
     */
    fun showProgressBar() {
        mProgressDialog = Dialog(this)
        /*
        * this progress bar will be inflated, adding all top level views to the screen
         */
        mProgressDialog.setContentView(R.layout.dialog_progress_bar)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        // show the dialog
        mProgressDialog.show()
    }

    fun hideProgressBar() {
        mProgressDialog.dismiss()
    }

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

    /*
    * function to implement the double back press feature to exit the app
     */

    fun doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please Click back again to exit", Toast.LENGTH_LONG).show()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                doubleBackToExitPressedOnce = false
            }, 2000
        )
    }

}