package org.gripp.android.gripp.fragments.SafetyFragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quiz.R
import com.example.quiz.SafetyQuizActivity
import com.example.quiz.SafetyQuizClass
import com.example.quiz.utils.Constants
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by harshgupta on 30/12/17.
 */

class SafetyFragment : Fragment() {

    var lSharedPreferences: SharedPreferences?=null
    var textId: TextView? = null
    var raised_btn:AppCompatButton?=null
    var safetyQuizClass:ArrayList<SafetyQuizClass>? = null
    var loading: ProgressDialog? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_safety_one, container, false)
       // SafetyQuizClass.safetyQuizClass!!.clear()
        textId=rootView.findViewById(R.id.text_id)
        raised_btn=rootView.findViewById(R.id.raised_btn)
        lSharedPreferences = activity!!.getSharedPreferences(Constants.USER_INFO_PREFERENCE, Context.MODE_PRIVATE)

        raised_btn!!.setOnClickListener {
            raisedbtn()
        }

     getItems()

        println(">>>>>>>>> safety quiz in safety fragment" +  safetyQuizClass)

        return rootView
    }


    fun raisedbtn() {
        lSharedPreferences!!.edit().putInt(Constants.safetyanswerString, 0).commit()
        lSharedPreferences!!.edit().putBoolean(Constants.setAnswer, false).commit()
        val i = Intent(activity, SafetyQuizActivity::class.java)
        i.putExtra("quizquestion", 0)
        startActivity(i)
    }

//

    override fun onStart() {
        super.onStart()
        if (lSharedPreferences!!.getBoolean(Constants.setAnswer, false)) {
            textId!!.text = "You scored " + lSharedPreferences!!.getInt(Constants.safetyanswerString, 0) + " marks"
            textId!!.visibility = View.VISIBLE
        }
    }

    companion object {

        fun newInstance(): Fragment {
            return SafetyFragment()
        }
    }

    private fun getItems() {
         loading = ProgressDialog.show(activity, "Loading", "please wait", false, true)
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

//        val result = "result"
//        // Use the Kotlin extension in the fragment-ktx artifact
//        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
         loading!!.dismiss()
    }
}
