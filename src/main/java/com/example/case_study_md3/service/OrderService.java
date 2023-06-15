package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    protected String jdbcURL = "jdbc:mysql://localhost:3306/duan_banhang?allowPublicKeyRetrieval=true&useSSL=false";
    protected String jdbcUsername = "root";
    protected String jdbcPassword = "Raisingthebar123";

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
    ;

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

    public Order findOrder(int id){
        return null;
    }
    public void save (Order order){

    }
    public void update (int id, Order order){

    }
    public void remove (int id){

    }
}
