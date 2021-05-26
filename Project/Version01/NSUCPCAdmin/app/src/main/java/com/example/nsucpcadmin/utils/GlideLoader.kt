package com.example.nsucpcadmin.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.nsucpcadmin.R
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.io.IOException

class GlideLoader(val context: Context) {

    // function to load image from url for the user profile image
    fun loadUserPicture(imageURI: Any, imageView: ImageView) {
        try {
            // load the user image in the Imageview
            Glide.with(context)
                // url of the image
                .load(Uri.parse(imageURI.toString()))
                // scale type of the image
                .centerCrop()
                //default placeholder if failed
                .placeholder(R.drawable.ic_profile_avater)
                //the view in which the image will be loaded
                .into(imageView)
        } catch (e: IOException) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().log("Error to load image")
            FirebaseCrashlytics.getInstance().setCustomKey("Image Error", e.toString())
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }
}