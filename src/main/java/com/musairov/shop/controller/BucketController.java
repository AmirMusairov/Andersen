package com.musairov.shop.controller;

import com.musairov.shop.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BucketController implements Authentication {
    private final BucketService bucketService;

    @GetMapping("bucket/products")
    protected String allProducts(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        model.addAttribute("products", bucketService.getProducts());

        return "bucket/products";
    }

    @GetMapping("/bucket/add-product/{productId}")
    public String addProductToBucket(@PathVariable Integer productId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        bucketService.addProductToBucket(productId);

        return "redirect:/warehouse/products";
    }

    @GetMapping("/bucket/delete-product/{productId}")
    protected String doGet(@PathVariable Integer productId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        bucketService.deleteProductFromTheBucket(productId);

        return "redirect:/bucket/products";
    }

    @GetMapping("/bucket/clear")
    protected String clearBucket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        bucketService.clearBucket();

        return "redirect:/main";
    }
}
