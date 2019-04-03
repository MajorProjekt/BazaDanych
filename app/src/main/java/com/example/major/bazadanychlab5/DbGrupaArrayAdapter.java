package com.example.major.bazadanychlab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.major.bazadanychlab5.bazaDanych.DbGrupa;
import com.j256.ormlite.dao.Dao;

import java.util.List;



public class DbGrupaArrayAdapter extends ArrayAdapter<String> {


    private LayoutInflater layoutInflater;
    private List list;
    private Dao<DbGrupa, Integer> dbGrupaDao;



    public DbGrupaArrayAdapter(Context context, int resource, List objects, Dao<DbGrupa, Integer> dbGrupaDao) {
        super(context, resource, objects);
        this.list = objects;
        this.dbGrupaDao = dbGrupaDao;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.lista_grup, parent, false);
        }
        if(list.get(position).getClass().isInstance(new DbGrupa())){
            final DbGrupa dbGrupa = (DbGrupa)list.get(position);
            ((TextView)convertView.findViewById(R.id.lgNazwa)).setText(dbGrupa.getNazwa());
        }

        return convertView;
    }
}
