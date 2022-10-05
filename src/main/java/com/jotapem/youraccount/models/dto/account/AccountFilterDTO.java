package com.jotapem.youraccount.models.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class AccountFilterDTO {

    private String agency;


    private int pageNumber = 0;

    private int pageSize = 10;

    @JsonIgnore
    public Pageable pageable() {
        return PageRequest.of(this.pageNumber, this.pageSize < 0 ? Integer.MAX_VALUE : this.pageSize);
    }
}
