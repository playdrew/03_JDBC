package com.ohgiraffers.practice.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application5 {

    public static void main(String[] args) {

        Connection con = null;
        Properties prop = new Properties();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee","ohgiraffers","ohgiraffers");
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee",prop);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
