package com.jotapem.youraccount.models.dto;


import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResultDTO<T> {

    private final long totalElements;

    private final int totalPages;

    private final int numberElements;

    private final int pageSize;

    private final int pageNumber;

    private final boolean last;

    private final boolean first;

    private final boolean empty;

    private final List<T> elements;

    public PageResultDTO(Page<T> page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.numberElements = page.getNumberOfElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.last = page.isLast();
        this.first = page.isFirst();
        this.empty = page.isEmpty();
        this.elements = page.getContent();
    }
}
