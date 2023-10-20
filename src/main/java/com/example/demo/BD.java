package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public static Product search_product(String code) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_products";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM products WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet2 = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet2.next()) {

            code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String description = resultSet2.getString("description");
            String id_supplier = resultSet2.getString("id_supplier");

            return new Product(code, name, description, id_supplier);

        }
        // Cerrar recursos
        resultSet2.close();
        statement.close();
        connection.close();

        return new Product(null,null,null,null);
    }

    public static int Delete(String code) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_products";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM products WHERE code = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, code);
        int f = prepareStatement.executeUpdate();

        if (f > 0){
            return 1;
        }else {
            return 0;
        }
    }

    public static int Delete() throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_products";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM products";

        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);

        int f = prepareStatement.executeUpdate();

        System.out.println("Producto eliminado correctamente");
        return f;

    }

    public String Select_supplier(String id_supplier) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_products";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM suppliers WHERE id_supplier = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, id_supplier);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_supplier = resultSet.getString("id_supplier");
            return id_supplier;

        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }

    public Product Register_product(String code, String name, String description, String id_supplier) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/api_products";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            String sql = "INSERT INTO products (code, name , description, id_supplier) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, id_supplier);

            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("La relacion se registro de manera exitosa.");
                return new Product(code, name, description, id_supplier);
            } else {
                System.out.println(Errors.error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Product(null, null,null,null);
    }

    public Product edit_product(String code, String name, String description, String id_supplier) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_products";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();
        String consulta = "UPDATE products SET name = ?, description = ?, id_supplier = ? WHERE code = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, id_supplier);
        preparedStatement.setString(4, code);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Identidad actualizada exitosamente");
            return new Product(code, name, description, id_supplier);
        } else {
            System.out.println(Errors.error_edit);
        }

        preparedStatement.close();
        connection2.close();

        return new Product(null,null,null,Errors.error_id_supplier);
    }

    public List<Product> search_products() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/api_products";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM products");
        List<Product> list = new ArrayList<>();

        while(resultSet2.next()){

            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String description = resultSet2.getString("description");
            String id_supplier = resultSet2.getString("id_supplier");

            Product product = new Product(code, name, description, id_supplier);
            list.add(product);
        }
        return list;
    }

    }




