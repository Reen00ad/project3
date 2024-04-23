package com.example.pproject3.Controller;

import com.example.pproject3.Model.Account;
import com.example.pproject3.Model.Customer;
import com.example.pproject3.Model.User;
import com.example.pproject3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;



//
//    //not
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User accountid, @RequestBody @Valid Account account){
        accountService.update(accountid.getId(), account);
        return ResponseEntity.status(200).body("account update successfully");
    }
//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@AuthenticationPrincipal User account){
//        accountService.delete(account.getId());
//        return ResponseEntity.status(200).body("account deleted successfully");
//    }
//------------------------- end crud----------------------------------------
    //--------------------end point 2
   @PostMapping("/add")
   public ResponseEntity addAccount(@AuthenticationPrincipal User user,@Valid @RequestBody Account account){
    accountService.add(user.getId(),account);
    return ResponseEntity.status(200).body("account added");
    }

    //--------------------end point 4
    @GetMapping("/accountDetails/{accid}")
    public ResponseEntity accountDetails(@AuthenticationPrincipal User user,@PathVariable Integer accid){
        return ResponseEntity.status(200).body(accountService.accountDetails(user.getId(),accid));
    }


    //------------------------endpoint6
    @PutMapping("/deposite/{accid}/{amount}")
    public ResponseEntity deposite(@AuthenticationPrincipal User user,@PathVariable Integer accid,@PathVariable Integer amount){
        accountService.deposite(user.getId(),accid,amount);
        return ResponseEntity.status(200).body("account deposited");
    }

    @PutMapping("/withdraw/{accid}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,@PathVariable Integer accid,@PathVariable Integer amount){
        accountService.withdraw(user.getId(),accid,amount);
        return ResponseEntity.status(200).body("account deposited");
    }

    //------------------------------end point 7
    @PutMapping("/transfer/{accid1}/{accid2}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user,@PathVariable Integer accid1,@PathVariable Integer accid2,@PathVariable Integer amount){
        accountService.transfer(accid1, accid2, amount);
        return ResponseEntity.status(200).body("transfer done");
    }
}
