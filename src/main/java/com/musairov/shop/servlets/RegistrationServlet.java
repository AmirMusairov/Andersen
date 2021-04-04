package com.musairov.shop.servlets;

import com.musairov.shop.dao.User;
import com.musairov.shop.repository.UserRepository;
import com.musairov.shop.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/auth/registration-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AuthService authService = new AuthService(new UserRepository());
        User user = authService.registration(
                req.getParameter("login"),
                req.getParameter("password")
        );

        if (Objects.isNull(user)) {
            resp.sendRedirect("/auth");
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/main");
    }
}
