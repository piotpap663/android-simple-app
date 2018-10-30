package com.example.i354889.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.model.Statistics;
import com.example.i354889.lab1.utils.FiguresListAdapter;
import com.example.i354889.lab1.utils.ListGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FiguresListAdapter figuresListAdapter;
    List<DisplayFigura> showList;
    SharedPreferences sf;
    TextView shapeTextView,fieldTextView,attributeTextView;
    int min,max;
    private final static String USUN = "Usuń";
    private final static String DUPLIKUJ = "Duplikuj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.main_listview);
        shapeTextView = findViewById(R.id.main_shape_textview);
        attributeTextView = findViewById(R.id.main_attribute_textview);
        fieldTextView=findViewById(R.id.main_field_textview);
        sf =getSharedPreferences("settings", MODE_PRIVATE);
        max = sf.getInt("max",3);
        min = sf.getInt("min",1);
        showList = ListGenerator.getList(
                sf.getInt("amount",10), min,max);
         figuresListAdapter = new FiguresListAdapter(this,R.layout.single_row,showList);
        listView.setAdapter(figuresListAdapter);
        updateAppInfo();
        setListeners();
        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sf.getInt("amount",10)!=showList.size()
                || min!=sf.getInt("min",1)
                || max!=sf.getInt("max",3)  ){
          /*  showList = ListGenerator.getList(sf.getInt("amount",10));
            figuresListAdapter.notifyDataSetChanged();*/
          min=sf.getInt("min",1);
          max=sf.getInt("max",3);
            showList = ListGenerator.getList(
                    sf.getInt("amount",10),min,max);
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
            menu.add(USUN);
            menu.add(DUPLIKUJ);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if(item.getTitle().equals(USUN)){
            showList.remove(position);
        } else if (item.getTitle().equals(DUPLIKUJ)) {
            DisplayFigura displayFigura = showList.get(position);
            showList.add(displayFigura);
        }

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

    private void setListeners() {
        fieldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(SortType.FIELD);
            }
        });

        attributeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(SortType.ATTRIBUTE);
            }
        });
        shapeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListView(SortType.SHAPE);
            }
        });
    }


    private void sortListView(SortType type) {

        switch (type) {
            case SHAPE:
                Collections.sort(showList, new Comparator<DisplayFigura>() {
                    @Override
                    public int compare(DisplayFigura o1, DisplayFigura o2) {
                        return o1.getType().getName().compareTo(o2.getType().getName());
                    }
                });
                resetTextViews();
                shapeTextView.setText(R.string.shape_arrow_down);
                break;
            case FIELD:
                Collections.sort(showList, new Comparator<DisplayFigura>() {
                    @Override
                    public int compare(DisplayFigura o1, DisplayFigura o2) {
                        return Double.compare(o1.getPole(),o2.getPole());
                    }
                });
                resetTextViews();
                fieldTextView.setText(R.string.field_arrow_down);
                break;
            case ATTRIBUTE:
                Collections.sort(showList, new Comparator<DisplayFigura>() {
                    @Override
                    public int compare(DisplayFigura o1, DisplayFigura o2) {
                        return Float.compare(o1.getCecha(),o2.getCecha());
                    }
                });
                resetTextViews();
                attributeTextView.setText(R.string.attribute_arrow_down);
                break;
        }
        figuresListAdapter.notifyDataSetChanged();
    }

    private void resetTextViews() {
        fieldTextView.setText("POLE");
        shapeTextView.setText("KSZTAŁT");
        attributeTextView.setText("ATRYBUT");
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

    private enum SortType{
        SHAPE,
        FIELD,
        ATTRIBUTE
    }
}
