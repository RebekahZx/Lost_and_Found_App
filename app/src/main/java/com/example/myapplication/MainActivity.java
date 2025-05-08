package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button createAdvertBtn, listAdvertsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createAdvertBtn = findViewById(R.id.createAdvertBtn);
        listAdvertsBtn = findViewById(R.id.listAdvertsBtn);

        createAdvertBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAdvertActivity.class);
            startActivity(intent);
        });

        listAdvertsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListAdvertActivity.class);
            startActivity(intent);
        });


    }
}