package com.example.major.bazadanychlab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.major.bazadanychlab5.bazaDanych.DatabaseHelper;
import com.example.major.bazadanychlab5.bazaDanych.DbGrupa;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormGrupa extends AppCompatActivity {

    private int idGrupa = 0;
    private String nazwaGrupy;
    private DatabaseHelper databaseHelper = null;


    @Bind(R.id.etNazwaGrupy) TextView etNazwaGrupy;

    @OnClick(R.id.bWyslij) public void grupaWyslij(){
        if(idGrupa == 0){
            dodajGrupa();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupa);
        ButterKnife.bind(this);
    }

    public void dodajGrupa(){
        nazwaGrupy = etNazwaGrupy.getText().toString();
        if(nazwaGrupy.trim().length() > 0){
            final DbGrupa dbGrupa = new DbGrupa();
            dbGrupa.setNazwa(nazwaGrupy);

            try {
                final Dao<DbGrupa, Integer> dbGrupaDao = getHelper().getDbGrupaDao();
                dbGrupaDao.create(dbGrupa);
                reset();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Dodano grupe: " + nazwaGrupy, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Wprowadź wartość.", Toast.LENGTH_LONG).show();
        }
    }

    private void reset()
    {
        etNazwaGrupy.setText("");
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public FormGrupa(){};

    public FormGrupa(int idGrupa, String nazwaGrupy) {
        this.idGrupa = idGrupa;
        this.nazwaGrupy = nazwaGrupy;
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
