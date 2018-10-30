package com.example.i354889.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class SettingsActivity extends AppCompatActivity {

    private EditText amount,minInput,maxInput;
    private Button okButton;
    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        amount = findViewById(R.id.settings_amount_input);
        minInput=findViewById(R.id.settings_min_input);
        maxInput=findViewById(R.id.settings_max_input);
        okButton = findViewById(R.id.settings_ok_button);
         sf = getSharedPreferences("settings",MODE_PRIVATE);
        amount.setText(String.valueOf(sf.getInt("amount",10)));
        minInput.setText(String.valueOf(sf.getInt("min",1)));
        maxInput.setText(String.valueOf(sf.getInt("max",3)));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText()!=null && !amount.getText().toString().equals("")){
                    try {
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putInt("amount",Integer.valueOf(amount.getText().toString()));
                        editor.putInt("min",Integer.valueOf(minInput.getText().toString()));
                        editor.putInt("max",Integer.valueOf(maxInput.getText().toString()));
                        editor.apply();
                        Toast toast = Toast.makeText(SettingsActivity.this,"Zaktualizowano ilość", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (Exception e) {
                        Log.e("SettingsActivity", "nie udało się sparsować liczby");
                        showError();
                    }

                } else {
                    showError();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        amount.setText(String.valueOf(sf.getInt("amount",10)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()){
            case "Main":
               Intent intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void showError() {
        final Toast error = Toast.makeText(this, "Wprowadzono błędny format", Toast.LENGTH_SHORT);
        error.show();
    }
}
