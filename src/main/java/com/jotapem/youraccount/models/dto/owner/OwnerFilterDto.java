package com.jotapem.youraccount.models.dto.owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jotapem.youraccount.models.enums.LogicOperator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class OwnerFilterDto {

    private String email;
    private String nickname;
    private String lastName;
    private LogicOperator logicOperator = LogicOperator.AND;

    private int pageNumber = 0;
    private int pageSize = 10;

    @JsonIgnore
    public Pageable pageable() {
        return PageRequest.of(this.pageNumber, this.pageSize < 0 ? Integer.MAX_VALUE: this.pageSize);
    }
}
