package com.example.pproject3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message ="accountNumber must not be empty" )
    @Column(columnDefinition = " varchar(20) not null ")
    //@Pattern()
    private String accountNumber;
    @NotNull(message = "balance can't be empty")
    @Column(columnDefinition = " double not null ")
    private Double balance;
    @AssertFalse
    private Boolean isActive ;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;
}
