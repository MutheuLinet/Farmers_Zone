package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.adapters.ResultsRecViewAdapter;
import com.farmersgroup.farmerszone.models.BrowseAllResponse;
import com.farmersgroup.farmerszone.models.ResultById;
import com.farmersgroup.farmerszone.network.Api;
import com.farmersgroup.farmerszone.network.ApiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseAllActivity extends AppCompatActivity {

    public static final String TAG = BrowseAllActivity.class.getSimpleName();

    @BindView(R.id.resultsRecycleView) RecyclerView mResultsRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_all_two);
        ButterKnife.bind(this);

        Api client = ApiClient.getClient();
        Call<BrowseAllResponse> call = client.getAllItems("all"); //search=all

        call.enqueue(new Callback<BrowseAllResponse>() {
            @Override
            public void onResponse(Call<BrowseAllResponse> call, Response<BrowseAllResponse> response) {
                Log.d(TAG, "onResponse: resp");
                if (response.isSuccessful()){

                    List<ResultById> allItemsList = response.body().getResults();

                    ResultsRecViewAdapter adapter = new ResultsRecViewAdapter(allItemsList, BrowseAllActivity.this);
                    adapter.setResultList(allItemsList);
                    mResultsRecycleView.setAdapter(adapter);
                    mResultsRecycleView.setLayoutManager(new LinearLayoutManager(BrowseAllActivity.this));//getApplicationContext()
                }
            }
            @Override
            public void onFailure(Call<BrowseAllResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}