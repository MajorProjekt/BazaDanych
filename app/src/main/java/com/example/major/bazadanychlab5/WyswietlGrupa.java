package com.example.major.bazadanychlab5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.major.bazadanychlab5.bazaDanych.DatabaseHelper;
import com.example.major.bazadanychlab5.bazaDanych.DbGrupa;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WyswietlGrupa extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private DatabaseHelper databaseHelper = null;

    private int selectPosition = -1;
    private Dao<DbGrupa, Integer> dbGrupaDao;
    private List<DbGrupa> dbGrupaList;

    @Bind(R.id.listView) ListView listView;

    @OnClick(R.id.bDodaj) void dodajStudent(View view){
        Intent intent = new Intent(this, FormGrupa.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_grupa);
        ButterKnife.bind(this);

        try {
            dbGrupaDao = getHelper().getDbGrupaDao();
            dbGrupaList = dbGrupaDao.queryForAll();
            final LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(R.layout.lista_grup, listView, false);
            listView.setAdapter(new DbGrupaArrayAdapter(this, R.layout.lista_grup,dbGrupaList, dbGrupaDao));
            listView.addHeaderView(view);
            listView.setOnItemLongClickListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void showDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to delete?");
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    dbGrupaDao.delete(dbGrupaList.get(selectPosition));
                    dbGrupaList.remove(selectPosition);
                    listView.invalidateViews();
                    selectPosition = -1;
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0)
        {
            selectPosition = position - 1;
            showDialog();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
