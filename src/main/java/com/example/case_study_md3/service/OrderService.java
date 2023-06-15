package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    protected String jdbcURL = "jdbc:mysql://localhost:3306/figure_store?useSSL=false";
    protected String jdbcUsername = "root";
    protected String jdbcPassword = "Raisingthebar123@";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);


        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    protected void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private static final String SECLECT_ALL_PAID_ORDERS = "SELECT * FROM orders  where isPaid = true;";
    private static final String SECLECT_ALL_UNPAID_ORDERS = "SELECT * FROM orders  where isPaid = false;";
    private static final String CREATE_ORDER = "INSERT INTO `orders` (`createAt`, `idUser`, `isPaid`, `subTotal`, `discount`) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders  where id = ?;";
    private static final String UPDATE_ORDER = "UPDATE `orders` SET `idUser` = ?, `isPaid` = ?, `subTotal` = ?, `discount` = ? WHERE (`id` = ?);";
    private static final String DELETE_ORDER = "DELETE FROM `orders` WHERE (`id` = ?);";
    public List<Order> findAllPaidOrders(){
        List<Order> orders = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SECLECT_ALL_PAID_ORDERS);

            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
            }
//            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }
    public List<Order> findAllUnPaidOrders(){
        List<Order> orders = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SECLECT_ALL_UNPAID_ORDERS);

            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
            }
//            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }
    public Order findOrderById(int id){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID);

            System.out.println("Find Order by ID " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                return order;
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
    public Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date sqlCreateAt = rs.getDate("createAt");
        java.util.Date createAt = null;
        if (sqlCreateAt != null) {
            createAt = new java.util.Date(sqlCreateAt.getTime());
        }
        int idUser = rs.getInt("idUser");
        boolean isPaid = rs.getBoolean("isPaid");
        float subTotal = rs.getFloat("subTotal");
        float discount = rs.getFloat("discount");

        Order order = new Order(id, createAt, idUser, isPaid, subTotal, discount);
        return order;
    }


    public void save (Order order){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_ORDER);
            java.util.Date createAt = new java.util.Date();
            //`createAt`, `idUser`, `isPaid`, `subTotal`, `discount`
            ps.setDate(1, new java.sql.Date(createAt.getTime()));
            ps.setInt(2, order.getIdUser());
            ps.setBoolean(3, order.isPaid());
            ps.setFloat(4, order.getSubTotal());
            ps.setFloat(5, order.getDiscount());

            System.out.println("function create " + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void update (int id, Order order){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER);
            ps.setInt(1, order.getIdUser());
            ps.setBoolean(2, order.isPaid());
            ps.setFloat(3, order.getSubTotal());
            ps.setFloat(4, order.getDiscount());
            ps.setInt(5, id);

            System.out.println("Function update"+ ps);
            connection.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void remove (int id){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER);
            ps.setInt(1, id);
            System.out.println("Function remove order" + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
