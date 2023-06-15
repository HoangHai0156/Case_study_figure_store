package com.example.case_study_md3.controller;

import com.example.case_study_md3.model.Category;
import com.example.case_study_md3.model.EScale;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.CategoryService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.utils.Config;

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

        EScale[] scales = EScale.values();
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        List<Product> products = productService.findAll();

//        Product product = new Product();
//        for (Integer idC: categoryMap.keySet()){
//            if (product.getIdCategory() == idC){
//                categoryMap.get(idC).getName();
//            }
//        }

        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "edit":
                break;
            case "view":
                int id = Integer.parseInt(req.getParameter("id"));
                Product product = productService.findProduct(id);

                req.setAttribute("scales",scales);
                req.setAttribute("product",product);
                req.setAttribute("categoryMap",categoryMap);
                req.getRequestDispatcher("/WEB-INF/admin/product/view-product.jsp").forward(req,resp);
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
        List<Product> products = productService.findAll();
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){

            default:
                req.setAttribute("products",products);
                req.setAttribute("categoryMap",categoryMap);
                req.getRequestDispatcher("/WEB-INF/homepage/home.jsp").forward(req,resp);
                break;
        }
    }

    public void destroy() {
    }

    public static void main(String[] args) {

    }
}