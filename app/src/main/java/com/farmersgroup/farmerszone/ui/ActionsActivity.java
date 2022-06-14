package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.farmersgroup.farmerszone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionsActivity extends AppCompatActivity {

//    @BindView(R.id.textViewSelect) TextView mTextViewKaribu;

    @BindView(R.id.btnFruit) Button mBtnFruits;
    @BindView(R.id.btnNuts) Button mBtnNuts;
    @BindView(R.id.btnHerbs) Button mBtnHerbs;

    private static final String TAG = ActionsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        mTextViewKaribu.setText("Hi, " + name + ". "+ "Please select a Category.");

        mBtnFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionsActivity.this, BrowseAllActivity.class);
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
    @Override
    protected void onResume() {
        super.onResume();

    }
}