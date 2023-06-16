package com.example.case_study_md3.controller.customer;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;


import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService= new OrderService();
        orderItemService = new OrderItemService();
        userService = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addToCart(request, response, action, session);
                break;
            case "change":
                changeQuantity(request, response, action, session);
                break;
            default:
                showCart(request, response, session);
                break;
        }
    }

    private void changeQuantity(HttpServletRequest request, HttpServletResponse response, String action, HttpSession session) throws ServletException, IOException {
        addToCart(request, response, action, session);

    }

    private void showCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        User user = (User) session.getAttribute("user");
        if (user != null){
            Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
            List<Product> products = productService.findAll();
            List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
            unpaidOrder.setOrderItems(orderItems);

            request.setAttribute("order",unpaidOrder);
            request.setAttribute("products",products);
        }
        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request,response);
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response, String action, HttpSession session) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProduct(idProduct);

        List<Product> allProducts = productService.findAll();
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        User user = (User) session.getAttribute("user");
        if (user == null){
            response.sendRedirect("/user?action=login");
            return;
        }
        Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
        String cartMess;


        /** Hiển thị ra cart chưa cần

        User user = (User) request.getSession().getAttribute("user");
        int idUser = user.getId();
         idOrder

        int quantity = 1;
        if (action.equals("change-quantity")) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        Order order = null;
        if (request.getSession().getAttribute("order") != null) {
            order = (Order) request.getSession().getAttribute("order");
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
                updateProductInOrder(idProduct, quantity + 1, order);
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
         **/

        if (quantity > product.getLeftQuantity() || quantity > 10){
            cartMess = "Số lượng muốn mua không hợp lệ !";

            request.setAttribute("product",product);
            request.setAttribute("cartMess",cartMess);
//            response.sendRedirect("/product?action=view&id="+idProduct);
//            return;
            request.getRequestDispatcher(Config.ADMIN_TO_PRODUCT+"view-product.jsp").forward(request,response);
        }else {
            if (unpaidOrder == null){
                Order newOrder = new Order();
                newOrder.setIdUser(user.getId());
                orderService.save(newOrder);
                unpaidOrder = orderService.findUserUnPaidOrder(user.getId());

                OrderItem orderItem = new OrderItem();
                setNullOrderItem(idProduct, product, quantity, unpaidOrder, orderItem);
                orderItemService.save(orderItem);

                float subTotal = getSubTotal(unpaidOrder);
                newOrder.setSubTotal(subTotal);
                orderService.update(unpaidOrder.getId(), newOrder);
            }else {
                OrderItem orderItem = orderItemService.findOrderItem(unpaidOrder.getId(), idProduct);
                if (orderItem != null){
                    if (action.equals("add")){
                        quantity = orderItem.getQuantity() + quantity;
                    }
                    if (quantity > 10){
                        cartMess = "Số lượng muốn mua không hợp lệ !";

                        request.setAttribute("product",product);
                        request.setAttribute("cartMess",cartMess);
                        request.getRequestDispatcher(Config.ADMIN_TO_PRODUCT+"view-product.jsp").forward(request,response);
                        return;
                    }
                    orderItem.setQuantity(quantity);
                    orderItem.setTotal(quantity * product.getPrice());
                    orderItemService.update(orderItem.getId(), orderItem);

                }else {
                    orderItem = new OrderItem();
                    setNullOrderItem(idProduct,product,quantity,unpaidOrder,orderItem);
                    orderItemService.save(orderItem);

                }
                float subTotal = getSubTotal(unpaidOrder);
                unpaidOrder.setSubTotal(subTotal);
                orderService.update(unpaidOrder.getId(), unpaidOrder);
            }

            List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
            unpaidOrder.setOrderItems(orderItems);

            request.setAttribute("order",unpaidOrder);
            request.setAttribute("products",allProducts);
            request.getRequestDispatcher(Config.HOMEPAGE+"cart.jsp").forward(request,response);
        }

    }

    private static void setNullOrderItem(int idProduct, Product product, int quantity, Order unpaidOrder, OrderItem orderItem) {
        orderItem.setIdOrder(unpaidOrder.getId());
        orderItem.setIdProduct(idProduct);
        orderItem.setQuantity(quantity);
        orderItem.setTotal(quantity * product.getPrice());
    }

    private float getSubTotal(Order unpaidOrder) {
        float subTotal = 0;
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
        for (OrderItem o : orderItems){
            subTotal += o.getTotal();
        }
        return subTotal;
    }

    /**
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
     **/

    public void destroy() {
    }

    public static void main(String[] args) {

    }
}