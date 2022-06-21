package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.adapters.ResultsRecViewAdapter;
import com.farmersgroup.farmerszone.models.BrowseAllResponse;
import com.farmersgroup.farmerszone.models.ResultById;
import com.farmersgroup.farmerszone.network.Api;
import com.farmersgroup.farmerszone.network.ApiClient;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseAllActivity extends AppCompatActivity {

    public static final String TAG = BrowseAllActivity.class.getSimpleName();

    public List<ResultById>allItemsList;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @BindView(R.id.resultsRecycleView) RecyclerView mResultsRecycleView;
    @BindView(R.id.searchView) androidx.appcompat.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_all);
        ButterKnife.bind(this);


        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String query = searchView.getQuery().toString().trim();
                filterList(query);
                return true;
            }
        });

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Api client = ApiClient.getClient();
        Call<BrowseAllResponse> call = client.getAllItems("all"); //search=all

        call.enqueue(new Callback<BrowseAllResponse>() {
            @Override
            public void onResponse(Call<BrowseAllResponse> call, Response<BrowseAllResponse> response) {
                Log.d(TAG, "onResponse: resp");
                if (response.isSuccessful()){

                    allItemsList = response.body().getResults();

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

    private void filterList(String text) {
        List<ResultById> filteredList = new ArrayList<>();

        for (ResultById result : allItemsList){
            if (result.getTfvname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(result);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "Item not available", Toast.LENGTH_SHORT).show();
        }else {
            ResultsRecViewAdapter adapter = new ResultsRecViewAdapter(allItemsList, BrowseAllActivity.this);
            adapter.setFilteredResultList(filteredList);
            mResultsRecycleView.setAdapter(adapter);
            mResultsRecycleView.setLayoutManager(new LinearLayoutManager(BrowseAllActivity.this));//getApplicationContext()
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}