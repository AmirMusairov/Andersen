package com.musairov.shop.controller;

import com.musairov.shop.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MenuController implements Authentication {
    @GetMapping("/main")
    protected String doGet(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("name", user.getLogin());
        model.addAttribute("id", user.getId().toString());

        return "main";
    }
}
