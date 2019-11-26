package com.ricky.application.utils.webservice;

import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constant.GET_ALL_USERS_ENDPOINT)
    Flowable<Response<User[]>> getUserList();
}
