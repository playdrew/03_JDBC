package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        /* Index. 1. Connection 연결통로 생성 */
        Connection con = getConnection();
        
        /* Index. 2. 통로를 통해 실행시킬 Query 문을 담을 객체 생성 */
        Statement stmt = null;
        
        /* Index. 3. 실행된 Query 문을 결과를 담을 객체 생성*/
        ResultSet rset = null;

        try {
            /* Index. 4. Connection 의 createStatement() 를 통한 인스턴스 생성 */
            stmt = con.createStatement(); // 생성을 하는데 통로를 통해서 생성한다

            Scanner sc = new Scanner(System.in);
            System.out.print("조회하실 사번을 입력해주세요 : ");
            String empId = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
            
            /* Index. 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet 으로 반환*/
            rset = stmt.executeQuery(query);
            
            /* Index. 6. ResultSet 에 담긴 결과 집합을 next()반복으로 출력*/
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID") + "번 : " + rset.getString("EMP_NAME"));
            } else {
                System.out.println("조회하신 사번의 이름이 없습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /* Index. 7. 사용한 자원 반납*/
            close(con);
            close(stmt);
            close(rset);
        }
    }
}

// 변수의 단점을 해결한게 배열 배열의 단점을 해결한게 컬렉션
