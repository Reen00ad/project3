package com.example.pproject3.Repository;

import com.example.pproject3.Model.Employee;
import com.example.pproject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
    List<Employee> findAllById(User user);
}
