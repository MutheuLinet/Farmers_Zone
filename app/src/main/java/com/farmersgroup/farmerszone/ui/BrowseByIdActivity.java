package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.adapters.ResultByIdRecViewAdapter;
import com.farmersgroup.farmerszone.models.BrowseByIdResponse;
import com.farmersgroup.farmerszone.models.ResultById;
import com.farmersgroup.farmerszone.network.Api;
import com.farmersgroup.farmerszone.network.ApiClient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseByIdActivity extends AppCompatActivity {

    public static final String TAG = BrowseAllActivity.class.getSimpleName();
    @BindView(R.id.resultsByIdRecyclerView) RecyclerView mResultsByIdRecycleView;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_by_id);
        ButterKnife.bind(this);

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