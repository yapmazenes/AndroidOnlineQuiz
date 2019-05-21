package com.example.pc.onlinequizapp.Activities;

import android.content.Intent;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc.onlinequizapp.Common.Common;
import com.example.pc.onlinequizapp.R;

import com.squareup.picasso.Picasso;



public class Playing extends AppCompatActivity implements View.OnClickListener {
    final static long INTERVAL = 1000; //1 sec
    final static long TIMEOUT = 7000; // 7 sec
    int progressValue = 0;
    CountDownTimer countDownTimer;
    int index = 0, score = 0, thisQuestion = 0, totalQouestion, correctAnswer;




    ProgressBar progressBar;

    ImageView question_image;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestionNum, question_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        init();
    }

    private void init() {


        txtScore = findViewById(R.id.txt_score);
        txtQuestionNum = findViewById(R.id.txt_totalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image=findViewById(R.id.question_image);
        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        countDownTimer.cancel();
        if (index < totalQouestion) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())) {
                score += 10;
                correctAnswer++;
                showQuestion(++index);
            } else {
                EndGame();
            }
            txtScore.setText(String.format("%d", score));


        }
    }

    private void EndGame() {
        Intent view = new Intent(Playing.this, Done.class);
        Bundle dataSend = new Bundle();
        dataSend.putInt("SCORE", score);
        dataSend.putInt("TOTAL", totalQouestion);
        dataSend.putInt("CORRECT", correctAnswer);
        view.putExtras(dataSend);
        startActivity(view);
        finish();
    }

    private void showQuestion(int index) {
        if (index < totalQouestion) {
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQouestion));
            progressBar.setProgress(0);
            progressValue = 0;

            if (Common.questionList.get(index).getIsImageQuestion().equals("true")) {
                Picasso.with(getBaseContext()).load(Common.questionList.get(index).getQuestion()).into(question_image);
                question_image.setVisibility(View.VISIBLE);
                question_text.setVisibility(View.INVISIBLE);
            } else {
                question_text.setText(Common.questionList.get(index).getQuestion());
                question_image.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }
            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());
            countDownTimer.start();
        } else {
            EndGame();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQouestion = Common.questionList.size();
        countDownTimer = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
