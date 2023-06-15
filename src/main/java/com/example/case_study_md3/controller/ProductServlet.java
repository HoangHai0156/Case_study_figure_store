package com.example.case_study_md3.controller;

import com.example.case_study_md3.model.Category;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.service.CategoryService;
import com.example.case_study_md3.service.ProductService;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "ProductServlet", value = {"/product" , "/"})
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        List<Product> products = productService.findAll();
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "edit":

                break;
            default:
                req.setAttribute("products",products);
                req.setAttribute("categoryMap",categoryMap);
                req.getRequestDispatcher("/WEB-INF/homepage/home.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){

            default:
                break;
        }
    }

    public void destroy() {
    }
}