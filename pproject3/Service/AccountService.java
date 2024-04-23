package com.example.pproject3.Service;

import com.example.pproject3.Api.ApiException;
import com.example.pproject3.Model.Account;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.User;
import com.example.pproject3.Repository.AccountRepository;
import com.example.pproject3.Repository.AuthRepository;
import com.example.pproject3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


//
    public void update(Integer id,Account account) {
        Account account1 = accountRepository.findAccountById(id);
        if(account1 == null){
            throw new ApiException("account not found");
        }
        account1.setAccountNumber(account.getAccountNumber());
        account1.setBalance(account.getBalance());
        account1.setIsActive(account.getIsActive());
        accountRepository.save(account1);
    }
//    public void delete(Integer id) {
//        Account account1 = accountRepository.findAccountById(id);
//        if(account1 == null){
//            throw new ApiException("account not found");
//        }
//        accountRepository.delete(account1);
//    }
    //----------------end crud

    //--------------------end point 2
    public void add(Integer customerid,Account account) {
        User user=authRepository.findUserById(customerid);
        Customer customer = customerRepository.findCustomerById(user.getId());


        account.setCustomer(customer);
        accountRepository.save(account);
    }



    //---------------end point 4

    public Account accountDetails(Integer id,Integer accid) {
        User user=authRepository.findUserById(id);
        Account account=accountRepository.findAccountById(accid);
        if(account.getCustomer().getId()==user.getId()){
            return account;
        }
        //هدفي اني اوصل للايدي مو اشيك اذا موجود او لا

        return null;

    }


    //---------------------end point 6

    public void deposite(Integer userid,Integer accid,Integer amount) {

        User user=authRepository.findUserById(userid);
        Account account=accountRepository.findAccountById(accid);

        if(account.getCustomer().getId() == user.getId()){
            account.setBalance(account.getBalance()+amount);
            accountRepository.save(account);
        }

    }

    public void withdraw(Integer userid,Integer accid,Integer amount) {

        User user=authRepository.findUserById(userid);
        Account account=accountRepository.findAccountById(accid);

        if(account.getCustomer().getId() == user.getId()){
            if(account.getBalance() >= amount){
                account.setBalance(account.getBalance()-amount);
                accountRepository.save(account);
            }

        }

    }

    //-----------------------------end point 7
    public void transfer(Integer accid1,Integer accid2,Integer amount) {
        Account account1=accountRepository.findAccountById(accid1);
        Account account2=accountRepository.findAccountById(accid2);
        if(account1.getBalance() >= amount) {
            account1.setBalance(account1.getBalance() - amount);
            account2.setBalance(account2.getBalance() + amount);
        }
        accountRepository.save(account1);
        accountRepository.save(account2);
    }

}
