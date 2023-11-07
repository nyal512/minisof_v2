package com.nyal.minisof.controller;

import com.nyal.minisof.controller.convert_password.ConvertPasswordMD5;
import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/account")
public class AccountController{
    private final static int ADMIN = 0;
    private final static int USER = 1;
    private final static int PASSWORD_EMPTY = 2;
    private final static int PASSWORD_LENGTH_REGISTER = 3;
    private final static int PASSWORD_INVALID_REGISTER = 4;
    private final static  int RESULT_OK = 5;
    private final static int PASSWORD_LENGTH_MIN = 8;
    private final static int PASSWORD_LENGTH_MAX = 16;
    private static final String REGEX_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=^(?:[A-Za-z0-9@#$+\\-.\\/:=?\\[^\\]_|]{8,50})$)(?=^(?:(.)(?!\\1{3}))+$)";
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
    public ResponseEntity<Boolean> login(@RequestBody Map<String, String> request) throws NoSuchAlgorithmException {
        String username = request.get("username");
        String password = ConvertPasswordMD5.convertHashToString(request.get("password"));
        if (accountService.authenticateAccount(username, password)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody AccountEntity account) throws NoSuchAlgorithmException {
        if (!accountService.exist(account.getUsername())) {
            if (validatePassword(account.getPassword())== RESULT_OK){
                String password = ConvertPasswordMD5.convertHashToString(account.getPassword());
                account.setPassword(password);
                account.setRole(USER);
                account.setCreated(new Date());
                accountService.save(account);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/editAccount") // áp dụng cho tài khoản admin
    public ResponseEntity<Boolean> editAccount(@RequestBody AccountEntity account) {
        AccountEntity existingAccount = accountService.getAccountByUsername(account.getUsername());
        if (existingAccount != null) {
            existingAccount.setRole(account.getRole());
            accountService.save(existingAccount);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) throws NoSuchAlgorithmException {
        AccountEntity existingAccount = accountService.getAccountByUsername(username);
        if (validatePassword(password) == RESULT_OK && validatePassword(newPassword) == RESULT_OK && validatePassword(confirmPassword) == RESULT_OK){
            password = ConvertPasswordMD5.convertHashToString(password);
            if (existingAccount != null) {
                if (existingAccount.getPassword().equals(password)) {
                    if (newPassword.equals(confirmPassword)) {
                        newPassword = ConvertPasswordMD5.convertHashToString(newPassword);
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
        } else{
            return new ResponseEntity<>("Account invalidate", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@RequestParam("username") String username) {
        AccountEntity existingAccount = accountService.getAccountByUsername(username);
        if (existingAccount != null) {
            accountService.delete(existingAccount);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    public static int validatePassword(@Nullable String password){
        if (password == null || password.isEmpty()){
            return PASSWORD_EMPTY;
        }
        int length = password.length();
        if (length < PASSWORD_LENGTH_MIN || length > PASSWORD_LENGTH_MAX) {
            return PASSWORD_LENGTH_REGISTER;
        }
        if (!Pattern.compile(REGEX_PASSWORD).matcher(password).find()) {
            return PASSWORD_INVALID_REGISTER;
        }
        return RESULT_OK;
    }
}
