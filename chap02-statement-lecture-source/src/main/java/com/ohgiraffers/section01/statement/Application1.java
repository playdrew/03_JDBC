package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        // 데이터베이스와 소통하기 위한 Connection
        Connection con = getConnection();

        // 자바로 작성한 인텔리제이에서 마이에스큐엘을 연결통로로 커넥션을 만들었는데
        // 지금 필요한건 뭐 조회해죠 라는 것이구요. 보낼 무엇인가와 받을 무언가가 필요해요
        // 이걸 스테이트먼트라고 해요 통로를 지나가서 인스턴스를 생성

        /*comment.
        *   쿼리문을 저장하고 실행할 수 있는 기능을 하는 인터페이스
        *   Statement
        * */

        // 인터페이스이기 때문에 직접적으로 인스턴스를 생성할 수 없습니다

        Statement stmt = null;
        /*comment.
        *   select 결과 집합을 받아올 수 있는 인터페이스
        *   ResultSet
        * */

        ResultSet rset = null; // 인터페이스기 때문에 직접적 인스턴스 생성 못하고 null 로서 초기화

        try {
            stmt = con.createStatement(); // connection 을 이용해서 인스턴스 생성후 쿼리문 저장하고 실행시킬수있는 
            rset = stmt.executeQuery("SELECT EMP_ID , EMP_NAME FROM EMPLOYEE"); // string sql 을 전달인자 SQL 구문은 구분을 위해서 대문자로 많이쓴다 rsert은 데이터베이스가 들어갔다
            
            while(rset.next()){ // rset에 다음요소가 있는지 next를 호출
                /*comment.
                *  next() : ResultSet 을 목록화시켜 행이 존재하면 true
                *  존재하지 않으면 false 를 반환한다.*/
                System.out.println(rset.getString("EMP_ID")+ "번 : " + rset.getString("EMP_NAME")); // KEY : EMP_ID VALUE 200번 ...
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con); // 연결통로는 닫았어요 stmt rset 도 닫아줘야해요
            close(stmt);
            close(rset);
        }
    }
}
