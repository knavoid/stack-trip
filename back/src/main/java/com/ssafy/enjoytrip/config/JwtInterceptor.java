package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청 헤더에서 토큰 추출
        String token = request.getHeader("Authorization");

        // 토큰 유효성 검사
        if (token != null && jwtUtil.validateToken(token.substring(7))) {
            // 토큰이 유효한 경우, 요청에 사용자 정보를 저장
            int userCode = jwtUtil.extractUserCode(token.substring(7));
            request.setAttribute("userCode", userCode);
            return true;
        } else {
            // 토큰이 유효하지 않은 경우, 401 Unauthorized 응답 반환
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }
}
