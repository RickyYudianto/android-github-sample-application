package com.ricky.application.utils.webservice;

import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constant.GET_ALL_USERS_ENDPOINT)
    Call<User[]> getUserList(@Query("since") int pagination
            , @Query("client_id") String clientId
            , @Query("client_secret") String clientSecret);
}
