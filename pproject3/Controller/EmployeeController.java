package com.example.pproject3.Controller;


import com.example.pproject3.DTO.EmployeeDTO;
import com.example.pproject3.Model.User;
import com.example.pproject3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

//    @GetMapping("/getall")
//    public ResponseEntity getAll(@AuthenticationPrincipal User user){
//        return ResponseEntity.status(200).body(employeeService.getAllEmployee(user.getId()));
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity getEmployee(@AuthenticationPrincipal User user){
//        return ResponseEntity.status(200).body(employeeService.getEmployee(user.getId()));
//    }
//    //not
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getId(), employeeDTO);
        return ResponseEntity.status(200).body("Customer update successfully");
    }
//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@AuthenticationPrincipal User user){
//        employeeService.deleteEmployee(user.getId());
//        return ResponseEntity.status(200).body("employee update successfully");
//    }


    //----------------------end point 3
    @PutMapping("/activateAccount/{id}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User user,@PathVariable Integer id){
        employeeService.activateAccount(id);
        return ResponseEntity.status(200).body("account activated successfully");
    }

    //--------------------end point 8
    @PutMapping("/blockAccount/{id}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user,@PathVariable Integer id){
        employeeService.blockAccount(id);
        return ResponseEntity.status(200).body("account blocked successfully");
    }
}
