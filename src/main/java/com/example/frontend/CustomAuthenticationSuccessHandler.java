package com.example.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String token = jwtTokenProvider.createToken(authentication);
System.out.println("1111");
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setMaxAge(60 * 60 * 24); // Thời gian sống của cookie là 1 ngày
        cookie.setPath("/"); // Chỉ định cookie có thể được truy cập bởi tất cả các URL trong ứng dụng
        response.addCookie(cookie);
    }
}

