package com.example.pproject3.Service;

import com.example.pproject3.Api.ApiException;
import com.example.pproject3.DTO.EmployeeDTO;
import com.example.pproject3.Model.Account;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.Employee;
import com.example.pproject3.Model.User;
import com.example.pproject3.Repository.AccountRepository;
import com.example.pproject3.Repository.AuthRepository;
import com.example.pproject3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthService authService;
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;

//
//    public List<Employee> getAllEmployee(Integer id) {
//        User user=authRepository.findUserById(id);
//        //هدفي اني اوصل للايدي مو اشيك اذا موجود او لا
//
//        return employeeRepository.findAllById(user);
//
//    }
//
//    public Employee getEmployee(Integer id){
//        User user=authRepository.findUserById(id);
//        //هدفي اني اوصل للايدي مو اشيك اذا موجود او لا
//
//        return employeeRepository.findEmployeeById(id);
//
//    }

    public void addEmployee(EmployeeDTO employeeDTO){
        User user = new User();
        user.setRole("EMPLOYEE");
        user.setUserName(employeeDTO.getUserName());
        String hashpass=new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashpass);
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        authService.addUser(user);

        Employee employee = new Employee(null, employeeDTO.getPosition(), employeeDTO.getSalary(), user);
        employeeRepository.save(employee);

    }
//not
    public void updateEmployee(Integer userid,EmployeeDTO employeeDTO) {
        Employee employee=employeeRepository.findEmployeeById(employeeDTO.getUser_id());

        if (employee == null) {
            throw new ApiException("employee not found");
        }
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        employeeRepository.save(employee);

    }

//    public void deleteEmployee(Integer userid){
//        Employee employee=employeeRepository.findEmployeeById(userid);
//
//        if(employee == null){
//            throw new ApiException("employee not found");
//        }
//        employeeRepository.delete(employee);
//    }

    //------------------end point 3

    public void activateAccount(Integer id) {

        Account account=accountRepository.findAccountById(id);
        account.setIsActive(true);

        accountRepository.save(account);
    }
    //---------------------------------end point 8
    public void blockAccount(Integer id) {

        Account account=accountRepository.findAccountById(id);
        account.setIsActive(false);

        accountRepository.save(account);
    }
}
