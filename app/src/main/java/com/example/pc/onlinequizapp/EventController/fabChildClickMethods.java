package com.example.pc.onlinequizapp.EventController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.onlinequizapp.Activities.newQuestion;
import com.example.pc.onlinequizapp.R;

import static com.example.pc.onlinequizapp.Activities.newQuestion.RESULT_GALLERY;

public class fabChildClickMethods implements View.OnClickListener {
    Context context;
    final Activity activity;

    public fabChildClickMethods(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_itemImage:
                menuImage();
                break;
            case R.id.menu_itemSound:
                menuSound();
                break;
            case R.id.menu_itemText:
                menuText();
                break;
           /* case R.id.btnsystem:
                btnForSystem();
            case R.id.exit:
                exit();
                break;*/
        }
    }

    void Load() {
        Intent starter = new Intent(context, newQuestion.class);

        activity.startActivity(starter);
    }


    void menuImage() {
        Load();

    }

    void btnForSystem() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, RESULT_GALLERY);
    }

    void menuSound() {
        Load();
       // Toast.makeText(context, "menuSound", Toast.LENGTH_SHORT).show();
    }

    void menuText() {
        Load();
     //   Toast.makeText(context, "menuText", Toast.LENGTH_SHORT).show();
    }

    void exit() {
        activity.finish();
    }

}
