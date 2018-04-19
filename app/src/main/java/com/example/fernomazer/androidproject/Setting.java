package com.example.fernomazer.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    private String selectlanguage;

    public String getSelectlanguage() {
        return selectlanguage;
    }

    public void setSelectlanguage(String selectlanguage) {
        this.selectlanguage = selectlanguage;
    }


    protected TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        text1 = (TextView) findViewById(R.id.testText);
        Spinner dropdown = findViewById(R.id.spinnerLanguage);
        String[] language = new String[]{"English", "Thai", "China" , "Japan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, language);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getSelectedItem();
                setSelectlanguage(item.toString());
                text1.setText(getSelectlanguage());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
