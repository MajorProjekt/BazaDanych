package com.example.major.bazadanychlab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.test) void addNewStudent(View view){
        Intent intentAddStudent = new Intent(this, FormStudent.class);
        startActivity(intentAddStudent);
    }

    @OnClick(R.id.test2) void wyswietlGrupa(View view){
        Intent intent2 = new Intent(this, WyswietlGrupa.class);
        startActivity(intent2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); }
}
