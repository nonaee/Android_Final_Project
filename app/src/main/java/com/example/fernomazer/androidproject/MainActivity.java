package com.example.fernomazer.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        //final TextView tvBuyRateTHB = (TextView) findViewById(R.id);
        View vAUD = findViewById(R.id.AUD);
        final TextView tvBuyRateAUD = (TextView)vAUD.findViewById(R.id.buyRate);
        final ImageView imageViewAUD = (ImageView)vAUD.findViewById(R.id.flag);

        View vCNY = findViewById(R.id.CNY);
        final TextView tvBuyRateCNY = (TextView)vCNY.findViewById(R.id.buyRate);
        final ImageView imageViewCNY = (ImageView)vCNY.findViewById(R.id.flag);

        View vEUR = findViewById(R.id.EUR);
        final TextView tvBuyRateEUR = (TextView)vEUR.findViewById(R.id.buyRate);
        final ImageView imageViewEUR = (ImageView)vEUR.findViewById(R.id.flag);

        View vGBP = findViewById(R.id.GBP);
        final TextView tvBuyRateGBP = (TextView)vGBP.findViewById(R.id.buyRate);
        final ImageView imageViewGBP = (ImageView)vGBP.findViewById(R.id.flag);

        View vHKD = findViewById(R.id.HKD);
        final TextView tvBuyRateHKD = (TextView)vHKD.findViewById(R.id.buyRate);
        final ImageView imageViewHKD = (ImageView)vHKD.findViewById(R.id.flag);

        View vSGD = findViewById(R.id.SGD);
        final TextView tvBuyRateSGD = (TextView)vSGD.findViewById(R.id.buyRate);
        final ImageView imageViewSGD = (ImageView)vSGD.findViewById(R.id.flag);

        View vJPY = findViewById(R.id.JPY);
        final TextView tvBuyRateJPY = (TextView)vJPY.findViewById(R.id.buyRate);
        final ImageView imageViewJPY = (ImageView)vJPY.findViewById(R.id.flag);

        View vKRW = findViewById(R.id.KRW);
        final TextView tvBuyRateKRW = (TextView)vKRW.findViewById(R.id.buyRate);
        final ImageView imageViewKRW = (ImageView)vKRW.findViewById(R.id.flag);

        View vNZD = findViewById(R.id.NZD);
        final TextView tvBuyRateNZD = (TextView)vNZD.findViewById(R.id.buyRate);
        final ImageView imageViewNZD = (ImageView)vNZD.findViewById(R.id.flag);

        View vUSD = findViewById(R.id.USD);
        final TextView tvBuyRateUSD = (TextView)vUSD.findViewById(R.id.buyRate);
        final ImageView imageViewUSD = (ImageView)vUSD.findViewById(R.id.flag);

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

                // This show currency based on THB Bath

                tvBuyRateAUD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("AUD")));
                tvBuyRateCNY.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("CNY")));
                tvBuyRateEUR.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("EUR")));
                tvBuyRateGBP.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("GBP")));
                tvBuyRateHKD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("HKD")));
                tvBuyRateSGD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("SGD")));
                tvBuyRateJPY.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("JPY")));
                tvBuyRateKRW.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("KRW")));
                tvBuyRateNZD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("NZD")));
                tvBuyRateUSD.setText(""+formatter.format(latest.getRate("THB")/latest.getRate("USD")));


                //imageViewAUD.setImageResource(R.drawable.ic_launcher_background);
                //getCurrencyRate(latest);

            }

            @Override
            public void onFailure(Call<FixerObject> call, Throwable t) {

            }
        });


    }

}
