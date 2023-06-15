package com.example.case_study_md3.controller;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.service.UserService;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    public ProductService productService;
    public OrderService orderService;
    public OrderItemService orderItemService;
    public UserService userService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService= new OrderService();
        orderItemService = new OrderItemService();
        userService = new UserService();
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
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProduct(idProduct);

//        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantity = 1;
//        float price = Float.parseFloat(request.getParameter("price"));
        float price = product.getPrice();
//        User user = (User) request.getSession().getAttribute("user");
//        int idUser = user.getId();
        User user = userService.findUser(1);
        int idUser = user.getId();
        Order order = null;
        if (request.getSession().getAttribute("cart") != null) {
            order = (Order) request.getSession().getAttribute("cart");
        } else {
            order = new Order();
        }
        order.setIdUser(idUser);
//        order.setId((int) (System.currentTimeMillis() % 10000));
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

        float subTotal = 0;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < order.getOrderItems().size(); i++){
            subTotal += order.getOrderItems().get(i).getTotal();
            int idP = order.getOrderItems().get(i).getId();
            Product p = productService.findProduct(idP);
            products.add(p);
        }
        order.setSubTotal(subTotal);

        // Chuyển thông tin từ order (chưa có hình ảnh, tên sp) sang cart (hình ảnh, tên sp)
        request.getSession().setAttribute("products", products);
        request.getSession().setAttribute("order", order);
//        request.getSession().setAttribute("orderItem", order.getOrderItems());
        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request, response);

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