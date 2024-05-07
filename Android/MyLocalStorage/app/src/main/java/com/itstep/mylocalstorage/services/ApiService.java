package com.itstep.mylocalstorage.services;

import com.itstep.mylocalstorage.models.EntityModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Серивс соответстует RESTful Controller на стороне бека
 */
public interface ApiService {

    @POST("/entities")
    Call<EntityModel> createEntity(@Body EntityModel entity);

    @GET("/entities")
    Call<ArrayList<EntityModel>> getAll();

    @GET("/entities/{id}")
    Call<EntityModel> getById(@Path("id") String id);

    @PUT("/entities/{id}")
    Call<EntityModel> updateEntity(@Path("id") String id, @Body EntityModel entity);

    @DELETE("/entities/{id}")
    Call<Void> deleteEntity(@Path("id") String id);

}
