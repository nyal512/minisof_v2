package com.nyal.minisof.controller;

import com.nyal.minisof.model.UserEntity;
import com.nyal.minisof.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/getUser")
    public UserEntity getUser(@RequestParam("user_id") Integer userId){
        if (userId != null && userService.findById(userId).get() != null){
            UserEntity user = userService.findById(userId).get();
            return user;
        }
        return null;
    }
    @GetMapping("/getUserByName")
    public ResponseEntity<UserEntity> getUserByName(@RequestParam("name") String name){
        if (name != null && userService.getUserByName(name) != null){
            UserEntity user = userService.getUserByName(name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addUser")
    public ResponseEntity<Boolean> addUser(@RequestBody UserEntity user){
        if (user != null){
            if (!userService.exist(user.getName())){
                userService.save(user);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/edit")
    public ResponseEntity<Boolean> editUser(@RequestBody UserEntity user){
        if (user != null && userService.existById(user.getUserId())){
            userService.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
