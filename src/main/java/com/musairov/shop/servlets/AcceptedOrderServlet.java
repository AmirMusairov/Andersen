package com.musairov.shop.servlets;

import com.musairov.shop.dao.User;
import com.musairov.shop.repository.OrderRepository;
import com.musairov.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AcceptedOrderServlet extends HttpServlet implements Authentication {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.acceptOrder(user, orderId);
        resp.sendRedirect("/order/declined-order");
    }
}
