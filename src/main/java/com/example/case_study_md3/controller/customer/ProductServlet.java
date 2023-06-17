package com.example.case_study_md3.controller.customer;

import com.example.case_study_md3.model.*;
import com.example.case_study_md3.service.CategoryService;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
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
    private OrderService orderService = new OrderService();
    private OrderItemService orderItemService = new OrderItemService();

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ProductPageable pageable = new ProductPageable();
        inputProductPageable(req, pageable);

        EScale[] scales = EScale.values();
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        List<Product> allProducts = productService.findAll();
        List<Product> products = productService.findAllAdvance(pageable);

        User user = (User) req.getSession().getAttribute("user");
        Order unpaidOrder = new Order();
        List<OrderItem> orderItems = null;
        if (user != null){
            unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
            if (unpaidOrder != null){
                orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
                unpaidOrder.setOrderItems(orderItems);
            }
        }

        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "view":
                int id = Integer.parseInt(req.getParameter("id"));
                Product product = productService.findProduct(id);

                req.setAttribute("allProducts",allProducts);
                req.setAttribute("products",products);
                req.setAttribute("order",unpaidOrder);
                req.setAttribute("scales",scales);
                req.setAttribute("product",product);
                req.setAttribute("categoryMap",categoryMap);
                req.getRequestDispatcher("/WEB-INF/admin/product/view-product.jsp").forward(req,resp);
                break;


            default:
                List<Product> specials = productService.findSpecial();

                //allProducts là tất cả product, kích thước không bị ảnh hưởng bởi pageable. Dùng cho head, hiện mini cart
                req.setAttribute("allProducts",allProducts);
                req.setAttribute("order",unpaidOrder);
                req.setAttribute("pageable", pageable);
                req.setAttribute("scales",scales);
                req.setAttribute("specials",specials);
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
    private void inputProductPageable(HttpServletRequest request, ProductPageable pageable) {
        if (request.getParameter("kw") != null) {
            String kw = request.getParameter("kw");
            pageable.setKw(kw);
        }
        if (request.getParameter("page") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            pageable.setPage(page);
        }
        if (request.getParameter("limit") != null) {
            int limit = Integer.parseInt(request.getParameter("limit"));
            pageable.setPage(limit);
        }
        if (request.getParameter("sortfield") != null) {
            String sortField = request.getParameter("sortfield");
            pageable.setSortField(sortField);
        }
        if (request.getParameter("order") != null) {
            String order = request.getParameter("order");
            pageable.setOrder(order);
        }
        if (request.getParameter("category") != null) {
            int idCategory = Integer.parseInt(request.getParameter("category"));
            pageable.setIdCategory(idCategory);
        }
        if (request.getParameter("scale") != null) {
            String scale = request.getParameter("scale");
            pageable.setScale(scale);
        }

    }

    public void destroy() {
    }

}