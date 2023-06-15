package com.example.case_study_md3.controller;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.service.ProductService;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addToCart(request, response);
                break;
            default:
                showCart(request, response);
                break;
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request,response);
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));

        Order order = null;
        if (request.getSession().getAttribute("cart") != null) {
            order = (Order) request.getSession().getAttribute("cart");
        } else {
            order = new Order();
        }
        order.setId((int) (System.currentTimeMillis() % 10000));
        List<OrderItem> orderItems = new ArrayList<>();
//            int id, int idOrder, int idProduct, int quantity, float total
        int id = (int) (System.currentTimeMillis() % 10000);
        int idOrder = order.getId();
        float total = quantity * price;

        OrderItem orderItem = new OrderItem(id, idOrder, idProduct, quantity, total) ;
        if (order.getOrderItems() == null) {
            order.setOrderItems(orderItems);
        } else {
            boolean check = checkIdProductExistOrder(idProduct, order);
            if (check) {
                updateProductInOrder(idProduct, quantity, order);
            } else {
                order.getOrderItems().add(orderItem);
            }
        }
    }

    private void updateProductInOrder(int id, int quantity, Order order) {
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            if (order.getOrderItems().get(i).getIdProduct() == id) {
                OrderItem temp = order.getOrderItems().get(i);
                temp.setQuantity(quantity);
                break;
            }
        }
    }

    private boolean checkIdProductExistOrder(int idProduct, Order order) {
        if (order.getOrderItems() == null) {
            return false;
        }
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            if (order.getOrderItems().get(i).getIdProduct() == idProduct) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
    }

}