package com.example.quiz.Fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quiz.R
import com.example.quiz.SafetyQuizActivity
import com.example.quiz.SafetyQuizClass

import com.example.quiz.utils.Constants.USER_INFO_PREFERENCE
import com.example.quiz.utils.Constants.safetyanswerString
import com.example.quiz.utils.Constants.setAnswer
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by harshgupta on 30/12/17.
 */
class SafetyOneFragment : Fragment() {
    var idQuiz = 0
    private var id_one = 0
    var answer: RadioGroup? = null
    var option1: AppCompatRadioButton? = null
    var option2: AppCompatRadioButton? = null
    var option3: AppCompatRadioButton? = null
    var option4: AppCompatRadioButton? = null
    var button: AppCompatButton? = null
    var alertDialog: AlertDialog? = null
    var builder: AlertDialog.Builder? = null
    var question: TextView? = null
    var questionlist: SafetyQuizClass?=null
    protected var mSharedPreferences: SharedPreferences? = null

    interface SafetyOneFragmentChangeListener {
        fun itemClicked(id: Int)
        fun takedataback(id: Int):SafetyQuizClass
    }

    private var listener: SafetyOneFragmentChangeListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =
            inflater.inflate(R.layout.fragment_safety_layout, container, false)
        mSharedPreferences = activity!!.getSharedPreferences(
            USER_INFO_PREFERENCE,
            Context.MODE_PRIVATE
        )
        builder = AlertDialog.Builder(activity)
        //listener!!.itemClicked(0)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            question = view.findViewById(R.id.question_id)
            answer = view.findViewById(R.id.radioGroup_id)
            button = view.findViewById(R.id.enter_btn_id)
            option1 = view.findViewById(R.id.radioGroup1_id)
            option2 = view.findViewById(R.id.radioGroup2_id)
            option3 = view.findViewById(R.id.radioGroup3_id)
            option4 = view.findViewById(R.id.radioGroup4_id)
            if(questionlist==null){
                questionlist= listener!!.takedataback(0)
                Log.d("check",questionlist!!.question)
            }else{
                //questionlist= listener!!.takedataback(0)
                Log.d("check","wrong")
                Log.d("check",questionlist.toString())
            }
            //final SafetyQuizClass safetyQuizClass= SafetyQuizClass.Companion.getSafetyQuizClass()[(int) id];
            //val safetyQuizClass = safetyQuizClass!!.get(idQuiz)
            //final SafetyQuizClass safetyQuizClass = SafetyQuizClass
            question!!.setText(questionlist!!.question)
            option1!!.setText(questionlist!!.option_a)
            option2!!.setText(questionlist!!.option_b)
            option3!!.setText(questionlist!!.option_c)
            option4!!.setText(questionlist!!.option_d)
            answer!!.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                id_one = checkedId
                button!!.setEnabled(true)
            })
            button!!.setOnClickListener(View.OnClickListener {
                when (id_one) {
                    R.id.radioGroup1_id -> {
                        if ("a".equals(questionlist!!.choice.trim())) {
                            safetydialogbox(true, "awesome")
                        } else {
                            safetydialogbox(false, questionlist!!.choice)
                        }
                    }
                    R.id.radioGroup2_id -> if ("b".equals(questionlist!!.choice.trim())) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, questionlist!!.choice)
                    }
                    R.id.radioGroup3_id -> if ("c".equals(questionlist!!.choice.trim())) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, questionlist!!.choice)
                    }
                    R.id.radioGroup4_id -> if ("d".equals(questionlist!!.choice.trim())) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, questionlist!!.choice)
                    }
                    else -> safetydialogbox(false, questionlist!!.choice.trim())
                }
            })
        }
    }



    fun safetydialogbox(a: Boolean, b: String) {
        Log.d("working", "yes i ama $a")
        builder!!.setTitle("Safety Quiz")
        mSharedPreferences!!.edit().putBoolean(setAnswer, true)
            .commit()
        if (a) {
            builder!!.setMessage("Great your answer is correct")
            val ba =
                mSharedPreferences!!.getInt(safetyanswerString, 0)
            mSharedPreferences!!.edit()
                .putInt(safetyanswerString, ba + 1).commit()
        } else {
            builder!!.setMessage("The correct answer is $b")
        }
        builder!!.setPositiveButton(
            "Okay"
        ) { dialog, which ->
            val bx = idQuiz
            Log.d("working", "yes i amb $bx")
            if (bx + 1 < 5) {
                Log.d("working", "yes i amd $bx")
                if (listener != null) {
                    Log.d("working", "yes i amc $bx")
                    listener!!.itemClicked(bx + 1)
                }
            } else {
                activity!!.finish()
            }
        }.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("working", "OOOO")
        listener = activity as SafetyOneFragmentChangeListener
    }




}