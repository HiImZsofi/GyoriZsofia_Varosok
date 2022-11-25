package com.example.gyorizsofia_varosok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private Button buttonback;
    private TextView jsondata;
    private String url = "https://retoolapi.dev/xRxTQq/varosok";
    private List<Varos> varosok = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        RequestTask task = new RequestTask(url, "GET");
        task.execute();

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
        jsondata.setMovementMethod(new ScrollingMovementMethod());
    }

    private void formBack() {
        jsondata.setVisibility(View.GONE);
        RequestTask task = new RequestTask(url, "GET");
        task.execute();
    }

    private class RequestTask extends AsyncTask<Void, Void, Response>{
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
                switch (requestMethod){
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
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Response response){
            Gson converter = new Gson();
            super.onPostExecute(response);
            if(response == null){
                Toast.makeText(ListActivity.this, "unable_to_connect", Toast.LENGTH_SHORT).show();
                return;
            }
            if(response.getResponseCode() >= 400){
                Toast.makeText(ListActivity.this, response.getContent(), Toast.LENGTH_SHORT).show();
                return;
            }
            switch (requestMethod){
                case "GET":
                    String content = response.getContent();
                    varosok = Arrays.asList(converter.fromJson(content, Varos[].class));
                    System.out.println("!"+varosok);
                    jsondata.setText(varosok.toString());
                    break;
                default:
                    if (response.getResponseCode() >= 201 && response.getResponseCode() < 300){
                        jsondata.setText("");
                        RequestTask task = new RequestTask(url);
                        task.execute();
                    }
                    break;
            }
        }
    }
}