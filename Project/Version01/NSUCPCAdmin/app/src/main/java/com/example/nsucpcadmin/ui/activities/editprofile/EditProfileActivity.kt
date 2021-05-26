package com.example.nsucpcadmin.ui.activities.editprofile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nsucpcadmin.data.model.AdminUser
import com.example.nsucpcadmin.databinding.ActivityEditProfileBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.example.nsucpcadmin.utils.Constants
import java.io.IOException

class EditProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var mUserDetails: AdminUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root

        // get user details from intent

        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // show values in the field

        binding.editProfileNameEditText.isEnabled = false
        binding.editProfileNameEditText.setText(mUserDetails.name)

        binding.editProfileEmailEditText.isEnabled = false
        binding.editProfileEmailEditText.setText(mUserDetails.email)

        binding.editProfileIDEditText.isEnabled = false
        binding.editProfileIDEditText.setText(mUserDetails.nsu_id)

        // user photo click event
        binding.editProfileUserPhoto.setOnClickListener(this)

        setContentView(view)
    }

    override fun onClick(view: View) {
        if (view != null) {
            when (view) {
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
            }
        }
    }


    /*
    * runtime permission
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
    *
     */

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        // The uri of selected image from phone storage.
                        val selectedImageFileUri = data.data!!
                        binding.editProfileUserPhoto.setImageURI(selectedImageFileUri)

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
        }
    }
}