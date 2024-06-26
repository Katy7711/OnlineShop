package com.example.onlinshop.dto;

import java.util.Collection;
import java.util.List;
import lombok.Data;

@Data
public class ResponseWrapper<T> {

    private int count;

    private Collection<T> results;

    public static <T> ResponseWrapper<T> of(List<T> results) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();

        if (results == null) {
            return responseWrapper;
        }

        responseWrapper.results = results;
        responseWrapper.count = results.size();

        return responseWrapper;
    }
}