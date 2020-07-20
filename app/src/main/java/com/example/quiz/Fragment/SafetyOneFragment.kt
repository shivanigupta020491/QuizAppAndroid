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
    protected var mSharedPreferences: SharedPreferences? = null

    interface SafetyOneFragmentChangeListener {
        fun itemClicked(id: Int)
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
        getItems()
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

       //final SafetyQuizClass safetyQuizClass= SafetyQuizClass.Companion.getSafetyQuizClass()[(int) id];
        val safetyQuizClass = safetyQuizClass!!.get(idQuiz)
            //final SafetyQuizClass safetyQuizClass = SafetyQuizClass
            question!!.setText(safetyQuizClass.question)
            option1!!.setText(safetyQuizClass.option_a)
            option2!!.setText(safetyQuizClass.option_b)
            option3!!.setText(safetyQuizClass.option_c)
            option4!!.setText(safetyQuizClass.option_d)
            answer!!.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                id_one = checkedId
                button!!.setEnabled(true)
            })
            button!!.setOnClickListener(View.OnClickListener {
                when (id_one) {
                    R.id.radioGroup1_id -> if ("a" === safetyQuizClass.choice) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, safetyQuizClass.choice)
                    }
                    R.id.radioGroup2_id -> if ("b" === safetyQuizClass.choice) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, safetyQuizClass.choice)
                    }
                    R.id.radioGroup3_id -> if ("c" === safetyQuizClass.choice) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, safetyQuizClass.choice)
                    }
                    R.id.radioGroup4_id -> if ("d" === safetyQuizClass.choice) {
                        safetydialogbox(true, "awesome")
                    } else {
                        safetydialogbox(false, safetyQuizClass.choice)
                    }
                    else -> safetydialogbox(false, safetyQuizClass.choice)
                }
            })
        }
    }

    fun setId(id: Int) {
        this.idQuiz = id
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

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("working", "OOOO")
        listener = activity as SafetyOneFragmentChangeListener
    }

    private fun getItems() {
        //    loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
        val stringRequest = StringRequest(
            Request.Method.GET,
            //"https://script.google.com/macros/s/AKfycbzxiC0ndPDRi72Vum8fuyBO2I6i4cDkF1-OK9a-2l_EeBtDzxTX/exec?action=getItems",

            //url for quiz
            "https://script.google.com/macros/s/AKfycbwTMAWny9KC220V5Pi4cR0WkwJAVvDPEHmkwLVG3aooH4e3JRg/exec?action=getItems",
            object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    parseItems(response!!)
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {}
            }
        )
        val socketTimeOut = 50000
        val policy: RetryPolicy =
            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = policy
        val queue = Volley.newRequestQueue(activity)
        queue.add(stringRequest)
    }

    // for quiz
    private fun parseItems(jsonResposnce: String) {
        // val list: ArrayList<HashMap<String, String?>> = ArrayList()
        try {
            val jobj = JSONObject(jsonResposnce)
            val jarray = jobj.getJSONArray("quiz")
            for (i in 0 until jarray.length()) {
                val jo = jarray.getJSONObject(i)
                val question = jo.getString("question")
                val option1 = jo.getString("option1")
                val option2 = jo.getString("option2")
                val option3 = jo.getString("option3")
                val option4 = jo.getString("option4")
                val answer = jo.getString("answer")
                // val price = jo.getString("price")
                //val item: HashMap<String, String?> = HashMap()
                val safetyQuizClass1 = SafetyQuizClass(question,option1,option2,option3,option4,answer)
//                item["question"] = question
//                item["option1"] = option1
//                item["option2"] = option2
//                item["option3"] = option3
//                item["option4"] = option4
//                item["answer"] = answer
                // item["price"] = price
                //list.add(item)
                safetyQuizClass!!.add(safetyQuizClass1)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        // loading!!.dismiss()
    }
}