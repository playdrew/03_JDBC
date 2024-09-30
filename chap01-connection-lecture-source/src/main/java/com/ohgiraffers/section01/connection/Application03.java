package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {

    public static void main(String[] args) {

        // properties 는 맵형식으로 키와 값을 가지고 있으나 차이점은 전부 String 이라는 점이다

        /* Index. 1. Properties 파일 읽기 위해 Properties 객체 생성 */
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            System.out.println("prop = " + prop); // prop = {password=ohgiraffers, driver=com.mysql.cj.jdbc.Driver, user=ohgiraffers, url=jdbc:mysql://localhost/employee}
            // 맵의 특징 : 저장순서를 기억하지 않는다 .
            // 적은 순서대로 안나온다

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);

            con = DriverManager.getConnection(url,user,password);

            System.out.println("con = " + con);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
