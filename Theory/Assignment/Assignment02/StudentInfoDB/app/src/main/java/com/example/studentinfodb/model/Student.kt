package com.example.studentinfodb.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Student(@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int) {
}