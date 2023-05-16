package com.ssafy.enjoytrip.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.dto.LoginForm;
import com.ssafy.enjoytrip.dto.User;
import com.ssafy.enjoytrip.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    protected ResponseEntity<User> signUp(@RequestBody User user) throws Exception {
        userService.signUp(user);
        return ResponseEntity.created(URI.create("/user/" + user.getUserCode())).body(user);
    }

    @PostMapping("/login")
    protected ResponseEntity<User> login(@RequestBody LoginForm loginForm) throws Exception {
        User user = userService.login(loginForm.getEmail(), loginForm.getPassword());
        if (user != null) {
            // 로그인 성공
            return ResponseEntity.ok(user);
        } else {
            // 로그인 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/{userCode}")
    protected ResponseEntity<User> getUserInfo(@PathVariable int userCode) throws Exception {
        User user = userService.getUserInfo(userCode);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userCode}")
    protected ResponseEntity<User> modifyUserInfo(@RequestBody User user) throws Exception {
        userService.modifyUserInfo(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userCode}")
    protected ResponseEntity<Integer> deleteAccount(@PathVariable int userCode) throws Exception {
        User user = userService.getUserInfo(userCode);
        if (user != null) {
            userService.deleteAccount(userCode);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        log.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

}
