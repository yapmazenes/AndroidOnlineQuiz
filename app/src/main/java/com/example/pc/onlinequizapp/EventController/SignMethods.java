package com.example.pc.onlinequizapp.EventController;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.pc.onlinequizapp.Activities.Home;
import com.example.pc.onlinequizapp.Common.Common;
import com.example.pc.onlinequizapp.Model.ShredPreference;
import com.example.pc.onlinequizapp.Model.User;
import com.example.pc.onlinequizapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignMethods implements View.OnClickListener {

    ShredPreference shredPreference;
    MaterialEditText _edtNewUser, _edtNewPassword, _edtNewEmail, _edtUser, _edtPassword;
    String User, Password;
    Context context;
    FirebaseDatabase database;
    DatabaseReference users;
    LayoutInflater layoutInflater;
    CheckBox rememberMe;
    boolean isRememberMe;
    final Activity activity;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                showSignUpDialog();
                break;
            case R.id.signin:
                showSignInDialog();
                break;
        }
    }


    public SignMethods(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
       _edtUser = activity.findViewById(R.id.edtUser);
       _edtPassword = activity.findViewById(R.id.edtPassword);
    }

    public void showSignUpDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please fill full information");


        View sign_Up_Layout = layoutInflater.inflate(R.layout.sign_up_layout, null);


       _edtNewUser = sign_Up_Layout.findViewById(R.id.edtNewUserName);
        _edtNewPassword = sign_Up_Layout.findViewById(R.id.edtNewPassword);
        _edtNewEmail = sign_Up_Layout.findViewById(R.id.edtNewEmail);


        alertDialog.setView(sign_Up_Layout);
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final com.example.pc.onlinequizapp.Model.User user = new User(_edtNewUser.getText().toString(), _edtNewPassword.getText().toString(), _edtNewEmail.getText().toString());


                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUserName()).exists())
                            Toast.makeText(context, "User already exist !", Toast.LENGTH_SHORT).show();
                        else {
                           users.child(user.getUserName()).setValue(user);
                            Toast.makeText(context, "User registration is success", Toast.LENGTH_SHORT).show();
                            _edtUser.setText(_edtNewUser.getText());
                            _edtPassword.setText("");
                            rememberMe.setChecked(false);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                dialog.dismiss();
            }
        }).show();


    }

    public void showSignInDialog() {

        User = _edtUser.getText().toString();
        Password = _edtPassword.getText().toString();

        rememberMe = activity.findViewById(R.id.chkRememberMe);
        isRememberMe = rememberMe.isChecked();


        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!User.isEmpty()) {
                    if (dataSnapshot.child(User).exists()) {

                        User login = dataSnapshot.child(User).getValue(User.class);
                        if (login.getPassword().equals(Password)) {
                            if (isRememberMe) {
                                shredPreference = new ShredPreference(context);
                                boolean isAlreadySaved;
                                isAlreadySaved = shredPreference.getValueBoolean("isRememberMe").booleanValue();
                                if (!isAlreadySaved)
                                    saveSign(User, Password);
                            } else {
                                shredPreference = new ShredPreference(context);
                                if (shredPreference.getValueBoolean("isRememberMe").booleanValue())
                                    shredPreference.Clear();
                            }
                            Intent view = new Intent(context, Home.class);
                            Common.currentUser = login;
                            activity.startActivity(view);
                            activity.finish();


                        } else
                            Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "User is not exist !", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(context, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveSign(String user, String password) {
        shredPreference.Save("userName", user);
        shredPreference.Save("userPassword", password);
        shredPreference.SaveBoolean("isRememberMe", true);
    }
}