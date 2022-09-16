package com.jotapem.youraccount.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, length = 4)
    private String agency;

    @Column(precision = 12, scale = 2)
    private BigDecimal balance;
}
