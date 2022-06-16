package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;
import com.example.springrestcontroller.repository.AccountRepository;
import com.example.springrestcontroller.singelon.SingelonHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class AccountServiceImp implements IAccountService{
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String randoomToken(){
        String characters="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        IntStream.range(0,30).forEach(x -> {sb.append(characters.charAt(rnd.nextInt(characters.length())));});
        return sb.toString();
    }

    @Override
    public boolean checkEmptyLoginAccount(Account account){
        if(account != null) {
            if(account.getPassword() != null && account.getUsername() != null
                    && account.getStatus() >= 0 && account.getStatus() <= 1)
            //
            if (!account.getUsername().isEmpty() && !account.getPassword().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean checkExistedUsername(Account account){
        try {
            Account accountTemp = accountRepository.findByUsername(account.getUsername());
            if(accountTemp != null){
                return true;
            }
            return false;
        }catch (Exception e){
            return true;
        }
    }

    @Override
    public Account saveAccount(Account account){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            account.setPassword(encoder.encode(account.getPassword()));
            return accountRepository.save(account);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String loginAndReturnToken(Account account){
        try {
            //get Account (for password) by username first
            Account getAccount = accountRepository.findAccountByUsername(account.getUsername());
            //create an encoder
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(getAccount != null) {
                //compare getAccount.getPassword() from db with this account.getPassword()
                if (encoder.matches(account.getPassword(), getAccount.getPassword())) {
                    //release token
                    String token = randoomToken();
                    //save token to singelon hashMap
                    Map<String, Integer> map = SingelonHashMap.getIns().getSingelonMap();
                    map.put(token, getAccount.getId());
                    return token;
                }
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean checkNullPasswordAndToken(ChangePasswordRequest request){
        if(request != null){
            if(request.getNewPassword() != null && request.getToken() != null){
                if(!request.getToken().isEmpty() && !request.getNewPassword().isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String newPassword, String token){
        try{
            //get map from singelon
            Map<String, Integer> map = SingelonHashMap.getIns().getSingelonMap();
            //check key
            if(map.containsKey(token)) {
                //encrypt a new password
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String pass = encoder.encode(newPassword);
                //get user id
                Integer id = map.get(token);
                accountRepository.updatePasswordById(pass, id);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
