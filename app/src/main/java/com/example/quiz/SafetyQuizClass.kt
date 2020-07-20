package com.example.quiz

import android.app.ProgressDialog
import android.content.Context
import android.widget.SimpleAdapter
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by harshgupta on 28/12/17.
 */

class SafetyQuizClass(var question: String, var option_a: String,
                      var option_b: String,var option_c: String, var option_d: String, var choice: String) {

    companion object {
       // val safetyQuizClass:ArrayList<SafetyQuizClass>?=null
        //val safetyQuizClass = arrayOf(SafetyQuizClass("Decibel (db) is a unit used to measure", "Light", " Sound", "Frequency", "None of the above", "b"), SafetyQuizClass("Water is used to extinguish", "Class-A fires", "Class-B fires", "Class-C fires", "All of the above", "a"), SafetyQuizClass("The following class of fire occur in electrical equipment", "Class-A fires", "Class-B fires", "Class-C fires", "All of the above", "c"), SafetyQuizClass("_____ is best suited to extinguishing oil or flammable liquid fire", "Soda acid", "Vaporizing liquid", "Foam", "Dry chemical", "c"), SafetyQuizClass("Workplace related injuries, illnesses and deaths impose costs upon?", "Employers", "Employees", "The community", "All OF The above", "d"))

    }

//    private fun getItems() {
//        //    loading = ProgressDialog.show(this, "Loading", "please wait", false, true)
//        val stringRequest = StringRequest(
//            Request.Method.GET,
//            //"https://script.google.com/macros/s/AKfycbzxiC0ndPDRi72Vum8fuyBO2I6i4cDkF1-OK9a-2l_EeBtDzxTX/exec?action=getItems",
//
//            //url for quiz
//            "https://script.google.com/macros/s/AKfycbwTMAWny9KC220V5Pi4cR0WkwJAVvDPEHmkwLVG3aooH4e3JRg/exec?action=getItems",
//            object : Response.Listener<String?> {
//                override fun onResponse(response: String?) {
//                    parseItems(response!!)
//                }
//            },
//            object : Response.ErrorListener {
//                override fun onErrorResponse(error: VolleyError?) {}
//            }
//        )
//        val socketTimeOut = 50000
//        val policy: RetryPolicy =
//            DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//        stringRequest.retryPolicy = policy
//        val queue = Volley.newRequestQueue(activity)
//        queue.add(stringRequest)
//    }
//
//    // for quiz
//    private fun parseItems(jsonResposnce: String) {
//        // val list: ArrayList<HashMap<String, String?>> = ArrayList()
//        try {
//            val jobj = JSONObject(jsonResposnce)
//            val jarray = jobj.getJSONArray("quiz")
//            for (i in 0 until jarray.length()) {
//                val jo = jarray.getJSONObject(i)
//                val question = jo.getString("question")
//                val option1 = jo.getString("option1")
//                val option2 = jo.getString("option2")
//                val option3 = jo.getString("option3")
//                val option4 = jo.getString("option4")
//                val answer = jo.getString("answer")
//                // val price = jo.getString("price")
//                //val item: HashMap<String, String?> = HashMap()
//                val safetyQuizClass1 = SafetyQuizClass(question,option1,option2,option3,option4,answer)
////                item["question"] = question
////                item["option1"] = option1
////                item["option2"] = option2
////                item["option3"] = option3
////                item["option4"] = option4
////                item["answer"] = answer
//                // item["price"] = price
//                //list.add(item)
//                safetyQuizClass!!.add(safetyQuizClass1)
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//
//
//        // loading!!.dismiss()
//    }
}
