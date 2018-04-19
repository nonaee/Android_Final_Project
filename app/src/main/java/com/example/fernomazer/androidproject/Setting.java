package com.example.fernomazer.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

        Spinner lan = findViewById(R.id.spinnerLanguage);
        String[] language = new String[]{"English", "Thai", "China" , "Japan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, language);
        lan.setAdapter(adapter);

        lan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                Intent intent = new Intent(Setting.this, MainActivity.class);
                intent.putExtra("Language",getSelectlanguage());
                startActivity(intent);
            }
        });
    }

}
