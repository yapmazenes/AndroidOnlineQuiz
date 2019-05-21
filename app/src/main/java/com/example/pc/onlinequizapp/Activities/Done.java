package com.example.pc.onlinequizapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc.onlinequizapp.Common.Common;
import com.example.pc.onlinequizapp.Model.QuestionScore;
import com.example.pc.onlinequizapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Done extends AppCompatActivity {
    Button btnTryAgain;
    TextView txtResultScore, txtResultQuestion;
    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference question_Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        init();


    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        question_Score = database.getReference("Question_Score");
        txtResultScore = findViewById(R.id.txtTotalScore);
        txtResultQuestion = findViewById(R.id.txtTotalQuestion);
        progressBar = findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(Done.this, Home.class);
                startActivity(view);
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int score = bundle.getInt("SCORE");
            int total = bundle.getInt("TOTAL");
            int correct = bundle.getInt("CORRECT");

            txtResultScore.setText(String.format("SCORE : %d", score));
            txtResultQuestion.setText(String.format("PASSED : %d / %d", correct, total));
            progressBar.setMax(total);
            progressBar.setProgress(correct);
            String user_categoryId=String.format("%s_%s", Common.currentUser.getUserName(),Common.categoryId);


            question_Score.child(user_categoryId).setValue(new QuestionScore(user_categoryId,Common.currentUser.getUserName(),String.valueOf(score),Common.categoryId,Common.categoryName));
        }

    }
}
