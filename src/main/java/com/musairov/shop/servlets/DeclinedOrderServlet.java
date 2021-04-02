package com.musairov.shop.servlets;

import com.musairov.shop.dao.Order;
import com.musairov.shop.dao.User;
import com.musairov.shop.repository.OrderRepository;
import com.musairov.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class DeclinedOrderServlet extends HttpServlet implements Authentication {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        OrderService orderService = new OrderService(new OrderRepository());
        List<Order> orders = orderService.getNotAcceptedOrders(user);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/view/order/declined-order.jsp").forward(req, resp);
    }
}
