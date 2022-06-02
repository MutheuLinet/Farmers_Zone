package com.farmersgroup.farmerszone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.models.BrowseAllResponse;
import com.farmersgroup.farmerszone.models.Result;
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

    @BindView(R.id.fruitList) ListView mFruitList;

    private String[] fruits = {"Banana","Orange", "Cucumber", "Mango", "Pineapple", "Passion", "Strawberry", "Grape", "Apple" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_all);
        ButterKnife.bind(this);
//        ArrayAdapter adapter = new ArrayAdapter(BrowseAllActivity.this, android.R.layout.simple_list_item_1, fruits);
//        mFruitList.setAdapter(adapter);

        Api client = ApiClient.getClient();
        Call<BrowseAllResponse> call = client.getAllItems("all");

        call.enqueue(new Callback<BrowseAllResponse>() {
            
            @Override
            public void onResponse(Call<BrowseAllResponse> call, Response<BrowseAllResponse> response) {
                Log.d(TAG, "onResponse: resp");

                if (response.isSuccessful()){

                    List<Result> allItemsList = response.body().getResults();
                    String[] fruits = new String[allItemsList.size()];
                    for (int i=0; i< fruits.length; i++){
                        fruits[i]=allItemsList.get(i).getTfvname();
                        Log.d("fruits", "Value" + "error");
                    }
                    ArrayAdapter adapter = new ArrayAdapter(BrowseAllActivity.this, android.R.layout.simple_list_item_1, fruits);
                    mFruitList.setAdapter(adapter);
                }
                         }

            @Override
            public void onFailure(Call<BrowseAllResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });
    }


}