package com.example.hacktiv8assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView angka;
    Button count;
    EditText text;
    private int mCount = 0;
    private static final String SAVED_TEXT_KEY = "SAVED_TEXT_KEY";
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        angka = (TextView) findViewById(R.id.angka);
        count = (Button) findViewById(R.id.button);
        angka.setText(String.valueOf(mCount));

        text = (EditText) findViewById(R.id.etText);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        text.setText(prefs.getString(SAVED_TEXT_KEY,""));

        count.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCount++;
                angka.setText(String.valueOf(mCount));
            }
        });
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");
            angka.setText(String.valueOf(mCount));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SAVED_TEXT_KEY,text.getText().toString());
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_TEXT_KEY, text.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text.setText(savedInstanceState.getString(SAVED_TEXT_KEY));
    }
}