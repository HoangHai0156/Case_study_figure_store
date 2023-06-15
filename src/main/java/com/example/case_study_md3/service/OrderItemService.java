package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService  { //extends DBContext
    protected String jdbcURL = "jdbc:mysql://localhost:3306/figure_store?allowPublicKeyRetrieval=true&useSSL=false";
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
    private static final String SELECT_ORDERITEM_BY_IDORDER = "SELECT * FROM orderitems where idOrder = ?;";
    private static final String SAVE_ORDERITEM = "INSERT INTO `figure_store`.`orderitems` (`id`, `idOrder`, `idProduct`, `quantity`, `total`) VALUES (?, ?, ?, ?, ?);\n";
    private static final String UPDATE_ORDERITEM = "UPDATE `figure_store`.`orderitems` SET  `idProduct` = ?, `quantity` = ?, `total` = ? WHERE (`id` = ?);\n";
    private static final String DELETE_ORDERITEM = "DELETE FROM orderitems WHERE (`id` = ?);";
    public List<OrderItem> findOrderItemByIdOrder(){
        List<OrderItem> orderItems = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ORDERITEM_BY_IDORDER);

            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem orderItem = getOrderItemFromResultSet(rs);
            }
//            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orderItems;

    }
    public OrderItem getOrderItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idOrder = rs.getInt("idOrder");
        int idProduct = rs.getInt("idProduct");
        int quantity = rs.getInt("quantity");
        float total = rs.getFloat("total");

        OrderItem orderItem = new OrderItem(id, idOrder, idProduct, quantity, total);
        return orderItem;
    }
    public void save (OrderItem orderItem)  {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_ORDERITEM);
            ps.setInt(1, orderItem.getId());
            ps.setInt(2, orderItem.getIdOrder());
            ps.setInt(3, orderItem.getIdProduct());
            ps.setInt(4, orderItem.getQuantity());
            ps.setFloat(5, orderItem.getTotal());

            ps.executeUpdate();
            System.out.println("save orderItem " + ps);

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void update (int id, OrderItem orderItem){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDERITEM);
           //`idProduct` = ?, `quantity` = ?, `total` = ? WHERE (`id` = ?)
            ps.setInt(1, orderItem.getIdProduct());
            ps.setInt(2, orderItem.getQuantity());
            ps.setFloat(3, orderItem.getTotal());
            ps.setInt(4, id);
            ps.executeUpdate();

            System.out.println("Update orderItem " + ps);
            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void remove (int id){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDERITEM);

            ps.setLong(1, id);
            System.out.println("Remove orderItem" + ps);
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
