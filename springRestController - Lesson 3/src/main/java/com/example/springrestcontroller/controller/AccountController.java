package com.example.springrestcontroller.controller;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;
import com.example.springrestcontroller.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    //create new account with username and password
    //Post Man: POST -> http://localhost:8080/accounts/save/
    //JSON: { "username":"?" , "password":"?"}
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveAccount(@RequestBody Account account){
        return  iAccountService.saveAccount(account);
    }

    //login by username and password
    //Post Man: POST -> http://localhost:8080/accounts/login/
    //JSON: { "username":"user7" , "password":"admin@123A" }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginAccount(@RequestBody Account account){
        return iAccountService.loginAndReturnToken(account);
    }

    //change password will need your token for update password
    //account will have new password of yours
    //Post Man: POST -> http://localhost:8080/accounts/change_password/
    //You Should Login first to get a Token key
    //JSON: { "token":"?" , "newPassword":"?your_new_password?" }
    @PostMapping("/change_password")
    @ResponseBody
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request){
        return iAccountService.changePassword(request);
    }
}
