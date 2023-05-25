package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.LoginForm;
import com.ssafy.enjoytrip.dto.User;
import com.ssafy.enjoytrip.service.UserService;
import com.ssafy.enjoytrip.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    protected ResponseEntity<User> signUp(@RequestBody User user) throws Exception {
        userService.signUp(user);
        return ResponseEntity.created(URI.create("/user/" + user.getUserCode())).body(user);
    }

    @PostMapping("/login")
    protected ResponseEntity<String> login(@RequestBody LoginForm loginForm) throws Exception {
        User user = userService.login(loginForm.getEmail(), loginForm.getPassword());
        if (user != null) {
            // 로그인 성공
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(token);
        } else {
            // 로그인 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    protected ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) throws Exception {
        // 헤더에서 토큰 추출
        String authToken = token.substring(7);

        // 토큰의 유효성 검사
        if (!jwtUtil.validateToken(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 사용자 코드 추출 후 정보 얻어오기
        int userCode = jwtUtil.extractUserCode(authToken);
        User user = userService.getUserInfo(userCode);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    protected ResponseEntity<Boolean> emailDuplicateCheck(@RequestParam String email) throws Exception {
        return ResponseEntity.ok(userService.isEmailExist(email));
    }

    @GetMapping("/password")
    protected ResponseEntity<?> findPassword(@RequestParam String email, @RequestParam String name) throws Exception {
        User user = userService.findPassword(email, name);
        if (user != null){
            return ResponseEntity.ok(user.getPassword());
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
