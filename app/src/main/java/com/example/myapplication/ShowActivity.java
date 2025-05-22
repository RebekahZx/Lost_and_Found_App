package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.databinding.ActivityShowBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityShowBinding binding;
    private SQLiteDatabase db;  // database instance
    private List<LatLng> locations = new ArrayList<>();  // list of locations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize or open your SQLite database (assumes a helper or path)
        db = openOrCreateDatabase("NewLostFoundDB", MODE_PRIVATE, null);  // adjust as needed

        // Get the map fragment and async notify when ready
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Fetch locations from the database
        Cursor cursor = db.rawQuery("SELECT name, lat, lng FROM adverts", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            double lat = cursor.getDouble(1);
            double lng = cursor.getDouble(2);
            LatLng position = new LatLng(lat, lng);
            locations.add(position);  // store to move camera later
            mMap.addMarker(new MarkerOptions().position(position).title(name));
        }
        cursor.close();

//        float zoomLevel = 15.0f; // Range is typically 2.0 (world) to 21.0+ (building)



        // Move camera to the first marker, if available
        if (!locations.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locations.get(2), 13));
        }
    }
}
