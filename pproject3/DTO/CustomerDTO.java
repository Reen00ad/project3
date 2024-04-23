package com.example.pproject3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
public class CustomerDTO {


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
    @NotEmpty(message ="phoneNumber must not be empty" )
    @Size(min = 10,max = 10,message = "phone number must be 10 digit")

    ////@Pattern(regexp = "^05.*")
    private String phoneNumber ;
}
