//package com.example.myapplication;
//
//import static android.content.ContentValues.TAG;
//
//import android.content.Intent;
//import android.net.http.UrlRequest;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.common.api.Status;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.widget.Autocomplete;
//import com.google.android.libraries.places.widget.AutocompleteActivity;
//import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
//import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class LocationPickerActivity extends AppCompatActivity {
//    private EditText locationInput;
//    private Button submitButton;
//    private String selectedAddress = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_picker);
//
//        locationInput = findViewById(R.id.locationInput);
//        submitButton = findViewById(R.id.submitButton);
//
//        // Initialize Places API
//        if (!Places.isInitialized()) {
//            String apiKey = getString(R.string.google_maps_key);
//            Places.initialize(getApplicationContext(), apiKey);
//        }
//
//        // Initialize the AutocompleteSupportFragment.
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.locationInput);
//
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//            }
//
//
//            @Override
//            public void onError(@NonNull Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
//
//
//
////        locationInput.setFocusable(false);
////        locationInput.setOnClickListener(v -> {
////            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
////            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this);
////            startActivityForResult(intent, 2001);
////        });
//
//        submitButton.setOnClickListener(v -> {
//            if (!selectedAddress.isEmpty()) {
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("selectedAddress", selectedAddress);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            } else {
//                Toast.makeText(this, "Please select a location first", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 2001) {
//            if (resultCode == RESULT_OK) {
//                Place place = Autocomplete.getPlaceFromIntent(data);
//                selectedAddress = place.getAddress();
//                locationInput.setText(selectedAddress);
//            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
//                Status status = Autocomplete.getStatusFromIntent(data);
//                Log.e("Places", "Error: " + status.getStatusMessage());
//            }
//        }
//    }
//}
//

package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class LocationPickerActivity extends AppCompatActivity {

    private Button submitButton;
    private String selectedAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        submitButton = findViewById(R.id.submitButton);

        // Initialize Places API
        if (!Places.isInitialized()) {
            String apiKey = getString(R.string.google_maps_key); // Add your API key in strings.xml
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Initialize AutocompleteSupportFragment
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.location_autocomplete_fragment);

        if (autocompleteFragment != null) {
            autocompleteFragment.setPlaceFields(Arrays.asList(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.ADDRESS
            ));

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place place) {
                    selectedAddress = place.getAddress();
                    Log.i(TAG, "Place selected: " + selectedAddress);
                }

                @Override
                public void onError(@NonNull Status status) {
                    Log.e(TAG, "Place selection error: " + status.getStatusMessage());
                }
            });
        }

        // Submit button click
        submitButton.setOnClickListener(v -> {
            if (!selectedAddress.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedAddress", selectedAddress);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Please select a location first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
