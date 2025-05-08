package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdvertActivity extends Activity {

    DBHelper dbHelper;
    ArrayList<String> advertsList;
    ArrayList<Integer> advertIdsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adverts);

        ListView advertsListView = findViewById(R.id.advertsList_View);

        dbHelper = new DBHelper(this);
        advertsList = new ArrayList<>();
        advertIdsList = new ArrayList<>();

        loadAdverts(advertsListView);

        advertsListView.setOnItemClickListener((parent, view, position, id) -> {
            // Get the advert ID from the list
            int advertId = advertIdsList.get(position);
            Intent intent = new Intent(ListAdvertActivity.this, AdvertDetailActivity.class);
            intent.putExtra("advertId", advertId);
            startActivity(intent);
        });
    }

    public void loadAdverts(ListView advertsListView) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM adverts", null);

        if (cursor.moveToFirst()) {
            do {
                // Retrieve the advert details
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

                // Add advert details to the list for display
                String display = "Item: " + name + "\nDescription: " + description +
                        "\nContact: " + phone + "\nLocation: " + location +
                        "\nDate: " + date + "\nType: " + type;

                advertsList.add(display);
                advertIdsList.add(id);  // Store advert ID for later use
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Set the adapter to display the adverts in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, advertsList);
        advertsListView.setAdapter(adapter);
    }
}
