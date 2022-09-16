package com.jotapem.youraccount.models.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Schema(description = "Object stores information from a list of elements per page.")
public class PageResultDTO<T> {

    @Schema(description = "Total Records.", example = "56")
    private final long totalElements;

    @Schema(description = "Total pages.", example = "3")
    private final int totalPages;

    @Schema(description = "Number of records.", example = "20")
    private final int numberElements;

    @Schema(description = "Number of records per page.", example = "20")
    private final int pageSize;

    @Schema(description = "Page number.", example = "0")
    private final int pageNumber;

    @Schema(description = "It is the last record.", example = "false")
    private final boolean last;

    @Schema(description = "It is the first record.", example = "true")
    private final boolean first;

    @Schema(description = "It is empty.", example = "false")
    private final boolean empty;

    @Schema(description = "List of elements.")
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
