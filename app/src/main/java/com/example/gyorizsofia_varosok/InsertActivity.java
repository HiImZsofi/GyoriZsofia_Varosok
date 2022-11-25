package com.example.gyorizsofia_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;

public class InsertActivity extends AppCompatActivity {
    private Button buttonback;
    private Button buttonadd;
    private EditText addnev;
    private EditText addorszag;
    private EditText addlakossag;
    private String url = "https://retoolapi.dev/xRxTQq/varosok";

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

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVaros();
            }
        });
    }

    public void init(){
        buttonback = findViewById(R.id.buttonback);
        buttonadd = findViewById(R.id.buttonadd);
        addnev = findViewById(R.id.addnev);
        addorszag = findViewById(R.id.addorszag);
        addlakossag = findViewById(R.id.addlakossag);
    }

    private void addVaros() {
        String lakossag = addnev.getText().toString();
        String orszag = addorszag.getText().toString();
        String nepszam = addlakossag.getText().toString();

        boolean valid = validacio();

        if (valid){
            Toast.makeText(this,
                    "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        int nepmennyiseg = Integer.parseInt(nepszam);
        Varos varos = new Varos(0,lakossag,orszag,nepmennyiseg);
        Gson jsonConverter = new Gson();
        RequestTask task = new RequestTask(url, "POST",
                jsonConverter.toJson(varos));
        task.execute();
    }

    private boolean validacio() {
        if (addnev.getText().toString().isEmpty() ||
                addorszag.getText().toString().isEmpty() || addlakossag.getText().toString().isEmpty())
            return true;
        else
            return false;
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        private String requestUrl;
        private String requestMethod;
        private String requestBody;

        public RequestTask(String requestUrl) {
            this.requestUrl = requestUrl;
            this.requestMethod = "GET";
        }

        public RequestTask(String requestUrl, String requestMethod) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
        }

        public RequestTask(String requestUrl, String requestMethod, String requestBody) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
            this.requestBody = requestBody;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestMethod) {
                    case "GET":
                        response = RequestHandler.get(url);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestBody);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestBody);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }
}