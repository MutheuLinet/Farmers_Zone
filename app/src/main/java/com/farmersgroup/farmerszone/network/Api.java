package com.farmersgroup.farmerszone.network;

import com.farmersgroup.farmerszone.models.BrowseAllResponse;
import com.farmersgroup.farmerszone.models.BrowseByIdResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("tfvjsonapi.php")
    Call<BrowseAllResponse> getAllItems(
            @Query("search") String searchQuery
    );
    @GET("tfvjsonapi.php")
    Call<BrowseByIdResponse> getItemById(
            @Query("tfvitem") String searchQuery
    );
}
