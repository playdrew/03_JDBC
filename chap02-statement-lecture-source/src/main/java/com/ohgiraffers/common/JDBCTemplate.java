package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    /* title. jdbc 커넥션 정보를 하나의 틀로 만들어 필요한 곳에서 호출 */
    // 싱글톤 메소드 : 공유하기 위한 목적으로 메소드를 만들었다 .
    public static Connection getConnection(){

        Connection con = null;

        Properties prop = new Properties();

        try {

            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver =  prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url,prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } // finally close 하면 안된다.

        return con;
    }

    public static void close(Connection con){

        //비트연산자 => 좌항과 우항을 모두 비교
        //&는 &&는 왼쪽항이 false 면 뒤에 항은 비교하지도 않는데
        //비트연산자 &는 왼쪽항이 false 도 둘다 비교한다
        try {
            if(con != null & !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt){

        //비트연산자 => 좌항과 우항을 모두 비교
        //&는 &&는 왼쪽항이 false 면 뒤에 항은 비교하지도 않는데
        //비트연산자 &는 왼쪽항이 false 도 둘다 비교한다
        try {
            if(stmt != null & !stmt.isClosed()){
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset){

        //비트연산자 => 좌항과 우항을 모두 비교
        //&는 &&는 왼쪽항이 false 면 뒤에 항은 비교하지도 않는데
        //비트연산자 &는 왼쪽항이 false 도 둘다 비교한다
        try {
            if(rset != null & !rset.isClosed()){
                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
