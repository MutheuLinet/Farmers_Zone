package com.farmersgroup.farmerszone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionsActivity extends AppCompatActivity {

    @BindView(R.id.textViewKaribu) TextView mTextViewKaribu;

    @BindView(R.id.btnFruit) Button mBtnFruits;
    @BindView(R.id.btnNuts) Button mBtnNuts;
    @BindView(R.id.btnHerbs) Button mBtnHerbs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mTextViewKaribu.setText("Hi, " + name + ". "+ "Please select a Category.");

        mBtnFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionsActivity.this, FruitActivity.class);
                startActivity(intent);
            }
        });

        mBtnNuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActionsActivity.this, "Nuts Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        mBtnHerbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActionsActivity.this, "Herbs Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}