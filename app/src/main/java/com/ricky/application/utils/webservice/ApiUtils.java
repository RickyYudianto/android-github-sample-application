package com.ricky.application.utils.webservice;

public class ApiUtils {

    private ApiUtils() {}

    public static ApiService getAPIService() {

        return RetrofitHelper.getClient().create(ApiService.class);
    }

}
