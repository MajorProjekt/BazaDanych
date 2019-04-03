package com.example.major.bazadanychlab5.bazaDanych;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Grupa")
public class DbGrupa {
@DatabaseField(id = true) private int idGrupa;
@DatabaseField(canBeNull = false) private String nazwa;

    public DbGrupa(){};

    public DbGrupa(int idGrupa, String nazwa) {
        this.idGrupa = idGrupa;
        this.nazwa = nazwa;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
