package com.musairov.shop.servlets;

import com.musairov.shop.dao.User;
import com.musairov.shop.dao.Warehouse;
import com.musairov.shop.repository.BucketRepository;
import com.musairov.shop.repository.ProductRepository;
import com.musairov.shop.service.BucketService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AddProductToBucketServlet extends HttpServlet implements Authentication {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        ProductRepository productRepository = new ProductRepository();
        Warehouse warehouse = new Warehouse(productRepository);
        BucketService bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, productRepository, warehouse)
        );

        Integer productId = Integer.parseInt(req.getParameter("productId"));
        bucketService.addProductToBucket(productId);

        resp.sendRedirect("/warehouse-products");
    }
}
