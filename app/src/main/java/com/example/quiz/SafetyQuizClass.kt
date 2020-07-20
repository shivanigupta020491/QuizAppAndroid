package com.example.quiz

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by harshgupta on 28/12/17.
 */
@Parcelize
data class SafetyQuizClass(
    var question: String, var option_a: String,
    var option_b: String, var option_c: String, var option_d: String, var choice: String):Parcelable {

}
