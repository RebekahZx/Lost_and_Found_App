package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdvertDetailActivity extends Activity {

    DBHelper dbHelper;
    TextView advertDetailsTextView;
    Button deleteBtn;
    int advertId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_detail);

        advertDetailsTextView = findViewById(R.id.advertDetailsTextView);
        deleteBtn = findViewById(R.id.deleteBtn);

        dbHelper = new DBHelper(this);

        // Get the advert ID passed from the previous activity
        advertId = getIntent().getIntExtra("advertId", -1);

        if (advertId != -1) {
            loadAdvertDetails(advertId);
        }

        deleteBtn.setOnClickListener(v -> {
            deleteAdvert(advertId);
        });
    }

    private void loadAdvertDetails(int advertId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM adverts WHERE id = ?", new String[]{String.valueOf(advertId)});

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

            // Display advert details in the TextView
            String advertDetails = "Name: " + name + "\nPhone: " + phone + "\nDescription: " + description +
                    "\nLocation: " + location + "\nDate: " + date + "\nType: " + type;

            advertDetailsTextView.setText(advertDetails);
        }

        cursor.close();
        db.close();
    }

    private void deleteAdvert(int advertId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("adverts", "id = ?", new String[]{String.valueOf(advertId)});

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Advert deleted", Toast.LENGTH_SHORT).show();
            finish();  // Close the detail activity and go back to the list
        } else {
            Toast.makeText(this, "Failed to delete advert", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
