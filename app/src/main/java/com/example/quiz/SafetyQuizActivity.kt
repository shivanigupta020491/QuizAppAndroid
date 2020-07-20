package com.example.quiz


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.quiz.Fragment.SafetyOneFragment



class SafetyQuizActivity : AppCompatActivity(), SafetyOneFragment.SafetyOneFragmentChangeListener {

    var questionlist:ArrayList<SafetyQuizClass>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_safety_file)

        val iab = intent.getIntExtra("quizquestion", 0)
        // val args:Bundle=intent.getBundleExtra("Bundle")
        questionlist=intent.getParcelableArrayListExtra<SafetyQuizClass>("quizquestionlist")
        val fragment=SafetyOneFragment()
        supportFragmentManager.beginTransaction().replace(R.id.safety_id,fragment).setTransition(FragmentTransaction.TRANSIT_NONE).addToBackStack(null).commit()
//        val frag = SafetyOneFragment()
//        val transaction = fragmentManager.beginTransaction()
//        frag.id = iab
//        transaction.replace(R.id.safety_id, frag)
//        transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
//        transaction.addToBackStack(null)
//        transaction.commit()

    }

    override fun itemClicked(id: Int) {
        val fragment=SafetyOneFragment()
        fragment.idQuiz=id
        fragment.questionlist=questionlist!!.get(id)
        supportFragmentManager.beginTransaction().replace(R.id.safety_id,fragment).setTransition(FragmentTransaction.TRANSIT_NONE).addToBackStack(null).commit()
        Log.d("working", "oh yea "+questionlist!!.get(id))
    }

    override fun takedataback(id:Int): SafetyQuizClass {
        return questionlist!!.get(id)
    }


}
