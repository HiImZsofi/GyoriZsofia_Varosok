package com.example.gyorizsofia_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    private Button buttonback;
    private TextView jsondata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
                ListActivity.this.startActivity(myIntent);
            }
        });
    }

    public void init(){
        buttonback = findViewById(R.id.buttonback);
        jsondata = findViewById(R.id.jsondata);
    }
}