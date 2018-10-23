package com.example.i354889.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class StatsActivity extends AppCompatActivity {

    SharedPreferences sf;
    private TextView squareAmount, triangleAmount,circleAmount;
    private TextView squareField, triangleField, circleField;
    private TextView squareAttribute, triangleAttribute, circleAttribute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        sf = getSharedPreferences("settings",MODE_PRIVATE);
        assignTextViews();
        fillTable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.stats_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getTitle().toString()){
            case "Main":
                intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case "Ustawienia":
                intent = new Intent(this,SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
        return true;
    }


    private void assignTextViews() {
        squareAmount = findViewById(R.id.stats_square_amount);
        triangleAmount = findViewById(R.id.stats_triangle_amount);
        circleAmount = findViewById(R.id.stats_circle_amount);
        squareField = findViewById(R.id.stats_square_field);
        triangleField = findViewById(R.id.stats_triangle_field);
        circleField = findViewById(R.id.stats_circle_field);
        squareAttribute=findViewById(R.id.stats_square_attributes);
        triangleAttribute = findViewById(R.id.stats_triangle_attributes);
        circleAttribute=findViewById(R.id.stats_circle_attributes);

    }

    private void fillTable() {
        squareAmount.setText(new DecimalFormat("##.##").format(sf.getInt("square_amount",0)));
        triangleAmount.setText(new DecimalFormat("##.##").format(sf.getInt("triangle_amount",0)));
        circleAmount.setText(new DecimalFormat("##.##").format(sf.getInt("circle_amount",0)));
        squareField.setText(new DecimalFormat("##.##").format(sf.getFloat("square_field",0)));
        triangleField.setText(new DecimalFormat("##.##").format(sf.getFloat("triangle_field",0)));
        circleField.setText(new DecimalFormat("##.##").format(sf.getFloat("circle_field",0)));
        squareAttribute.setText(new DecimalFormat("##.##").format(sf.getFloat("square_attribute",0)));
        triangleAttribute.setText(new DecimalFormat("##.##").format(sf.getFloat("triangle_attribute",0)));
        circleAttribute.setText(new DecimalFormat("##.##").format(sf.getFloat("circle_attribute",0)));


    }

}
