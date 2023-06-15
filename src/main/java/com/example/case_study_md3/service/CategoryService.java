package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Category;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CategoryService {
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
    private final String SELECT_ALL = "SELECT * FROM categories where `deleteAt` is null";
    private final String SELECT_BY_ID = "SELECT * FROM categories where `id` = ? and `deleteAt` is null";
    private final String INSERT_CATEGORY = "INSERT INTO `categories` (`name`) VALUES (?)";
    private final String UPDATE_CATEGORY = "UPDATE `categories` SET `name` = ? WHERE `id` = ? and `deleteAt` is null";
    private final String DELETE_CATEGORY = "UPDATE `categories` SET `deleteAt` = ? WHERE (`id` = ?)";
    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println("Function find all category");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Category category = getCategoryFromRs(rs);

                categories.add(category);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return categories;
    }

    private static Category getCategoryFromRs(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setDeleteAt(rs.getDate("deleteAt"));
        return category;
    }
    public Map<Integer,Category> getCategoryMap(){
        Map<Integer,Category> categoryMap = new HashMap<>();
        for (Category category: findAll()){
            categoryMap.put(category.getId(),category);
        }
        return categoryMap;
    }

    public Category findCategory(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1,id);
            System.out.println("Function find category");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getCategoryFromRs(rs);
            }
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public void save(Category category){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {

            preparedStatement.setString(1, category.getName());
            System.out.println("Function insert category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void update(int id, Category category){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {

            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,id);

            System.out.println("Function update category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void remove(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1,new java.sql.Date(deleteAt.getTime()));
            preparedStatement.setInt(2,id);

            System.out.println("Function delete category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}
