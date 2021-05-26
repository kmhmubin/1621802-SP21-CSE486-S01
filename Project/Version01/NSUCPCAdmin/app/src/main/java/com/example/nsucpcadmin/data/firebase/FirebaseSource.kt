package com.example.nsucpcadmin.data.firebase

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.nsucpcadmin.data.model.AdminUser
import com.example.nsucpcadmin.ui.activities.auth.LoginActivity
import com.example.nsucpcadmin.ui.activities.auth.SignupActivity
import com.example.nsucpcadmin.ui.activities.editprofile.EditProfileActivity
import com.example.nsucpcadmin.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirebaseSource {

    // access the Firestore database
    private val mFireStore = FirebaseFirestore.getInstance()

    /*
    * Function to make entry of the register user in the Firestore database
     */

    fun registerUser(activity: SignupActivity, userInfo: AdminUser) {
        // collection name
        mFireStore.collection(Constants.USER).document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // function of base activity for transferring the result to it
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                // hide the progress bar
                activity.hideProgressBar()
                // save the error in Crushlytics
                FirebaseCrashlytics.getInstance().log("Registration Data not save")
                FirebaseCrashlytics.getInstance()
                    .setCustomKey("Registration Failed", activity.javaClass.simpleName)
                FirebaseCrashlytics.getInstance().recordException(e)
            }
    }

    /*
    * Function to get the current user
     */

    fun getCurrentUserID(): String {
        // Instance of currentUser using Firebase Auth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // variable to assign the currentUserID if it is not null
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    /*
    * Function for get logged user details from Firestore databse
     */

    fun getUserDetails(activity: Activity) {
        // pass the collection name, to get data from
        mFireStore.collection(Constants.USER)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                // print in the log
                Log.i(activity.javaClass.simpleName, document.toString())

                // receive the document snapshot and convert in user data model object
                val user = document.toObject(AdminUser::class.java)!!

                // save in shared prefs
                val sharedPreferences = activity.getSharedPreferences(
                    Constants.NSU_CPC_ADMIN_PREFERENCES,
                    Context.MODE_PRIVATE
                )

                // Instance of the editor which is help us to edit
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_USERNAME, "${user.name}"
                )
                editor.apply()

                when (activity) {
                    is LoginActivity -> {
                        // call a function of base activity for transferring tha result
                        activity.userLoggedInSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is LoginActivity -> {
                        activity.hideProgressBar()
                    }
                }
                // save the error logs
                FirebaseCrashlytics.getInstance().log("Error while getting user details")
                FirebaseCrashlytics.getInstance().setCustomKey("Login Error", e.toString())
                FirebaseCrashlytics.getInstance().recordException(e)
            }
    }


    /*
    * function for update the user details
     */

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        // collection name
        mFireStore.collection(Constants.USER)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is EditProfileActivity -> {
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener {
                when (activity) {
                    is EditProfileActivity -> {
                        activity.hideProgressBar()
                    }
                }

                FirebaseCrashlytics.getInstance().log("Error while updating the user details")

            }
    }

}