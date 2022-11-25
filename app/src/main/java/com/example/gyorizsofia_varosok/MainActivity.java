package com.example.gyorizsofia_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private ListView listviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


    public void init(){
        progressBar = findViewById(R.id.progressBar);
        layoutlist = findViewById(R.id.layoutlist);
        dataid = findViewById(R.id.dataid);
        buttonadd = findViewById(R.id.buttonadd);
        buttonlist = findViewById(R.id.buttonlist);
        listviewdata = findViewById(R.id.listviewdata);
    }
}