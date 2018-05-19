package com.example.mumo.agrimart.remote;

import com.example.mumo.agrimart.model.Product;
import com.example.mumo.agrimart.model.ProductApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/products/")
    Call<ProductApiResponse> getProducts();

}
