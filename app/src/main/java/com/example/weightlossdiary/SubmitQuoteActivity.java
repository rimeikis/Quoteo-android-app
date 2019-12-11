package com.example.weightlossdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SubmitQuoteActivity extends AppCompatActivity {
    Button mSubmitButton;
    FirebaseAuth mAuth;
    private EditText refPostQuote;
    private EditText refPostAuthor;
    private DatabaseReference mPostDatabase;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quote);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("Entries");
        refPostQuote = findViewById(R.id.postQuote);
        refPostAuthor = findViewById(R.id.postAuthor);
        mSubmitButton = findViewById(R.id.submitPost);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quote to database
                startPosting();
            }
        });
    }

    private void startPosting() {
        final String quoteValue = refPostQuote.getText().toString().trim();
        final String authorValue = refPostAuthor.getText().toString().trim();
        if (!TextUtils.isEmpty(quoteValue) && !TextUtils.isEmpty(authorValue)) {
            DatabaseReference newPost = mPostDatabase.push();
            Map<String, String> dataToSave = new HashMap<>();
            dataToSave.put("quote", quoteValue);
            dataToSave.put("author", authorValue);
            dataToSave.put("userid", mUser.getUid());
            dataToSave.put("username", mUser.getEmail());
            newPost.setValue(dataToSave);
            startActivity(new Intent(SubmitQuoteActivity.this, QuoteListActivity.class));
            finish();
        } else {
            Toast.makeText(SubmitQuoteActivity.this, "Empty Field Error!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SubmitQuoteActivity.this, QuoteListActivity.class));
    }
}
