package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String action = req.getParameter("action");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/user?action=login");
            return;
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                int idOrder = Integer.parseInt(req.getParameter("id"));
                List<Product> products = productService.findAll();
                List<OrderItem> orderItems = orderItemService.findAllByIdOrder(idOrder);
                Order order = orderService.findOrderById(idOrder);
                order.setOrderItems(orderItems);

                req.setAttribute("order",order);
                req.setAttribute("products",products);
                req.getRequestDispatcher(Config.ADMIN_TO_ORDER+"view-order.jsp").forward(req,resp);
                break;
            default:
                List<User> users = userService.findAll();
                List<Order> orders = orderService.findAll();

                req.setAttribute("orders",orders);
                req.setAttribute("users",users);
                req.getRequestDispatcher(Config.ADMIN_TO_ORDER+"list-order.jsp").forward(req,resp);
                break;
        }
    }
}
