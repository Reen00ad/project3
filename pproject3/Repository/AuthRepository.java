package com.example.pproject3.Repository;

import com.example.pproject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String username);
    User findUserById(Integer id);
}
