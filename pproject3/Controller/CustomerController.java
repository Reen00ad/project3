package com.example.pproject3.Controller;

import com.example.pproject3.DTO.CustomerDTO;
import com.example.pproject3.Model.User;
import com.example.pproject3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;



    //------------------end point5
@GetMapping("/getall")
public ResponseEntity getAll(){
    return ResponseEntity.status(200).body(customerService.getAllCustomers());
}
//
//@GetMapping("/get")
//public ResponseEntity getCustomer(@AuthenticationPrincipal User user){
//    return ResponseEntity.status(200).body(customerService.getCustomer(user.getId()));
//}
////not
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body("Customer update successfully");
    }
//    @DeleteMapping("/delete")
//    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
//        customerService.deleteCustomer(user.getId());
//        return ResponseEntity.status(200).body("Customer update successfully");
//    }



}
