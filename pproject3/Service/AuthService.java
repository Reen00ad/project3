package com.example.pproject3.Service;

import com.example.pproject3.Api.ApiException;
import com.example.pproject3.DTO.CustomerDTO;
import com.example.pproject3.DTO.EmployeeDTO;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.Employee;
import com.example.pproject3.Model.User;
import com.example.pproject3.Repository.AuthRepository;
import com.example.pproject3.Repository.CustomerRepository;
import com.example.pproject3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

//    public void registerCustomer(CustomerDTO customerDTO) throws ApiException {
//
//
//        User user = new User();
//
//        user.setRole("CUSTOMER");
//        Customer customer = new Customer();
//        user.setUserName(customerDTO.getUserName());
//        user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
//        user.setName(customerDTO.getName());
//        user.setEmail(customerDTO.getEmail());
//        customer.setPhoneNumber(customerDTO.getPhoneNumber());
//
//        customer.setUser(user);
//
//        authRepository.save(user);
//        customerRepository.save(customer);
//
//    }
//
//    public void registerEmployee(EmployeeDTO employeeDTO) {
//
//        User user1 = new User();
//        user1.setRole("EMPLOYEE");
//        Employee employee = new Employee();
//        user1.setUserName(employeeDTO.getUserName());
//        user1.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
//        user1.setName(employeeDTO.getName());
//        user1.setEmail(employeeDTO.getEmail());
//        employee.setPosition(employeeDTO.getPosition());
//        employee.setSalary(employeeDTO.getSalary());
//
//        employee.setUser(user1);
//
//        authRepository.save(user1);
//        employeeRepository.save(employee);
//    }

    public List<User> getAllUser(String username){
        User user=authRepository.findUserByUserName(username);
        if(user.getRole().equals("ADMIN")){
            return authRepository.findAll();
        }
        return null;
    }

    public void addUser(User user)  {
        authRepository.save(user);
    }


    public void update(String username,User user){
        User user1=authRepository.findUserByUserName(username);

        if(user1 == null){
            throw new ApiException("you are not authorized to update this user");
        }
        user1.setUserName(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());

        authRepository.save(user1);


    }

    public void delete(String userNameAdmin, String userName) {
        User admin=authRepository.findUserByUserName(userNameAdmin);
        User user=authRepository.findUserByUserName(userName);

        if(!admin.getRole().equals("ADMIN")){

            throw new ApiException("you are not authorized to delete this user") ;
        }
        authRepository.delete(user);
    }

}
