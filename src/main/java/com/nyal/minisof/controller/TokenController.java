package com.nyal.minisof.controller;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.TokenEntity;
import com.nyal.minisof.service.account.AccountService;
import com.nyal.minisof.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    TokenService tokenService;
    @Autowired
    AccountService accountService;
    @GetMapping("/getToken")
    public ResponseEntity<TokenEntity> getToken(@RequestParam("device_token") String deviceToken){
        if (tokenService != null){
            TokenEntity token = tokenService.findByDeviceToken(deviceToken);
            if (token != null){
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam("username") String username, @RequestParam("device_token") String deviceToken){
        if(accountService != null && tokenService != null){
            AccountEntity account = accountService.getAccountByUsername(username);
            if (account != null){
                if (tokenService.existsByDeviceTokenAndAccountId(deviceToken, account.getAccountId())) {
                    TokenEntity token = tokenService.findByDeviceTokenAndAccountId(deviceToken, account.getAccountId());
                    if (token != null){
                        token.setAccessToken(true);
                        tokenService.save(token);
                        return new ResponseEntity<>(true, HttpStatus.OK);//update token device
                    } else{
                        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
                    }
                } else{
                    TokenEntity token = new TokenEntity();
                    token.setAccessToken(true);
                    token.setDeviceToken(deviceToken);
                    token.setAccount(account);
                    tokenService.save(token);
                    return new ResponseEntity<>(true, HttpStatus.OK);//add token new device
                }
            } else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> deleteToken(@RequestParam("username") String username,
                                          @RequestParam("device_token") String deviceToken){
        if(accountService != null && tokenService != null){
            AccountEntity account = accountService.getAccountByUsername(username);
            if (account != null){
                if (tokenService.existsByDeviceTokenAndAccountId(deviceToken, account.getAccountId())) {
                    TokenEntity token = tokenService.findByDeviceTokenAndAccountId(deviceToken, account.getAccountId());
                    if (token != null){
                        tokenService.delete(token);
                        return new ResponseEntity<>(true, HttpStatus.OK);// logout successful
                    } else{
                        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
                    }
                }
            } else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
