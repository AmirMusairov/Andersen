package com.musairov.shop.controller;

import com.musairov.shop.currency.CurrencyBuilder;
import com.musairov.shop.currency.CurrencyCode;
import com.musairov.shop.dao.Product;
import com.musairov.shop.dao.User;
import com.musairov.shop.service.BucketService;
import com.musairov.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController implements Authentication {
    private final OrderService orderService;
    private final BucketService bucketService;

    @GetMapping("/order/create")
    protected String orderForm(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        Map<Product, Integer> products = bucketService.getProducts();
        Map<CurrencyCode, Double> totals = new LinkedHashMap<>();
        for (CurrencyCode currencyCode : CurrencyCode.values()) {
            double totalForCurrency = bucketService.getTotal(CurrencyBuilder.getCurrency(currencyCode)).doubleValue();
            totals.put(currencyCode, totalForCurrency);
        }

        model.addAttribute("products", products);
        model.addAttribute("totals", totals);

        return "/order/create-order";
    }

    @PostMapping("/order/create")
    protected String doPost(@RequestParam Double sum, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        orderService.makeOrder(user, BigDecimal.valueOf(sum), false);

        return "redirect:/bucket/clear";
    }

    @GetMapping("/order/declined")
    protected String declinedOrder(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("orders", orderService.getNotAcceptedOrders(user));

        return "order/declined-order";
    }

    @GetMapping("/order/accept/{orderId}")
    protected String acceptSavedOrder(@PathVariable Integer orderId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        orderService.acceptOrder(user, orderId);

        return "redirect:/order/declined";
    }

    @GetMapping("/order/history")
    protected String allOrders(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("orders", orderService.getOrdersHistory(user));

        return "order/order-history";
    }
}
