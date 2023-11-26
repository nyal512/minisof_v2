package com.nyal.minisof.controller;

import com.nyal.minisof.controller.convert_password.ConvertPasswordMD5;
import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.UserEntity;
import com.nyal.minisof.service.account.AccountService;
import com.nyal.minisof.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

@RestController
@RequestMapping("/api/forgotPassword")
public class ResetPasswordController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @GetMapping("/getCode")
    public ResponseEntity<String> getVerifyCode(@RequestParam("number_phone") String numberPhone){
        if (userService != null){
            UserEntity user = userService.findByNumberPhone(numberPhone).get();
            if (user != null && user.getVerifyCode() != null){
                return new ResponseEntity<>(user.getVerifyCode(), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/sendVerifyCode")
    public ResponseEntity<String> addVerifyCode(@RequestParam("number_phone") String numberPhone){
        if (userService != null){
            UserEntity user = userService.findByNumberPhone(numberPhone).get();
            if (user != null){
                String code = generateRandomCode();
                user.setVerifyCode(code);
                userService.save(user);
                return new ResponseEntity<>(code, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/verifyCode")
    public ResponseEntity<Boolean> verifyCode(@RequestParam("number_phone")
                                                  String numberPhone, @RequestParam("code") String code){
        if (userService != null){
            UserEntity user = userService.findByNumberPhone(numberPhone).get();
            if (user != null && user.getVerifyCode().equals(code)){
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    private String generateRandomCode() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> changePass(@RequestParam("number_phone") String numberPhone,
                                              @RequestParam("new_password") String newPass,
                                              @RequestParam("confirm_password") String confirmPass)
            throws NoSuchAlgorithmException {
        if (userService != null && accountService != null && userService.findByNumberPhone(numberPhone).get() != null){
            UserEntity user = userService.findByNumberPhone(numberPhone).get();
            AccountEntity account = accountService.findByUserId(user.getUserId());
            if (account != null && newPass!= null && confirmPass!= null && newPass.equals(confirmPass)){
                String password = ConvertPasswordMD5.convertHashToString(newPass);
                account.setPassword(password);
                accountService.save(account);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
