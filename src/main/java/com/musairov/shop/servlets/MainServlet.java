package com.musairov.shop.servlets;

import com.musairov.shop.dao.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet implements Authentication {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        req.setAttribute("name", user.getLogin());
        req.setAttribute("id", user.getId().toString());
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }
}
