package com.ohgiraffers.practice.practice;

import java.sql.Connection;

import static com.ohgiraffers.practice.practice.JDBCTemplate.close;
import static com.ohgiraffers.practice.practice.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        Connection con = getConnection();
        close(con);
        System.out.println("con = " + con);

        Connection con2 = getConnection();
        close(con2);
        System.out.println("con2 = " + con2);
    }
}
