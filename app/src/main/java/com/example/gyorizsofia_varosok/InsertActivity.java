package com.example.gyorizsofia_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class InsertActivity extends AppCompatActivity {
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(InsertActivity.this, MainActivity.class);
                InsertActivity.this.startActivity(myIntent);
            }
        });
    }

    public void init(){
        buttonback = findViewById(R.id.buttonback);
    }
}