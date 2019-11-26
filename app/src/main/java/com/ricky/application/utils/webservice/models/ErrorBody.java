package com.ricky.application.utils.webservice.models;

public class ErrorBody {

    private int status;
    private String error;
    private String message;

    public ErrorBody() {
    }

    public int status() {
        return status;
    }

    public String error() {
        return error;
    }

    public String message() {
        return message;
    }

}