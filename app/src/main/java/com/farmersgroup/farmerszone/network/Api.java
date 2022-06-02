package com.farmersgroup.farmerszone.network;

import com.farmersgroup.farmerszone.models.BrowseAllResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("tfvjsonapi.php")
    Call<BrowseAllResponse> getAllItems(
            @Query("search") String searchQuery
    );
}
