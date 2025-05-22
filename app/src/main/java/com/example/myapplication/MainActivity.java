package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button createAdvertBtn, listAdvertsBtn,showOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createAdvertBtn = findViewById(R.id.createAdvertBtn);
        listAdvertsBtn = findViewById(R.id.listAdvertsBtn);
        showOnMap=findViewById(R.id.showOnMap);

//        SQLiteDatabase db;
//        db = openOrCreateDatabase("NewLostFoundDB", MODE_PRIVATE, null);
//        Cursor c = db.rawQuery("SELECT * FROM adverts", null);
//        int colCount = c.getColumnCount();
//        while (c.moveToNext()) {
//            StringBuilder sb = new StringBuilder("Row: ");
//            for (int i = 0; i < colCount; i++) {
//                sb.append(c.getColumnName(i)).append("=").append(c.getString(i)).append(" ");
//            }
//            Log.d("DEBUG", sb.toString());
//        }
//        c.close();



        createAdvertBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAdvertActivity.class);
            startActivity(intent);
        });

        listAdvertsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListAdvertActivity.class);
            startActivity(intent);
        });

        showOnMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);

        });


    }
}