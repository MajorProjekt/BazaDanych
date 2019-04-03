package com.example.major.bazadanychlab5.bazaDanych;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Student")
public class DbStudent {
    @DatabaseField(id = true) private int idStudent;
    @DatabaseField(canBeNull = false) private String nazwisko;
    @DatabaseField(canBeNull = false) private String imie;
    @DatabaseField(canBeNull = false) private int wiek;
    @DatabaseField(foreign = true, canBeNull = false) private DbGrupa DbGrupa;

    public DbStudent(){};

    public DbStudent(int idStudent, String nazwisko, String imie, int wiek, DbGrupa DbGrupa) {
        this.idStudent = idStudent;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.wiek = wiek;
        this.DbGrupa = DbGrupa;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public DbGrupa getDbGrupa() {
        return DbGrupa;
    }

    public void setDbGrupa(DbGrupa DbGrupa) {
        this.DbGrupa = DbGrupa;
    }
}
