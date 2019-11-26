package com.ricky.application.utils.webservice;

import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constant.GET_ALL_USERS_ENDPOINT)
    Call<User[]> getUserList();
}
