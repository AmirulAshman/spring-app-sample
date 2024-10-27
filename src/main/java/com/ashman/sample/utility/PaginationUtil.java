package com.ashman.sample.utility;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PaginationUtil<T extends Object> {

    public Page<T> listToPage(List<T> list, int pageNumber, int pageSize) {
        int start = pageNumber * pageSize;
        int end = Math.min(start + pageSize, list.size());

        List<T> pageContent = list.subList(start, end);
        return new PageImpl<>(pageContent, PageRequest.of(pageNumber, pageSize), list.size());
    }

}
