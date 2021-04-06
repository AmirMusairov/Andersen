package com.musairov.shop.controller;

import com.musairov.shop.dao.User;
import com.musairov.shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth")
    protected String loginForm() {
        return "auth/login-form";
    }

    @PostMapping("/auth")
    protected String login(HttpServletRequest req) {
        User user = authService.login(
                req.getParameter("login"),
                req.getParameter("password")
        );

        if (Objects.isNull(user)) {
            return "redirect:/auth";
        }

        req.getSession().setAttribute("user", user);
        return "redirect:/main";
    }

    @GetMapping("/logout")
    protected String logout(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);

        return "redirect:/auth";
    }

    @GetMapping("/registration")
    protected String doGet() {
        return "/auth/registration-form";
    }

    @PostMapping("/registration")
    protected String doPost(HttpServletRequest req) {
        User user = authService.registration(
                req.getParameter("login"),
                req.getParameter("password")
        );

        if (Objects.isNull(user)) {
            return "redirect:/auth";
        }

        req.getSession().setAttribute("user", user);
        return "redirect:/main";
    }
}
