package com.example.pproject3.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class EmployeeDTO {


    private Integer user_id;
    @NotEmpty(message = "username can't be empty")
    @Size(min = 4,max = 10,message = "username must be between 4 and 10 character")
    private String userName;
    @NotEmpty(message = "password can't be empty")
    @Size(min = 6,message = "password must be atleast 6 character")
    private String password;
    @NotEmpty(message = "name can't be empty")
    @Size(min = 2,max = 20,message = "name must be between 2 and 20 character")
    private String name;
    @NotEmpty(message = "email can't be empty")
    @Email
    private String email;


    @NotEmpty(message = "position can't be empty")
    private String position;
    @Positive
    @NotNull(message = "salary can't be empty")
    private Double salary;

}
