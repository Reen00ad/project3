package com.example.pproject3.Service;

import com.example.pproject3.Api.ApiException;
import com.example.pproject3.Model.User;
import com.example.pproject3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findUserByUserName(username);

        if(user==null){
            throw new ApiException("wrong username or password");
        }

        return user;
    }


}
