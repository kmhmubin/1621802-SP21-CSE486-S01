package com.example.nsucpcadmin.ui.activities.editprofile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nsucpcadmin.MainActivity
import com.example.nsucpcadmin.data.firebase.FirebaseSource
import com.example.nsucpcadmin.data.model.AdminUser
import com.example.nsucpcadmin.databinding.ActivityEditProfileBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.example.nsucpcadmin.utils.Constants
import com.example.nsucpcadmin.utils.GlideLoader
import java.io.IOException

class EditProfileActivity : BaseActivity(), View.OnClickListener {

    // admin user data class
    private lateinit var mUserDetails: AdminUser

    // variable for URI of the selected images
    private var mSelectedImageFileUri: Uri? = null
    private var mUserProfileImageURL: String = ""

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root

        // get user details from intent

        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // if the profile incomplete
        if (mUserDetails.profileCompleted == 0) {
            // show values in the field

            binding.editProfileNameEditText.isEnabled = false
            binding.editProfileNameEditText.setText(mUserDetails.name)

            binding.editProfileEmailEditText.isEnabled = false
            binding.editProfileEmailEditText.setText(mUserDetails.email)

            binding.editProfileIDEditText.isEnabled = false
            binding.editProfileIDEditText.setText(mUserDetails.nsu_id)
        } else {
            // Load the image using GlideLoader class
            GlideLoader(this).loadUserPicture(mUserDetails.image, binding.editProfileUserPhoto)

            binding.editProfileNameEditText.setText(mUserDetails.name)
            binding.editProfileEmailEditText.isEnabled = false
            binding.editProfileEmailEditText.setText(mUserDetails.email)

            if (mUserDetails.mobile != 0L) {
                binding.editProfilePhoneEditText.setText(mUserDetails.mobile.toString())
            }
            if (mUserDetails.gender == Constants.MALE) {
                binding.radioButtonMale.isChecked = true
            } else {
                binding.radioButtonFemale.isChecked = true
            }

        }


        // user photo click event
        binding.editProfileUserPhoto.setOnClickListener(this)

        // save button click event
        binding.editProfileSaveButton.setOnClickListener(this)

        setContentView(view)
    }

    override fun onClick(view: View) {
        if (view != null) {
            when (view) {
                // user profile image
                binding.editProfileUserPhoto -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this)
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

                // save button
                binding.editProfileSaveButton -> {
                    if (validateUserProfileDetails()) {

                        // show progress bar
                        showProgressBar()

                        if (mSelectedImageFileUri != null) {
                            FirebaseSource().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                        } else {
                            updateUserProfileDetails()
                        }
                    }
                }
            }
        }
    }


    /*
    * runtime permission check function
     */

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            // if permission granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this)
            } else {
                // display toast message
                Toast.makeText(this, "Permission Denided", Toast.LENGTH_LONG).show()
            }
        }
    }

    /*
    * function for image load from storage
     */

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        // The uri of selected image from phone storage.
                        mSelectedImageFileUri = data.data!!

                        // Use GlideLoader
                        GlideLoader(this).loadUserPicture(
                            mSelectedImageFileUri!!, binding.editProfileUserPhoto
                        )

                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this,
                            "Image selection failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Request Cancelled", "Image selection cancelled")
        }
    }

    /*
    *  function for validate the inputs
     */
    private fun validateUserProfileDetails(): Boolean {
        return when {
            binding.editProfilePhoneEditText.text.toString().trim().isEmpty() -> {
                binding.editProfilePhoneTextLayout.error = "Please enter valid phone number"
                false
            }
            binding.editProfileAddressEditText.text.toString().trim().isEmpty() -> {
                binding.editProfileAddressTextLayout.error = "Please enter your address"
                false
            }
            else -> {
                true
            }
        }
    }

    /*
    * function to update the user profile details to the firestore
     */

    private fun updateUserProfileDetails() {
        // create a hashmap of user for upload
        val userHashMap = HashMap<String, Any>()

        // if user edit profile details instead of complete profile
        val name: String = binding.editProfileNameEditText.text.toString().trim()
        val phone: String = binding.editProfilePhoneEditText.text.toString().trim()

        val gender = if (binding.radioButtonMale.isChecked) {
            Constants.MALE
        } else {
            Constants.FEMALE
        }

        val presentAddress: String =
            binding.editProfileAddressEditText.text.toString().trim()

        if (name != mUserDetails.name) {
            userHashMap[Constants.NAME] = name
        }

        if (phone.isNotEmpty()) {
            userHashMap[Constants.MOBILE] = phone.toLong()
        }

        userHashMap[Constants.GENDER] = gender

        if (presentAddress.isNotEmpty()) {
            userHashMap[Constants.PRESENT_ADDRESS] = presentAddress
        }

        if (mUserProfileImageURL.isNotEmpty()) {
            userHashMap[Constants.IMAGE] = mUserProfileImageURL
        }

        if (mUserDetails.profileCompleted == 0) {
            userHashMap[Constants.COMPLETE_PROFILE] = 1
        }

        // call the update user function of FirebaseSource class to make entry to database
        FirebaseSource().updateUserProfileData(this, userHashMap)


    }

    /*
    * function to notify the success result
     */
    fun userProfileUpdateSuccess() {
        hideProgressBar()

        showSnackBar("Profile Update Successful", false)

        // redirect to main screen
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /*
    * function to notify the success result of image upload to the cloud
     */

    fun imageUploadSuccess(imageURL: String) {
        mUserProfileImageURL = imageURL
        updateUserProfileDetails()
    }


}