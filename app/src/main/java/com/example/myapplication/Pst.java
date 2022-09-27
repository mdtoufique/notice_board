package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Pst extends ArrayAdapter<Info> {

    private Context context;
    private ArrayList<Info> hi;
    public Pst(@NonNull Context context, @NonNull ArrayList<Info> hi) {
        super(context, R.layout.row,hi);
        this.context=context;
        this.hi=hi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.row,parent,false);
        TextView nm= convertView.findViewById(R.id.t1);
        TextView tm= convertView.findViewById(R.id.t2);
        TextView ps= convertView.findViewById(R.id.t3);
        nm.setText(hi.get(position).getMail());
        tm.setText(hi.get(position).getChk());
        ps.setText(hi.get(position).getTime());
        return convertView;
    }
}
