package com.itstep.mylocalstorage.services;

import com.itstep.mylocalstorage.models.EntityModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/entities")
    Call<ArrayList<EntityModel>> getAll();

}
