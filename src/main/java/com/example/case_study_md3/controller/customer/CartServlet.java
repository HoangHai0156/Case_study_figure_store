package com.example.case_study_md3.controller.customer;

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
                addToCart(request, response, action);
                break;
            case "change-quantity":
                changeQuantity(request, response, action);
                break;
            default:
                showCart(request, response);
                break;
        }
    }

    private void changeQuantity(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        addToCart(request, response, action);

    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request,response);
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProduct(idProduct);

        /** Hiển thị ra cart chưa cần

        User user = (User) request.getSession().getAttribute("user");
        int idUser = user.getId();
         idOrder
         **/
        int quantity = 1;
        if (action.equals("change-quantity")) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        Order order = null;
        if (request.getSession().getAttribute("cart") != null) {
            order = (Order) request.getSession().getAttribute("cart");
        } else {
            order = new Order();
        }

        if (order.getOrderItems() == null) {
            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = new OrderItem(idProduct, 1) ;
            orderItems.add(orderItem);

            order.setOrderItems(orderItems);
        } else {
            boolean check = checkIdProductExistOrder(idProduct, order);
            if (check) {
                updateProductInOrder(idProduct, quantity, order);
            } else {
                addProductToOrder(idProduct, quantity, order);
            }
        }

        List<Product> products = new ArrayList<>();
//        products.add(product);
        for (int i = 0; i < order.getOrderItems().size(); i++){
            OrderItem orderItem = order.getOrderItems().get(i);
            Product p = productService.findProduct(orderItem.getIdProduct());
            products.add(p);
        }

        // Chuyển thông tin từ order (chưa có hình ảnh, tên sp) sang cart (hình ảnh, tên sp)
        request.setAttribute("products", products);
        HttpSession session = request.getSession();
        session.setAttribute("order", order);

        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request, response);

    }
    private void addProductToOrder(int idProduct, int quantity, Order order) {
        OrderItem orderItem = new OrderItem(idProduct, quantity);
        order.getOrderItems().add(orderItem);
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