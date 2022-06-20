package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;
import com.example.springrestcontroller.repository.AccountRepository;
import com.example.springrestcontroller.singelon.SingelonHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class AccountServiceImp implements IAccountService{
    @Autowired
    AccountRepository accountRepository;

    //generate a String randomly 30 characters
    private String randoomToken(){
        String characters="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        IntStream.range(0,30).forEach(x -> {sb.append(characters.charAt(rnd.nextInt(characters.length())));});
        return sb.toString();
    }

    //Just check empty, null and >0
    private boolean checkEmptyLoginAccount(Account account){
        if(account != null) {
            if(account.getPassword() != null && account.getUsername() != null
                    && account.getStatus() >= 0 && account.getStatus() <= 1) {
                //
                return !account.getUsername().isEmpty() && !account.getPassword().isEmpty();
            }
        }
        return false;
    }

    //check existed username
    private Boolean checkExistedUsername(Account account){
        try {
            return accountRepository.existsByUsername(account.getUsername());
        }catch (Exception e){
            return true;
        }
    }

    //create new account
    @Override
    public ResponseEntity saveAccount(Account account){
        try {
            //check empty
            if(checkEmptyLoginAccount(account)) {
                //check existed username
                if (!checkExistedUsername(account)) {
                    //encrypt password
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
                    account.setPassword(encoder.encode(account.getPassword()));
                    return new ResponseEntity<>(accountRepository.save(account), HttpStatus.ACCEPTED);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already existed!");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Name or Password is Empty! Account Status in range 0 -> 1");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //login account then return a token
    @Override
    public ResponseEntity loginAndReturnToken(Account account){
        try {
            if(checkEmptyLoginAccount(account)) {
                //get Account (for password) by username first
                Account getAccount = accountRepository.findAccountByUsername(account.getUsername());
                //create an encoder
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (getAccount != null) {
                    //compare getAccount.getPassword() from db with this account.getPassword()
                    if (encoder.matches(account.getPassword(), getAccount.getPassword())) {
                        //release token
                        String token = randoomToken();
                        //save token to singelon hashMap
                        Map<String, Integer> map = SingelonHashMap.getIns().getSingelonMap();
                        map.put(token, getAccount.getId());
                        return new ResponseEntity<>("Your Token: " + token, HttpStatus.OK);
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Account");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //check null and empty
    private boolean checkNullPasswordAndToken(ChangePasswordRequest request){
        if(request != null){
            if(request.getNewPassword() != null && request.getToken() != null){
                return !request.getToken().isEmpty() && !request.getNewPassword().isEmpty();
            }
        }
        return false;
    }

    //change password after get token
    @Override
    public ResponseEntity changePassword(ChangePasswordRequest request){
        try{
            if(checkNullPasswordAndToken(request)) {
                //get map from singelon
                Map<String, Integer> map = SingelonHashMap.getIns().getSingelonMap();
                //check key
                if (map.containsKey(request.getToken())) {
                    //encrypt a new password
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    String pass = encoder.encode(request.getToken());
                    //get user id
                    Integer id = map.get(request.getToken());
                    accountRepository.updatePasswordById(pass, id);
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password Changed");
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong or Empty new Password and Token");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
}
