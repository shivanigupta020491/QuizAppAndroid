package com.example.quiz.Fragment;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quiz.R;
import com.example.quiz.SafetyQuizClass;
import com.example.quiz.utils.Constants;


/**
 * Created by harshgupta on 30/12/17.
 */

public class SafetyOneFragmentjava extends Fragment {

    private int id,id_one;
    RadioGroup answer;
    AppCompatRadioButton option1,option2,option3,option4;
    AppCompatButton button;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    TextView question;
    protected SharedPreferences mSharedPreferences;


    public static interface SafetyOneFragmentChangeListener{
        void itemClicked(int id);
    }

    private SafetyOneFragmentChangeListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_safety_layout,container,false);
        mSharedPreferences=getActivity().getSharedPreferences(Constants.INSTANCE.getUSER_INFO_PREFERENCE(), Context.MODE_PRIVATE);
        builder=new AlertDialog.Builder(getActivity());
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        if(view!=null){
             question=view.findViewById(R.id.question_id);
            answer=view.findViewById(R.id.radioGroup_id);

            button=view.findViewById(R.id.enter_btn_id);
            option1=view.findViewById(R.id.radioGroup1_id);
            option2=view.findViewById(R.id.radioGroup2_id);
            option3=view.findViewById(R.id.radioGroup3_id);
            option4=view.findViewById(R.id.radioGroup4_id);

           // final SafetyQuizClass safetyQuizClass= SafetyQuizClass.Companion.getSafetyQuizClass()[(int) id];
            final SafetyQuizClass safetyQuizClass=SafetyQuizClass.Companion.getSafetyQuizClass().get(id);
            //final SafetyQuizClass safetyQuizClass = SafetyQuizClass

            question.setText(safetyQuizClass.getQuestion());
            option1.setText(safetyQuizClass.getOption_a());
            option2.setText(safetyQuizClass.getOption_b());
            option3.setText(safetyQuizClass.getOption_c());
            option4.setText(safetyQuizClass.getOption_d());

            answer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    id_one=checkedId;
                    button.setEnabled(true);
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (id_one){
                        case R.id.radioGroup1_id:
                            if("a"==safetyQuizClass.getChoice()){
                                safetydialogbox(true,"awesome");
                            }else{
                                safetydialogbox(false,safetyQuizClass.getChoice());
                            }
                            break;
                        case R.id.radioGroup2_id:
                            if("b"==safetyQuizClass.getChoice()){
                                safetydialogbox(true,"awesome");
                            }else{
                                safetydialogbox(false,safetyQuizClass.getChoice());
                            }
                            break;
                        case R.id.radioGroup3_id:
                            if("c"==safetyQuizClass.getChoice()){
                                safetydialogbox(true,"awesome");
                            }else{
                                safetydialogbox(false,safetyQuizClass.getChoice());
                            }
                            break;
                        case R.id.radioGroup4_id:
                            if("d"==safetyQuizClass.getChoice()){
                                safetydialogbox(true,"awesome");
                            }else{
                                safetydialogbox(false,safetyQuizClass.getChoice());
                            }
                            break;
                        default:
                            safetydialogbox(false,safetyQuizClass.getChoice());
                    }
                }
            });

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void safetydialogbox(boolean a, String b){
        Log.d("working","yes i ama "+a);
        builder.setTitle("Safety Quiz");
        mSharedPreferences.edit().putBoolean(Constants.INSTANCE.getSetAnswer(), true).commit();
        if(a) {
            builder.setMessage("Great your answer is correct");
            int ba= mSharedPreferences.getInt(Constants.INSTANCE.getSafetyanswerString(), 0);
            mSharedPreferences.edit().putInt(Constants.INSTANCE.getSafetyanswerString(), ba + 1).commit();
        }else{
            builder.setMessage("The correct answer is "+ b);
        }

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int bx=id;
                Log.d("working","yes i amb "+bx);
                if(bx+1<5) {
                    Log.d("working","yes i amd "+bx);
                    if(listener!=null){
                        Log.d("working","yes i amc "+bx);
                        listener.itemClicked(bx+1);
                    }
                }else{
                    getActivity().finish();
                }
            }
        }).show();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("working","OOOO");
        this.listener=(SafetyOneFragmentChangeListener)activity;
    }


}
