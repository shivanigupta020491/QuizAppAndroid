package com.example.quiz


import androidx.fragment.app.Fragment
import org.gripp.android.gripp.fragments.SafetyFragment.SafetyFragment


class SafetyActivity : BaseFragmentActivity() {
    var safequiz:ArrayList<SafetyQuizClass>?=null

    override fun createFragment(): Fragment {
        return SafetyFragment.newInstance()
    }
}