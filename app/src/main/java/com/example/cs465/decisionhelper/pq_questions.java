package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class pq_questions extends BaseActivity {
    List<Storage.Question> questions = null;
    TextView questionText = null;
    TextView questionNumText = null;
    RadioGroup radioGroup = null;
    int currentQuestionNum = 0;
    boolean hadResponse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_questions);
        setTitle("Personality Quiz");

        questions = db.getAllQuizQuestions();
        questionText = (TextView)findViewById(R.id.pq_questions_tv_question);
        questionNumText = (TextView)findViewById(R.id.pq_questions_tv_numberdisplay);
        radioGroup = (RadioGroup) findViewById(R.id.pq_radio_group);

        updateUI();
    }
    
    public void pq_questions_ibtn_previousOnClick(View view)
    {
        submitResponse();
        if (currentQuestionNum == 0)
        {
            finish();
            return;
        }
        currentQuestionNum--;
        updateUI();
    }
    
    public void pq_questions_ibtn_nextOnClick(View view)
    {
        submitResponse();
        if (currentQuestionNum == questions.size() - 1)
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
        String totalQuestion = Integer.toString(questions.size());
        return currentQuestion + "/" + totalQuestion;
    }

    private void updateUI()
    {
        final View screen = findViewById(R.id.pq_question_view);
        screen.setVisibility(View.INVISIBLE);
        radioGroup.clearCheck();
        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    Storage.Question q = questions.get(currentQuestionNum);
                    questionText.setText(q.prompt);
                    questionNumText.setText(getQuestionNumberDisplay());
                    int response = db.getResponseForQuestionAndUser(login_login.username, q.id);
                    hadResponse = response > -1;
                    if (hadResponse) {
                        selectButton(response);
                    }
                    screen.setVisibility(View.VISIBLE);
                }
            },
        300);

    }

    private void submitResponse() {
        int response = getSelectedButton();
        Storage.Question q = questions.get(currentQuestionNum);
        if (response > -1) {
            if (hadResponse) {
                db.updateResponseForQuestionAndUser(
                        login_login.username,
                        q.id,
                        response
                );
            } else {
                db.answerQuestionForUser(
                        login_login.username,
                        q.id,
                        response
                );
            }
        }
    }

    private int getSelectedButton() {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        if (radioButtonID == -1) {
            return -1;
        }
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);
        return idx + 1;
    }

    private void selectButton(int idx) {
        View radioButton = radioGroup.getChildAt(idx - 1);
        if (radioButton == null) {
            return;
        }
        int id = radioButton.getId();
        radioGroup.check(id);
    }
}
