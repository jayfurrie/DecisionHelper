package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class pq_questions extends AppCompatActivity {


    Resources res = null;
    String[] question_array = null;
    TextView questionText = null;
    TextView questionNumText = null;
    RadioGroup radioGroup = null;
    int currentQuestionNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_questions);

        res = getResources();
        question_array = res.getStringArray(R.array.pq_questions_array);
        questionText = (TextView)findViewById(R.id.pq_questions_tv_question);
        questionNumText = (TextView)findViewById(R.id.pq_questions_tv_numberdisplay);
        radioGroup = (RadioGroup) findViewById(R.id.pq_radio_group);
        updateUI();
    }
    
    public void pq_questions_ibtn_previousOnClick(View view)
    {
        if (currentQuestionNum == 0)
        {
            return;
        }
        currentQuestionNum--;
        updateUI();
    }
    
    public void pq_questions_ibtn_nextOnClick(View view)
    {
        if (currentQuestionNum == question_array.length - 1)
        {
            Intent intent = new Intent(this, pq_results.class);
            startActivity(intent);
            return;
        }
        currentQuestionNum++;
        updateUI();
    }

    private String getQuestionNumberDisplay()
    {
        String currentQuestion = Integer.toString(currentQuestionNum + 1);
        String totalQuestion = Integer.toString(question_array.length);
        return currentQuestion + "/" + totalQuestion;
    }

    private void updateUI()
    {
        radioGroup.clearCheck();
        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    questionText.setText(question_array[currentQuestionNum]);
                    questionNumText.setText(getQuestionNumberDisplay());
                }
            },
        300);

    }
}
