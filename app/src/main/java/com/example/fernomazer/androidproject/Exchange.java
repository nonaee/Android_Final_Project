package com.example.fernomazer.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exchange extends AppCompatActivity {

    private String sourceCurrencyText;
    private String targetCurrencyText;
    private double sourceCurrency;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        View menu = findViewById(R.id.menu);
        ImageButton imbDashboard = (ImageButton) menu.findViewById(R.id.dashboardBtn);
        ImageButton imbExchange = (ImageButton) menu.findViewById(R.id.exchangeBtn);
        ImageButton imbSetting = (ImageButton) menu.findViewById(R.id.settingBtn);

        imbExchange.setBackground(new ColorDrawable(Color.parseColor("#2F4F4F")));

        ImageView imvExchange = (ImageView) findViewById(R.id.imvExchange);


        //Implement menu here
        imbDashboard.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(Exchange.this, Dashboard.class);

                                                startActivity(intent);
                                            }
                                        }
        );

        imbExchange.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(Exchange.this, Exchange.class);

                                               startActivity(intent);
                                           }
                                       }
        );
        imbSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exchange.this, Setting.class);

                startActivity(intent);
            }
        });

        final EditText sourceET = (EditText) findViewById(R.id.sourceET);
        //final EditText targetET = (EditText) findViewById(R.id.targetET);
        final TextView targetTV = (TextView) findViewById(R.id.targetTV);
        String[] arrayCurrency = new String[]
                {"AUD","CNY","GBP","HKD","JPY","KRW","NZD","SGD","THB","USD","EUR"};

        final Spinner spinnerCurrencySource = (Spinner) findViewById(R.id.spinnerSource);
        Spinner spinnerCurrencyTarget = (Spinner) findViewById(R.id.spinnerTarget);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayCurrency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrencySource.setAdapter(adapter);
        spinnerCurrencyTarget.setAdapter(adapter);

        spinnerCurrencySource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //get source currency
                Object itemSource = adapterView.getItemAtPosition(i);
                sourceCurrencyText = itemSource.toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerCurrencyTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object itemTarget = adapterView.getItemAtPosition(i);
                targetCurrencyText = itemTarget.toString();

                sourceCurrency = Double.parseDouble(sourceET.getText().toString());
                Log.d("NatradaC's Log ","Source Currency | currency amount: " + sourceCurrency+", currency rate: "+ sourceCurrencyText + ", target rate: "+targetCurrencyText);


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
                        NumberFormat formatter = new DecimalFormat("#0.0000");

                       //targetET.setText(""+formatter.format(sourceCurrency*(latest.getRate(targetCurrencyText)/latest.getRate(sourceCurrencyText))));

                        targetTV.setText(""+formatter.format(sourceCurrency*(latest.getRate(targetCurrencyText)/latest.getRate(sourceCurrencyText))));
                    }

                    @Override
                    public void onFailure(Call<FixerObject> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        imvExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sourceCurrency = Double.parseDouble(sourceET.getText().toString());

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
                        NumberFormat formatter = new DecimalFormat("#0.0000");

                        //targetET.setText(""+formatter.format(sourceCurrency*(latest.getRate(targetCurrencyText)/latest.getRate(sourceCurrencyText))));

                        targetTV.setText(""+formatter.format(sourceCurrency*(latest.getRate(targetCurrencyText)/latest.getRate(sourceCurrencyText))));
                    }

                    @Override
                    public void onFailure(Call<FixerObject> call, Throwable t) {

                    }
                });
            }
        });



    }

}
