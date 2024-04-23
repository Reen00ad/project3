package com.example.pproject3.Repository;

import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);
    List<Customer> findAllById(User user);
}
