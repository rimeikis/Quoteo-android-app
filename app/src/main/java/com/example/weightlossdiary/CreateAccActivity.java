package com.example.weightlossdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccActivity extends AppCompatActivity {
    Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        createAccountBtn = findViewById(R.id.buttonReg);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(CreateAccActivity.this).create();
                alert.setTitle("Attention!");
                alert.setMessage("Account creation functionality is under construction.\n\n" +
                        "Please go back to the Login Activity and use the following credentials to continue.\n\n" +
                        "E-Mail: admin@admin.com\nPass: admin123\n\nor\n\nE-Mail: user@user.com\nPass: user123");
                alert.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CreateAccActivity.this, MainActivity.class));
                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CreateAccActivity.this, MainActivity.class));
    }
}