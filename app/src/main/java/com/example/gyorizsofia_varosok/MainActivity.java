package com.example.gyorizsofia_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private LinearLayout layoutlist;
    private EditText dataid;
    private Button buttonadd;
    private Button buttonlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ListActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, InsertActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }


    public void init(){
        progressBar = findViewById(R.id.progressBar);
        layoutlist = findViewById(R.id.layoutlist);
        dataid = findViewById(R.id.dataid);
        buttonadd = findViewById(R.id.buttonadd);
        buttonlist = findViewById(R.id.buttonlist);
    }
}