package com.example.quiz


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by harshgupta on 02/03/17.
 */

abstract class BaseFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_base)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_fragmentContainer)


        if (fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.activity_fragment_base_fragmentContainer, fragment)
                    .commit()
        }
    }

}

