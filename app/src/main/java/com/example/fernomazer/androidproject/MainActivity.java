package com.example.fernomazer.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView buyRateTHB = (TextView) findViewById(R.id.buyRate);



        //Error
        /*String urlFixer= "http://data.fixer.io/api/latest?access_key=07d3ae4dfadca745698d570b7cff098a";
        System.out.println(this.getJSONUrl(urlFixer));*/

        final String urlFixer= "http://data.fixer.io/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlFixer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FixerService service = retrofit.create(FixerService.class);
        service.getLatest().enqueue(new Callback<FixerObject>() {
            @Override
            public void onResponse(Call<FixerObject> call, Response<FixerObject> response) {
                FixerObject latest = response.body();
                Log.d("NatradaC's Log ","API Test | " + latest.getRates());
                Log.d("NatradaC's Log ","THB Rate Test | " + latest.getRate("THB"));

                buyrateTHB.setText(""+latest.getRate("THB"));

            }

            @Override
            public void onFailure(Call<FixerObject> call, Throwable t) {

            }
        });


    }

    public String getJSONUrl(){


        return null;
    }
}
