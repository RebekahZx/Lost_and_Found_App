////package com.example.myapplication;
////
////import android.app.Activity;
////import android.content.ContentValues;
////import android.database.sqlite.SQLiteDatabase;
////import android.os.Bundle;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.RadioButton;
////import android.widget.RadioGroup;
////import android.widget.Toast;
////
////public class AddAdvertActivity extends Activity {
////
////    EditText itemNameInput, descriptionInput, contactInput;
////    Button saveBtn;
////    DBHelper dbHelper;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.add_advert);
////
////        itemNameInput = findViewById(R.id.itemNameInput);
////        descriptionInput = findViewById(R.id.descriptionInput);
////        contactInput = findViewById(R.id.contactInput);
////        saveBtn = findViewById(R.id.saveBtn);
////
////        dbHelper = new DBHelper(this);
////
////        saveBtn.setOnClickListener(v -> {
////            String itemName = itemNameInput.getText().toString();
////            String description = descriptionInput.getText().toString();
////            String contact = contactInput.getText().toString();
////
////            if (itemName.isEmpty() || description.isEmpty() || contact.isEmpty()) {
////                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
////                return;
////            }
////
////            SQLiteDatabase db = dbHelper.getWritableDatabase();
//////            ContentValues values = new ContentValues();
//////            values.put("itemName", itemName);
//////            values.put("description", description);
//////            values.put("contact", contact);
//////            values.put("status", "Lost");
////            RadioGroup typeRadioGroup = findViewById(R.id.typeRadioGroup);
////            int selectedTypeId = typeRadioGroup.getCheckedRadioButtonId();
////            RadioButton selectedRadioButton = findViewById(selectedTypeId);
////            String type = selectedRadioButton.getText().toString(); // "Lost" or "Found"
////
////// Add the type value to the database insert logic
////            ContentValues values = new ContentValues();
////            values.put("type", type);
////            values.put("name", nameEditText.getText().toString());
////            values.put("phone", phoneEditText.getText().toString());
////            values.put("description", descriptionEditText.getText().toString());
////            values.put("location", locationEditText.getText().toString());
////            values.put("date", dateEditText.getText().toString());
////
////            SQLiteDatabase db = dbHelper.getWritableDatabase();
////            db.insert("adverts", null, values);
////            db.close();
////
////
////            long result = db.insert("adverts", null, values);
////            db.close();
////
////            if (result != -1) {
////                Toast.makeText(this, "Advert saved", Toast.LENGTH_SHORT).show();
////                finish(); // Close activity
////            } else {
////                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
////}
//package com.example.myapplication;
//
//import android.app.Activity;
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//public class AddAdvertActivity extends Activity {
//
//    EditText nameEditText, phoneEditText, descriptionEditText, locationEditText, dateEditText;
//    RadioGroup typeRadioGroup;
//    Button saveBtn;
//    DBHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_advert);
//
//        // Link UI elements
//        nameEditText = findViewById(R.id.itemNameInput);
//        phoneEditText = findViewById(R.id.contactInput);
//        descriptionEditText = findViewById(R.id.descriptionInput);
//        locationEditText = findViewById(R.id.locationEditText);
//        dateEditText = findViewById(R.id.dateEditText);
//        typeRadioGroup = findViewById(R.id.typeRadioGroup);
//        saveBtn = findViewById(R.id.saveBtn);
//
//        dbHelper = new DBHelper(this);
//
//        saveBtn.setOnClickListener(v -> {
//            String name = nameEditText.getText().toString();
//            String phone = phoneEditText.getText().toString();
//            String description = descriptionEditText.getText().toString();
//            String location = locationEditText.getText().toString();
//            String date = dateEditText.getText().toString();
//
//            if (name.isEmpty() || phone.isEmpty() || description.isEmpty()
//                    || location.isEmpty() || date.isEmpty()
//                    || typeRadioGroup.getCheckedRadioButtonId() == -1) {
//                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            RadioButton selectedRadioButton = findViewById(typeRadioGroup.getCheckedRadioButtonId());
//            String type = selectedRadioButton.getText().toString();
//
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put("type", type);
//            values.put("name", name);
//            values.put("phone", phone);
//            values.put("description", description);
//            values.put("location", location);
//            values.put("date", date);
//
//            long result = db.insert("adverts", null, values);
//            db.close();
//
//            if (result != -1) {
//                Toast.makeText(this, "Advert saved", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddAdvertActivity extends Activity {

    // Declare the EditText and Button variables
    EditText itemNameInput, descriptionInput, contactInput, dateEditText, locationEditText;
    Button saveBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_advert);

        // Initialize the EditText and Button views
        itemNameInput = findViewById(R.id.itemNameInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        contactInput = findViewById(R.id.contactInput);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);
        saveBtn = findViewById(R.id.saveBtn);

        // Initialize the database helper
        dbHelper = new DBHelper(this);

        // Set the onClickListener for the save button
        saveBtn.setOnClickListener(v -> {
            String itemName = itemNameInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String contact = contactInput.getText().toString();
            String date = dateEditText.getText().toString();
            String location = locationEditText.getText().toString();

            // Check if any of the fields are empty
            if (itemName.isEmpty() || description.isEmpty() || contact.isEmpty() || date.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the selected type (Lost/Found) from the RadioGroup
            RadioGroup typeRadioGroup = findViewById(R.id.typeRadioGroup);
            int selectedTypeId = typeRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedTypeId);
            String type = selectedRadioButton.getText().toString(); // "Lost" or "Found"

            // Insert the values into the database
            ContentValues values = new ContentValues();
            values.put("type", type);
            values.put("name", itemName);
            values.put("phone", contact);
            values.put("description", description);
            values.put("location", location);
            values.put("date", date);

            // Get writable database and insert the advert
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long result = db.insert("adverts", null, values);
            db.close();

            // Check if the insert was successful
            if (result != -1) {
                Toast.makeText(this, "Advert saved", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after saving
            } else {
                Toast.makeText(this, "Failed to save advert", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
