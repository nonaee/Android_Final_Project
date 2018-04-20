package com.example.fernomazer.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Setting extends AppCompatActivity {
    private String selectlanguage;
    private Button saveBut;

    public String getSelectlanguage() {
        return selectlanguage;
    }

    public void setSelectlanguage(String selectlanguage) {
        this.selectlanguage = selectlanguage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        View menu = findViewById(R.id.menu);
        ImageButton imbDashboard = (ImageButton) menu.findViewById(R.id.dashboardBtn);
        ImageButton imbExchange = (ImageButton) menu.findViewById(R.id.exchangeBtn);
        ImageButton imbSetting = (ImageButton) menu.findViewById(R.id.settingBtn);

        imbSetting.setBackground(new ColorDrawable(Color.parseColor("#2F4F4F")));

        //Implement menu here
        imbDashboard.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(Setting.this, Dashboard.class);

                                                startActivity(intent);
                                            }
                                        }
        );

        imbExchange.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(Setting.this, Exchange.class);

                                               startActivity(intent);
                                           }
                                       }
        );
        imbSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, Setting.class);

                startActivity(intent);
            }
        });

        Spinner dropdown = findViewById(R.id.spinnerLanguage);
        String[] language = new String[]{"English", "Thai", "China" , "Japan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, language);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getSelectedItem();
                setSelectlanguage(item.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveBut = (Button) findViewById(R.id.setSave);
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, Dashboard.class);
                intent.putExtra("Language",getSelectlanguage());

                startActivity(intent);
            }
        });
    }

}
