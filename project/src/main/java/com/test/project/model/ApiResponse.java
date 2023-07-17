package com.test.project.model;

public class ApiResponse {
    private boolean error = false;
    private String message = "";
    private Object data;


    public ApiResponse(Object data) {
        this.data = data;
    }

}
