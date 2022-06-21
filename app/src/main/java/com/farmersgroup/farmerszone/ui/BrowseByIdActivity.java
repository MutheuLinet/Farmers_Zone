package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.adapters.ResultByIdRecViewAdapter;
import com.farmersgroup.farmerszone.models.BrowseByIdResponse;
import com.farmersgroup.farmerszone.models.ResultById;
import com.farmersgroup.farmerszone.models.User;
import com.farmersgroup.farmerszone.network.Api;
import com.farmersgroup.farmerszone.network.ApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseByIdActivity extends AppCompatActivity {

    private ResultById resultById;
    public static final String TAG = BrowseAllActivity.class.getSimpleName();
    @BindView(R.id.resultsByIdRecyclerView) RecyclerView mResultsByIdRecycleView;
    @BindView(R.id.btnSave) Button mBtnSave;
    Api api;
    private FirebaseAuth mAuth; //    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener; //    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_by_id);
        ButterKnife.bind(this);


        resultById= new ResultById();
        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference("saved");
        DatabaseReference userRef = firebaseDatabase.getReference("saved");

//        Button mBtnSave = findViewById(R.id.btnSave);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                String userid=firebaseUser.getUid();

//                Toast.makeText(RegisterActivity.this, "Registration Complete!",
//                        Toast.LENGTH_SHORT).show();

                String user_Id = mAuth.getCurrentUser().getUid();

//                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference("saved").child(user_Id);
//                databaseReference.child(result.getKey()).setValue(result_);

                DatabaseReference saved = FirebaseDatabase
                        .getInstance()
                        .getReference("saved")
                        .child(user_Id);

                DatabaseReference pushRef = saved.push();
                String pushId = pushRef.getKey();
                resultById.setPushId(pushId);
                pushRef.setValue(resultById);

//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String uid = user.getUid();
//
//
//                DatabaseReference saved = FirebaseDatabase
//                        .getInstance()
//                        .getReference("saved")
//                        .child(uid);
//
//                DatabaseReference pushRef = saved.push();
//                String pushId = pushRef.getKey();
//                resultById.setPushId(pushId);
//                pushRef.setValue(resultById);
//
//            Ref.push().setValue(mDatum);
                Toast.makeText(getApplicationContext(), "Item Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BrowseByIdActivity.this, FavouritesActivity.class);
                startActivity(intent);
            }
        });

        ResultById resultById = (ResultById) getIntent().getSerializableExtra("tfvitem"); //search parameter with value of tfvitem name
        api = ApiClient.getClient();
        Call<BrowseByIdResponse> call = api.getItemById(resultById.getTfvname());

        call.enqueue(new Callback<BrowseByIdResponse>() {
            @Override
            public void onResponse(Call<BrowseByIdResponse> call, Response<BrowseByIdResponse> response) {
                Log.d(TAG, "onResponse: resp");
                if (response.isSuccessful()){
                    List<ResultById> allItems = response.body().getResults();

                    ResultByIdRecViewAdapter adapter = new ResultByIdRecViewAdapter(allItems, BrowseByIdActivity.this);

                    adapter.setResultList(allItems);
                    mResultsByIdRecycleView.setAdapter(adapter);
                    mResultsByIdRecycleView.setLayoutManager(new LinearLayoutManager(BrowseByIdActivity.this));//getApplicationContext()
                }
            }
            @Override
            public void onFailure(Call<BrowseByIdResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}