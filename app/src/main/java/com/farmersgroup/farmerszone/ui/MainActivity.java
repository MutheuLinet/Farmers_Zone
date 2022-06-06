package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.farmersgroup.farmerszone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.buttonName) Button mButtonName;
    @BindView(R.id.editTextName) EditText mEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditTextName.getText().toString();
                Intent intent = new Intent(MainActivity.this, ActionsActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                Log.d(TAG, "onClick: btnclick");
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}