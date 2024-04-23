package com.example.pproject3.Repository;

import com.example.pproject3.Model.Account;
import com.example.pproject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    List<Account> findAllById(User user);
}
