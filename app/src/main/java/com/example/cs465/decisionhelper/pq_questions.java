package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class pq_questions extends AppCompatActivity {


    Resources res = null;
    String[] question_array = null;
    TextView questionText = null;
    TextView questionNumText = null;
    int currentQuestionNum = 1;
    int maxQuestionNum = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_questions);

        res = getResources();
        question_array = res.getStringArray(R.array.pq_questions_array);
        questionText = (TextView)findViewById(R.id.pq_question);
        questionNumText = (TextView)findViewById(R.id.pq_question_number_display);
        questionText.setText(question_array[currentQuestionNum-1]);
        questionNumText.setText(getQuestionNumberDisplay());
    }
    
    public void previousButtonClicked(View view)
    {
        if (currentQuestionNum == 1)
        {
            return;
        }
        currentQuestionNum--;
        questionText.setText(question_array[currentQuestionNum-1]);
        questionNumText.setText(getQuestionNumberDisplay());
    }
    
    public void nextButtonClicked(View view)
    {
        if (currentQuestionNum == 10)
        {
            Intent intent = new Intent(this, pq_results.class);
            startActivity(intent);
        }
        currentQuestionNum++;
        questionText.setText(question_array[currentQuestionNum-1]);
        questionNumText.setText(getQuestionNumberDisplay());
    }

    private String getQuestionNumberDisplay()
    {
        String currentQuestion = Integer.toString(currentQuestionNum);
        String totalQuestion = Integer.toString(maxQuestionNum);
        return currentQuestion + "/" + totalQuestion;
    }
}
