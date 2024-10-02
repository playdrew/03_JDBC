package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        /*comment
        *   ?
        *   - placeholder
        *   - ? 의 갯수, 시작값 (1)
        *   */

        // ? 에 대한 값을 동적으로 셋팅 바뀔 부분만 ?로 놓게 되면 전과정은 미리 컴파일(SELECT ... )하면
        // ?로 바뀌는 부분은 동적으로 바꿔준다.

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해주세요 : ");
        String empId = sc.nextLine();

        
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? ";

        try {
            pstmt = con.prepareStatement(query);
            
            // 첫번째 ? 에 empId 를 넣어주어라
            pstmt.setString(1,empId);
            rset = pstmt.executeQuery();

            // 한번만 실행하므로 rset 에 조회한 결과물이 존재한다면
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID") + "번 : " + rset.getString("EMP_NAME"));
            } else {
                System.out.println("조회하신 사번이 없습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }
    }
}
// 수행한 결과를 가져가서 다시 사용하는 캐싱
// ? 를 쓸 수 있다는 것이 좋습니다