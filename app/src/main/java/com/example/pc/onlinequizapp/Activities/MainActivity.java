package com.example.pc.onlinequizapp.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pc.onlinequizapp.BroadCastReceiver.AlarmReceiver;

import com.example.pc.onlinequizapp.EventController.SignMethods;
import com.example.pc.onlinequizapp.Model.ShredPreference;
import com.example.pc.onlinequizapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail;
    MaterialEditText edtUser, edtPassword;

    Button btnSignIn, btnSignUp;


    ShredPreference shredPreference;
    boolean isRememberMe;
    CheckBox rememberMe;
    FirebaseDatabase database;
    DatabaseReference users;
    SignMethods signMethods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerAlarm();
        init();
    }

    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 41);
        calendar.set(Calendar.SECOND, 0);
        Intent starter = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, starter, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void init() {

        signMethods = new SignMethods(MainActivity.this);
        rememberMe = findViewById(R.id.chkRememberMe);
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);

        edtNewUser = findViewById(R.id.edtNewUserName);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtNewEmail = findViewById(R.id.edtNewEmail);

        shredPreference = new ShredPreference(MainActivity.this);
        isRememberMe = shredPreference.getValueBoolean("isRememberMe");
        if (isRememberMe) {
            edtUser.setText(shredPreference.getValue("userName"));
            edtPassword.setText(shredPreference.getValue("userPassword"));
            rememberMe.setChecked(isRememberMe);
        }

        btnSignIn = findViewById(R.id.signin);
        btnSignUp = findViewById(R.id.signUp);

        btnSignIn.setOnClickListener(signMethods);
        btnSignUp.setOnClickListener(signMethods);


    }
}
