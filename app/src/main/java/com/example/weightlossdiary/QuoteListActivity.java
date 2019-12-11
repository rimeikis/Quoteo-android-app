package com.example.weightlossdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
    FirebaseDatabase Database;
    private DatabaseReference DatabaseReference;
    private FirebaseUser User;
    private FirebaseAuth userAuth;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Quote> quoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        userAuth = FirebaseAuth.getInstance();
        User = userAuth.getCurrentUser();
        Database = FirebaseDatabase.getInstance();
        DatabaseReference = Database.getReference().child("Entries");
        DatabaseReference.keepSynced(true);
        quoteList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu nav) {
        getMenuInflater().inflate(R.menu.nav, nav);
        return super.onCreateOptionsMenu(nav);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                if (User != null && userAuth != null) {
                    startActivity(new Intent(QuoteListActivity.this, SubmitQuoteActivity.class));
                    finish();
                } else {
                    Toast.makeText(QuoteListActivity.this, "Please log in to post!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.action_signout:
                if (User != null && userAuth != null) {
                    userAuth.signOut();
                    startActivity(new Intent(QuoteListActivity.this, MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Quote quote = dataSnapshot.getValue(Quote.class);
                quoteList.add(quote);
                Collections.reverse(quoteList);
                adapter = new Adapter(QuoteListActivity.this, quoteList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
