package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.farmersgroup.farmerszone.R;
import com.google.firebase.database.DatabaseReference;

public class FavouritesActivity extends AppCompatActivity {

    private DatabaseReference mNewsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


    }
}