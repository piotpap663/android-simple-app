package com.example.i354889.lab1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.utils.FiguresListAdapter;
import com.example.i354889.lab1.utils.ListGenerator;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListAdapter listAdapter;
    private ListView listView;
    private FiguresListAdapter figuresListAdapter;
    List<DisplayFigura> showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.main_listview);
        showList = ListGenerator.getList();
         figuresListAdapter = new FiguresListAdapter(this,R.layout.single_row,showList);
        listView.setAdapter(figuresListAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.main_listview) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            DisplayFigura displayFigura = (DisplayFigura) lv.getItemAtPosition(acmi.position);
            menu.add("Usuń");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
       showList.remove(position);
       figuresListAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.first_menu,menu);
        return true;
    }

    private void updateAppInfo() {

    }
}
