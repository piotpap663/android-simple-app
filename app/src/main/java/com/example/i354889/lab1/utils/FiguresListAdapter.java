package com.example.i354889.lab1.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i354889.lab1.R;
import com.example.i354889.lab1.model.DisplayFigura;

import java.text.DecimalFormat;
import java.util.List;

public class FiguresListAdapter extends ArrayAdapter<DisplayFigura> {
    public FiguresListAdapter(@NonNull Context context, int resource, @NonNull List<DisplayFigura> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DisplayFigura displayFigura = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.main_shape_image);
        TextView attribute1 = convertView.findViewById(R.id.main_attribute_1);
        TextView attribute2 = convertView.findViewById(R.id.main_attribute_2);

        image.setImageResource(0);
        image.setImageResource(displayFigura.getDrawablePath());
        attribute1.setText(new DecimalFormat("##.##").format(displayFigura.getPole()));
        attribute2.setText(new DecimalFormat("##.##").format(displayFigura.getCecha()));
        return convertView;
    }
}
