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
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

        final TextView tvBuyRateTHB = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateAUD = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateCNY = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateEUR = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateHKD = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateSGD = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateJPY = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateKRW = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateNZD = (TextView) findViewById(R.id.buyRate);
        final TextView tvBuyRateUSD = (TextView) findViewById(R.id.buyRate);

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
                //Log.d("NatradaC's Log ","THB Rate Test | " + latest.getRate("THB"));

                NumberFormat formatter = new DecimalFormat("##.0000");

                //TEST USD to THB
                tvBuyRateUSD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("USD")));
                //getCurrencyRate(latest);

            }

            @Override
            public void onFailure(Call<FixerObject> call, Throwable t) {

            }
        });


    }

}
