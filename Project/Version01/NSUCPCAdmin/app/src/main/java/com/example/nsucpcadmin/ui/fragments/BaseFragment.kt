package com.example.nsucpcadmin.ui.fragments

import android.app.Dialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.nsucpcadmin.R
import com.google.android.material.snackbar.Snackbar


open class BaseFragment : Fragment() {

    private lateinit var mProgressDialog: Dialog

    /*
    * show progressbar indicator
     */
    fun showProgressBar() {
        mProgressDialog = Dialog(requireActivity())
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

        val snackBar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content), message,
            Snackbar.LENGTH_LONG
        )
        val snackBarView = snackBar.view

        if (errorMessage) {
            // for error
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(requireActivity(), R.color.snackBar_error)
            )
        } else {
            // for success
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(requireActivity(), R.color.snackBar_success)
            )
        }
        // show the snack bar
        snackBar.show()
    }


}