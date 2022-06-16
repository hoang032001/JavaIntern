package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.ChangePasswordRequest;
import com.example.springrestcontroller.model.Account;

public interface IAccountService {
    String randoomToken();

    boolean checkEmptyLoginAccount(Account account);

    Boolean checkExistedUsername(Account account);

    Account saveAccount(Account account);

    String loginAndReturnToken(Account account);

    boolean checkNullPasswordAndToken(ChangePasswordRequest request);

    boolean changePassword(String newPassword, String token);
}
