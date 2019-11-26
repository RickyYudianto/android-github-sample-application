package com.ricky.application.utils.webservice;

import com.ricky.application.utils.webservice.models.ErrorBody;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ApiError {

    public static ErrorBody parseError(Response<?> response) {
        Converter<ResponseBody, ErrorBody> converter =
                RetrofitHelper.getClient()
                        .responseBodyConverter(ErrorBody.class, new Annotation[0]);

        ErrorBody error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorBody();
        }

        return error;
    }
}
