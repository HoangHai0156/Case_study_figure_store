package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="DashboardServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = productService.findAll();

        req.getRequestDispatcher(Config.ADMIN+"dashboard.jsp").forward(req,resp);
    }
}
