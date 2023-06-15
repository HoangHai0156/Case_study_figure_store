package com.example.case_study_md3.controller;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.service.ProductService;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addToCart(request, response);
                break;
//            default:
//                showCart(request, response);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
//        Order order = new Order();
//        if ()
    }

    public void destroy() {
    }
}