package com.example.weightlossdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener userAuthListener;
    private FirebaseUser User;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        userAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                User = firebaseAuth.getCurrentUser();
                if (User != null) {
                    startActivity(new Intent(MainActivity.this, QuoteListActivity.class));
                    finish();
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email.getText().toString())
                        && !TextUtils.isEmpty(password.getText().toString())) {
                    String emailInput = email.getText().toString();
                    String passwordInput = password.getText().toString();
                    login(emailInput, passwordInput);
                } else {
                    Toast.makeText(MainActivity.this, "Authentication Failed!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void login(String email, String pwd) {
        userAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, QuoteListActivity.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Failed!", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signout) {
            userAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userAuth.addAuthStateListener(userAuthListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (userAuthListener != null) {
            userAuth.removeAuthStateListener(userAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void clickCreateAcc(View view) {
        startActivity(new Intent(MainActivity.this, CreateAccActivity.class));
        finish();
    }
}