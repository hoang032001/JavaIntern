package com.example.springrestcontroller.controller;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;
import com.example.springrestcontroller.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    //create new account with username and password
    //Post Man: POST -> http://localhost:8080/accounts/save/
    //JSON: { "username":"?" , "password":"?"}
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveAccount(@RequestBody Account account){
        try{
            //check empty
            if(iAccountService.checkEmptyLoginAccount(account)) {
                //check existed username
                if (!iAccountService.checkExistedUsername(account)) {
                    return new ResponseEntity<>(iAccountService.saveAccount(account), HttpStatus.ACCEPTED);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already existed!");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Name or Password is Empty! Account Status in range 0 -> 1");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //login by username and password
    //Post Man: POST -> http://localhost:8080/accounts/login/
    //JSON: { "username":"user7" , "password":"admin@123A" }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginAccount(@RequestBody Account account){
        try{
            //check empty
            if(iAccountService.checkEmptyLoginAccount(account)){
                //login successful will return a token
                return new ResponseEntity<>("Your Token: " + iAccountService.loginAndReturnToken(account), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Account");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //change password will need your token for update password
    //account will have new password of yours
    //Post Man: POST -> http://localhost:8080/accounts/change_password/
    //You Should Login first to get a Token key
    //JSON: { "token":"?" , "newPassword":"?your_new_password?" }
    @PostMapping("/change_password")
    @ResponseBody
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request){
        try{
            //check pass and token
            if (iAccountService.checkNullPasswordAndToken(request)) {
                //call to service
                if (iAccountService.changePassword(request.getNewPassword(), request.getToken())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password Changed");
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong or Empty new Password and Token");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
}
