package com.example.pproject3.Service;

import com.example.pproject3.Api.ApiException;
import com.example.pproject3.DTO.CustomerDTO;
import com.example.pproject3.Model.Account;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.User;
import com.example.pproject3.Repository.AuthRepository;
import com.example.pproject3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;

    //---------------------end point 5
    public List<Customer> getAllCustomers() {

      return customerRepository.findAll();
    }



//    public Customer getCustomer(Integer id) {
//        User user=authRepository.findUserById(id);
//        //هدفي اني اوصل للايدي مو اشيك اذا موجود او لا
//
//        return customerRepository.findCustomerById(id);
//    }

    public void addCustomer(CustomerDTO customerDTO){
        User user = new User();

        user.setRole("CUSTOMER");
        Customer customer = new Customer();
        user.setUserName(customerDTO.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        authService.addUser(user);


        Customer customer1 =new Customer(null,customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer1);

    }
//not
    public void updateCustomer(Integer userid,CustomerDTO customerDTO) {
        Customer customer=customerRepository.findCustomerById(customerDTO.getUser_id());


        if (customer.getId() != userid) {
            throw new ApiException("customer not found");
        }

        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        customerRepository.save(customer);


    }


//    public void deleteCustomer(Integer userid){
//        Customer customer=customerRepository.findCustomerById(userid);
//
//        if(customer == null){
//            throw new ApiException("customer not found");
//        }
//        customerRepository.delete(customer);
//    }
}
