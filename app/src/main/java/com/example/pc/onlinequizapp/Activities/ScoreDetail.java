package com.example.pc.onlinequizapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pc.onlinequizapp.Model.QuestionScore;
import com.example.pc.onlinequizapp.R;
import com.example.pc.onlinequizapp.ViewHolder.ScoreDetailViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreDetail extends AppCompatActivity {
    String viewUser = "";
    DatabaseReference question_Score;
    RecyclerView scoreList;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);
        question_Score = FirebaseDatabase.getInstance().getReference("Question_Score");
        scoreList = findViewById(R.id.scoreList);
        scoreList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scoreList.setLayoutManager(layoutManager);
        if (getIntent() != null){
            viewUser = getIntent().getStringExtra("viewUser");
            ((TextView)findViewById(R.id.txtUserName)).setText(viewUser);
        }
        if (!viewUser.isEmpty())
            loadScoreDetail(viewUser);

    }

    private void loadScoreDetail(String viewUser) {
        adapter = new FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder>(QuestionScore.class, R.layout.score_detail_layout, ScoreDetailViewHolder.class, question_Score.orderByChild("user").equalTo(viewUser)) {
            @Override
            protected void populateViewHolder(ScoreDetailViewHolder viewHolder, QuestionScore model, int position) {
                viewHolder.txt_CategoryName.setText(model.getCategoryName());
                viewHolder.txt_Score.setText(model.getScore());
            }
        };
    adapter.notifyDataSetChanged();
    scoreList.setAdapter(adapter);
    }
}
