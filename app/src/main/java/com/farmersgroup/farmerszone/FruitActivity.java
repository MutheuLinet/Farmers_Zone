package com.farmersgroup.farmerszone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitActivity extends AppCompatActivity {


    @BindView(R.id.fruitList) ListView mFruitList;
    private String[] fruits = {"Banana","Orange", "Cucumber", "Mango", "Pineapple", "Passion", "Strawberry", "Grape", "Apple" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fruits);
        mFruitList.setAdapter(adapter);

    }
}