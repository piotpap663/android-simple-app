package com.example.i354889.lab1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.utils.FiguresListAdapter;
import com.example.i354889.lab1.utils.ListGenerator;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.main_listview);
        List<DisplayFigura> showList = ListGenerator.getList();
        FiguresListAdapter figuresListAdapter = new FiguresListAdapter(this,R.layout.single_row,showList);
        listView.setAdapter(figuresListAdapter);
    }
}
