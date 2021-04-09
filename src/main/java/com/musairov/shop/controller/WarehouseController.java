package com.musairov.shop.controller;

import com.musairov.shop.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WarehouseController implements Authentication {
    private final WarehouseService warehouseService;

    @GetMapping("/warehouse/products")
    protected String showAllProducts(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAuthUser(req, resp);

        model.addAttribute("products", warehouseService.getAll());

        return "warehouse/products";
    }
}
