package com.example.i354889.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.model.Statistics;
import com.example.i354889.lab1.utils.FiguresListAdapter;
import com.example.i354889.lab1.utils.ListGenerator;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FiguresListAdapter figuresListAdapter;
    List<DisplayFigura> showList;
    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.main_listview);
        sf =getSharedPreferences("settings", MODE_PRIVATE);
        showList = ListGenerator.getList(sf.getInt("amount",10));
         figuresListAdapter = new FiguresListAdapter(this,R.layout.single_row,showList);
        listView.setAdapter(figuresListAdapter);
        updateAppInfo();
        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sf.getInt("amount",10)!=showList.size()){
          /*  showList = ListGenerator.getList(sf.getInt("amount",10));
            figuresListAdapter.notifyDataSetChanged();*/
            showList = ListGenerator.getList(sf.getInt("amount",10));
            figuresListAdapter = new FiguresListAdapter(this,R.layout.single_row,showList);
            listView.setAdapter(figuresListAdapter);
            updateAppInfo();
            registerForContextMenu(listView);
            Toast toast = Toast.makeText(this, "Zaktualizowano liczbę figur",Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.main_listview) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.add("Usuń");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
       showList.remove(position);
       figuresListAdapter.notifyDataSetChanged();
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("amount",showList.size());
        editor.apply();
       updateAppInfo();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.first_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
       switch (item.getTitle().toString()){
           case "Ustawienia":
               intent = new Intent(this,SettingsActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               break;
           case "Statystyki":
               intent = new Intent(this,StatsActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
       }
        return true;
    }

    private void updateAppInfo() {
        Statistics statistics = new Statistics();
            for (DisplayFigura df: showList) {
                switch (df.getType()){
                    case CIRCLE:
                       statistics.setCircleAmount(statistics.getCircleAmount()+1);
                       statistics.setCircleAttributeSum(statistics.getCircleAttributeSum()+df.getCecha());
                       statistics.setCircleFieldSum(statistics.getCircleFieldSum()+df.getPole());
                       break;
                    case SQUARE:
                        statistics.setSquareAmount(statistics.getSquareAmount()+1);
                        statistics.setSquareAttributeSum(statistics.getSquareAttributeSum()+df.getCecha());
                        statistics.setSquareFieldSum(statistics.getSquareFieldSum()+df.getPole());
                        break;
                    case TRIANGLE:
                        statistics.setTriangleAmount(statistics.getTriangleAmount()+1);
                        statistics.setTriangleAttributeSum(statistics.getTriangleAttributeSum()+df.getCecha());
                        statistics.setTriangleFieldSum(statistics.getTriangleFieldSum()+df.getPole());
                }
            }
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("circle_amount",statistics.getCircleAmount());
        editor.putFloat("circle_field",(float)statistics.getCircleFieldSum());
        editor.putFloat("circle_attribute",statistics.getCircleAttributeSum());
        editor.putInt("square_amount",statistics.getSquareAmount());
        editor.putFloat("square_field",(float)statistics.getSquareFieldSum());
        editor.putFloat("square_attribute",statistics.getSquareAttributeSum());
        editor.putInt("triangle_amount",statistics.getTriangleAmount());
        editor.putFloat("triangle_field",(float)statistics.getTriangleFieldSum());
        editor.putFloat("triangle_attribute",statistics.getTriangleAttributeSum());
        editor.apply();
    }
}
