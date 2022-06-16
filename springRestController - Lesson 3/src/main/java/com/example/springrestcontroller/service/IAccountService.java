package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;
import org.springframework.http.ResponseEntity;

public interface IAccountService {
    ResponseEntity saveAccount(Account account);

    ResponseEntity loginAndReturnToken(Account account);

    ResponseEntity changePassword(ChangePasswordRequest request);
}
