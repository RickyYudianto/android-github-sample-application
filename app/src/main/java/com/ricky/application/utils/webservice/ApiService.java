package com.ricky.application.utils.webservice;

import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.Repository;
import com.ricky.application.utils.webservice.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constant.USERS_ENDPOINT)
    Call<User[]> getUserList(@Query("since") int pagination
            , @Query("client_id") String clientId
            , @Query("client_secret") String clientSecret);

    @GET(Constant.USERS_ENDPOINT + Constant.SLASH_DELIMITER + "{login}")
    Call<User> getUserDetails(@Path("login") String login
            , @Query("client_id") String clientId
            , @Query("client_secret") String clientSecret);

    @GET(Constant.USERS_ENDPOINT + Constant.SLASH_DELIMITER + "{login}" + Constant.REPOS_ENDPOINT)
    Call<Repository[]> getUserRepos(@Path("login") String login
            , @Query("client_id") String clientId
            , @Query("client_secret") String clientSecret);
}
