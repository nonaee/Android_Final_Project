package com.example.fernomazer.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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

        View menu = findViewById(R.id.menu);
        ImageButton imbDashboard = (ImageButton) menu.findViewById(R.id.dashboardBtn);
        ImageButton imbExchange = (ImageButton) menu.findViewById(R.id.exchangeBtn);
        ImageButton imbSetting = (ImageButton) menu.findViewById(R.id.settingBtn);


        //Implement menu here





        //final TextView tvBuyRateTHB = (TextView) findViewById(R.id);
        View vAUD = findViewById(R.id.AUD);
        final TextView tvBuyRateAUD = (TextView)vAUD.findViewById(R.id.buyRate);
        final ImageView imageViewAUD = (ImageView)vAUD.findViewById(R.id.flag);
        final TextView tvCountryAUD = (TextView)vAUD.findViewById(R.id.country);
        final ImageButton imbAUD = (ImageButton) vAUD.findViewById(R.id.goExchange);

        View vCNY = findViewById(R.id.CNY);
        final TextView tvBuyRateCNY = (TextView)vCNY.findViewById(R.id.buyRate);
        final ImageView imageViewCNY = (ImageView)vCNY.findViewById(R.id.flag);
        final TextView tvCountryCNY = (TextView)vCNY.findViewById(R.id.country);
        ImageButton imbCNY = (ImageButton) vCNY.findViewById(R.id.goExchange);


        View vEUR = findViewById(R.id.EUR);
        final TextView tvBuyRateEUR = (TextView)vEUR.findViewById(R.id.buyRate);
        final ImageView imageViewEUR = (ImageView)vEUR.findViewById(R.id.flag);
        final TextView tvCountryEUR = (TextView)vEUR.findViewById(R.id.country);
        ImageButton imbEUR = (ImageButton) vEUR.findViewById(R.id.goExchange);


        View vGBP = findViewById(R.id.GBP);
        final TextView tvBuyRateGBP = (TextView)vGBP.findViewById(R.id.buyRate);
        final ImageView imageViewGBP = (ImageView)vGBP.findViewById(R.id.flag);
        final TextView tvCountryGBP = (TextView)vGBP.findViewById(R.id.country);
        ImageButton imbGBP = (ImageButton) vEUR.findViewById(R.id.goExchange);


        View vHKD = findViewById(R.id.HKD);
        final TextView tvBuyRateHKD = (TextView)vHKD.findViewById(R.id.buyRate);
        final ImageView imageViewHKD = (ImageView)vHKD.findViewById(R.id.flag);
        final TextView tvCountryHKD = (TextView)vHKD.findViewById(R.id.country);
        ImageButton imbHKD = (ImageButton) vHKD.findViewById(R.id.goExchange);


        View vSGD = findViewById(R.id.SGD);
        final TextView tvBuyRateSGD = (TextView)vSGD.findViewById(R.id.buyRate);
        final ImageView imageViewSGD = (ImageView)vSGD.findViewById(R.id.flag);
        final TextView tvCountrySGD = (TextView)vSGD.findViewById(R.id.country);
        ImageButton imbSGD = (ImageButton) vSGD.findViewById(R.id.goExchange);


        View vJPY = findViewById(R.id.JPY);
        final TextView tvBuyRateJPY = (TextView)vJPY.findViewById(R.id.buyRate);
        final ImageView imageViewJPY = (ImageView)vJPY.findViewById(R.id.flag);
        final TextView tvCountryJPY = (TextView)vJPY.findViewById(R.id.country);
        ImageButton imbJPY = (ImageButton) vJPY.findViewById(R.id.goExchange);


        View vKRW = findViewById(R.id.KRW);
        final TextView tvBuyRateKRW = (TextView)vKRW.findViewById(R.id.buyRate);
        final ImageView imageViewKRW = (ImageView)vKRW.findViewById(R.id.flag);
        final TextView tvCountryKRW = (TextView)vKRW.findViewById(R.id.country);
        ImageButton imbKRW = (ImageButton) vKRW.findViewById(R.id.goExchange);


        View vNZD = findViewById(R.id.NZD);
        final TextView tvBuyRateNZD = (TextView)vNZD.findViewById(R.id.buyRate);
        final ImageView imageViewNZD = (ImageView)vNZD.findViewById(R.id.flag);
        final TextView tvCountryNZD = (TextView)vNZD.findViewById(R.id.country);
        ImageButton imbNZD = (ImageButton) vNZD.findViewById(R.id.goExchange);


        View vUSD = findViewById(R.id.USD);
        final TextView tvBuyRateUSD = (TextView)vUSD.findViewById(R.id.buyRate);
        final ImageView imageViewUSD = (ImageView)vUSD.findViewById(R.id.flag);
        final TextView tvCountryUSD = (TextView)vUSD.findViewById(R.id.country);
        ImageButton imbUSD = (ImageButton) vUSD.findViewById(R.id.goExchange);


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

                NumberFormat formatter = new DecimalFormat("#0.0000");

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


                imageViewAUD.setImageResource(R.drawable.ic_aud_flag);
                imageViewCNY.setImageResource(R.drawable.ic_cny_flag);
                imageViewEUR.setImageResource(R.drawable.is_eur_flag);
                imageViewGBP.setImageResource(R.drawable.ic_gbp_flag);
                imageViewHKD.setImageResource(R.drawable.ic_hkd_flag);
                imageViewJPY.setImageResource(R.drawable.ic_jpy_flag);
                imageViewKRW.setImageResource(R.drawable.ic_krw_flag);
                imageViewNZD.setImageResource(R.drawable.ic_nzd_flag);
                imageViewSGD.setImageResource(R.drawable.ic_sgd_flag);
                imageViewUSD.setImageResource(R.drawable.ic_usd_flag);

                tvCountryAUD.setText("AUD");
                tvCountryCNY.setText("CNY");
                tvCountryEUR.setText("EUR");
                tvCountryGBP.setText("GBP");
                tvCountryHKD.setText("HKD");
                tvCountryJPY.setText("JPY");
                tvCountryKRW.setText("KRW");
                tvCountryNZD.setText("NZD");
                tvCountrySGD.setText("SGD");
                tvCountryUSD.setText("USD");



/*
                imbAUD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Exchange exchange
                    }
                });
*/



            }

            @Override
            public void onFailure(Call<FixerObject> call, Throwable t) {

            }
        });


    }

}
