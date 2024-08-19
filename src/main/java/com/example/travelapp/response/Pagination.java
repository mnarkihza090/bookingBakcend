package com.example.travelapp.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class Pagination<T> {

    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private T content;
    private long totalElements;

    public Pagination(int totalPages, int pageNumber, int pageSize, T content, long totalElements) {
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.content = content;
        this.totalElements = totalElements;
    }


}
