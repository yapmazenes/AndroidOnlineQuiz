package com.example.pc.onlinequizapp.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pc.onlinequizapp.EventController.fabChildClickMethods;
import com.example.pc.onlinequizapp.R;

public class newQuestion extends AppCompatActivity {
    TextView exit;

    RadioGroup radioGroup;
    EditText edtforLink;
    Button btnforSystem;
    FloatingActionButton fab;
    fabChildClickMethods childClickMethods;

    public static final int RESULT_GALLERY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
     //   init();
    }

  /*  private void init() {
        childClickMethods = new fabChildClickMethods(newQuestion.this);
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(childClickMethods);
        radioGroup = findViewById(R.id.rdbGrup);
        btnforSystem = findViewById(R.id.btnsystem);
        edtforLink = findViewById(R.id.edtLink);
        fab = findViewById(R.id.btnOkey);
        btnforSystem.setOnClickListener(childClickMethods);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdbBtnLink:
                        rdbLink();
                        break;
                    case R.id.rdbBtnSystem:
                        rdbSytstem();
                        break;
                }
            }
        });
    }*/

    private void rdbSytstem() {
        edtforLink.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        btnforSystem.setVisibility(View.VISIBLE);
    }

    private void rdbLink() {
        btnforSystem.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        edtforLink.setVisibility(View.VISIBLE);
    }


}
