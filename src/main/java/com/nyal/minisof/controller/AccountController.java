package com.nyal.minisof.controller;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController{
    private final static int ADMIN = 0;
    private final static int USER = 1;
    @Autowired
    AccountService accountService;
    @GetMapping ("/getAccount")
    public ResponseEntity<AccountEntity> getAccount(@RequestParam("username") String username) {
        AccountEntity account = accountService.getAccountByUsername(username);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        System.out.println("auth: "+accountService.authenticateAccount(username, password));
        if (accountService.authenticateAccount(username, password)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountEntity account) {
        if (!accountService.exist(account.getUsername())) {
            account.setRole(USER);
            account.setCreated(new Date());
            accountService.save(account);
            return new ResponseEntity<>("Account registered successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/editAccount") // áp dụng cho tài khoản admin
    public ResponseEntity<String> editAccount(@RequestBody AccountEntity account) {
        AccountEntity existingAccount = accountService.getAccountByUsername(account.getUsername());
        if (existingAccount != null) {
            existingAccount.setRole(account.getRole());
            accountService.save(existingAccount);
            return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        AccountEntity existingAccount = accountService.getAccountByUsername(username);

        if (existingAccount != null) {
            if (existingAccount.getPassword().equals(password)) {
                if (newPassword.equals(confirmPassword)) {
                    existingAccount.setPassword(newPassword);
                    accountService.save(existingAccount);
                    return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("New password and confirmation do not match", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Current password is incorrect", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }
}
