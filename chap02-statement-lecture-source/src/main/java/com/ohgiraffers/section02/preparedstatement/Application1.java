package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        /* comment.
        *   PreparedStatement <- 준비된 statement
        *   Statement 는 SQL 쿼리문을 실행할 때마다 SQL 문이
        *   DBMS 에 전송이 되어 DBMS 에서 SQL 문을 파싱하고 컴파일을
        *   하는 과정을 거치게 된다. (똑같은 쿼리문이어도 계속 반복)
        *   PreparedStatement 는 최초에 한 번 실행했을 시
        *   SQL 문을 파싱하고 컴파일을 하지만,
        *   동일한 SQL 구문을 여러번 실행하게 되면
        *   최초에 컴파일한 SQL 구문을 재사용하게 된다
        *   따라서 파싱하고 컴파일하는 과정을 생략하게 되어
        *   성능이 향상이 된다.
        * */

        // 연결통로
        Connection con = getConnection();

        // 쿼리문 실행
        PreparedStatement pstmt = null;

        // 쿼리문 실행 결과
        ResultSet rset = null;

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE";

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            
            // 해당하는 값을 접근할때는 컬럼명으로 접근한다.
            while(rset.next()){
                System.out.println(rset.getString("EMP_ID") + "번 : " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt); // pstmt 는 stmt 의 자식이므로 close 메소드 사용가능
        }
    }
}

// String 타입으로 쿼리문을 작성하니 힘들다.. 체크! 여기서 문제가 발생하는 구나 다른 방식도 있다는 거에요
