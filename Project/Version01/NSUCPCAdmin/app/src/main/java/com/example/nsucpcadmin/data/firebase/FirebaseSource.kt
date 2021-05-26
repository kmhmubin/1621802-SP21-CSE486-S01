package com.example.nsucpcadmin.data.firebase

import androidx.compose.ui.unit.Constraints
import com.example.nsucpcadmin.data.model.AdminUser
import com.example.nsucpcadmin.ui.activities.auth.SignupActivity
import com.example.nsucpcadmin.utils.Constants
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
}