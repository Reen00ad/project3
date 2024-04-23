package com.example.pproject3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username can't be empty")
    @Size(min = 4,max = 10,message = "username must be between 4 and 10 character")
    @Column(columnDefinition = " varchar(10) not null unique")
    private String userName;
    @NotEmpty(message = "password can't be empty")
    @Size(min = 6,message = "password must be atleast 6 character")
    @Column(columnDefinition = " varchar(20) not null ")
    private String password;
    @NotEmpty(message = "name can't be empty")
    @Size(min = 2,max = 20,message = "name must be between 2 and 20 character")
    @Column(columnDefinition = " varchar(20) not null ")
    private String name;
    @NotEmpty(message = "email can't be empty")
    @Email
    @Column(columnDefinition = " varchar(30) not null unique")
    private String email;

    @Pattern(regexp ="^(CUSTOMER|EMPLOYEE|ADMIN)$", message = "role must be CUSTOMER or EMPLOYEE or ADMIN ")
    private String role;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
