package com.example.case_study_md3.service;

import com.example.case_study_md3.model.EScale;
import com.example.case_study_md3.model.EStudio;
import com.example.case_study_md3.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    protected String jdbcURL = "jdbc:mysql://localhost:3306/figure_store?useSSL=false";
    protected String jdbcUsername = "root";
    protected String jdbcPassword = "Raisingthebar123@";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
    private final String SELECT_ALL = "SELECT * FROM products where (`deleteAt` is null)";
    private final String INSERT_PRODUCT = "INSERT INTO `products` (`name`, `price`, `leftQuantity`, `createAt`," +
            " `scale`, `idCategory`, `description`, `imgLink`, `studio`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_BY_ID = "SELECT * FROM products where id = ? and `deleteAt` is null";
    private final String UPDATE_PRODUCT = "UPDATE `products` SET `name` = ?, `price` = ?, `leftQuantity` = ?, `scale` = ?, `idCategory` = ?, `description` = ?, `imgLink` = ?, `studio` = ? where id = ? and `deleteAt` is null";
    private final String DELETE_PRODUCT = "UPDATE `products` SET `deleteAt` = ? where id = ? and `deleteAt` is null";
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println("Function find all product");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Product product = getProductFromRs(rs);
                products.add(product);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return products;
    }

    private static Product getProductFromRs(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getFloat("price"));
        product.setLeftQuantity(rs.getInt("leftQuantity"));
        product.setCreateAt(rs.getDate("createAt"));
        product.seteScale(EScale.getScaleByScale(rs.getString("scale")));
        product.setIdCategory(rs.getInt("idCategory"));
        product.setDescription(rs.getString("description"));
        product.setImgLink(rs.getString("imgLink"));
        product.setDeleteAt(rs.getDate("deleteAt"));
        product.seteStudio(EStudio.getStudio(rs.getString("studio")));
        return product;
    }

    public Product findProduct(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1,id);

            System.out.println("Function find product by id");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getProductFromRs(rs);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public void save (Product product){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2,product.getPrice());
            preparedStatement.setInt(3,product.getLeftQuantity());

            java.util.Date createAt = new java.util.Date();
            preparedStatement.setDate(4,new Date(createAt.getTime()));

            preparedStatement.setString(5,product.geteScale().getScale());
            preparedStatement.setInt(6,product.getIdCategory());
            preparedStatement.setString(7,product.getDescription());
            preparedStatement.setString(8,product.getImgLink());
            preparedStatement.setString(9,product.geteStudio().getName());

            System.out.println("Function save product");
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void update (int id, Product product){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2,product.getPrice());
            preparedStatement.setInt(3,product.getLeftQuantity());
            preparedStatement.setString(4,product.geteScale().getScale());
            preparedStatement.setInt(5,product.getIdCategory());
            preparedStatement.setString(6,product.getDescription());
            preparedStatement.setString(7,product.getImgLink());
            preparedStatement.setString(8,product.geteStudio().getName());
            preparedStatement.setInt(9,id);

            System.out.println("Function Update product");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void remove (int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1,new Date(deleteAt.getTime()));
            preparedStatement.setInt(2,id);

            System.out.println("Function remove product");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}
