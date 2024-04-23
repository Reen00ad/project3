package com.example.pproject3.Controller;

import com.example.pproject3.DTO.CustomerDTO;
import com.example.pproject3.DTO.EmployeeDTO;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.User;
import com.example.pproject3.Service.AuthService;
import com.example.pproject3.Service.CustomerService;
import com.example.pproject3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;


    @GetMapping("/getall/{username}")
    public ResponseEntity getAllUser(@PathVariable String username){
        return ResponseEntity.status(200).body(authService.getAllUser(username));
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(200).body("customer registered successfully");
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(200).body("employee registered successfully");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User username, @RequestBody @Valid User user){
        authService.update(username.getUsername(), user);
        return ResponseEntity.status(200).body(" updated successfully");
    }
    @DeleteMapping("/delete/{userNameAdmin}/{userName}")
    public ResponseEntity delete(@PathVariable String userNameAdmin,@PathVariable String userName){
        authService.delete(userNameAdmin,userName);
        return ResponseEntity.status(200).body(" deleted successfully");
    }

//



}
