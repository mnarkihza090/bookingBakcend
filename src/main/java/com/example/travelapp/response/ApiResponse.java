package com.example.travelapp.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T>{
    T data;
    List<T> dataList;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(List<T> dataList) {
        this.dataList = dataList;
    }

    public ApiResponse() {
    }
}
