package com.example.major.bazadanychlab5.bazaDanych;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.major.bazadanychlab5.bazaDanych.DbGrupa;
import com.example.major.bazadanychlab5.bazaDanych.DbStudent;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "lab5.db";
    private static final int DB_VERSION = 1;
    private Dao<DbGrupa, Integer> dbGrupaDao;
    private Dao<DbStudent, Integer> dbStudentDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DbGrupa.class);
            TableUtils.createTable(connectionSource, DbStudent.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DbGrupa.class, true);
            TableUtils.dropTable(connectionSource, DbStudent.class, true);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }


    public Dao<DbGrupa, Integer> getDbGrupaDao() throws SQLException,
            java.sql.SQLException {
        if (dbGrupaDao == null) {
            dbGrupaDao = getDao(DbGrupa.class);
        }
        return dbGrupaDao;

    }

    public Dao<DbStudent, Integer> getDbStudentDao() throws SQLException,
            java.sql.SQLException {
        if (dbStudentDao == null) {
            dbStudentDao = getDao(DbStudent.class);
        }
        return dbStudentDao;

    }


}

