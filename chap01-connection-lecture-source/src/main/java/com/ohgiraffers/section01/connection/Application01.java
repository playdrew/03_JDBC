package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

    public static void main(String[] args) {
        
        /* comment. DB 접속을 위한 Connection 인스턴스 생성 */
        // null 로 초기화 시키는 이유
        // finally 블럭에서 자원해제를 위한 과정

        Connection con = null;

        try {
            /* comment. 사용할 드라이버 등록 */
            // jdbc 란 기술을 사용할 건데 mysql 에 특화된 드라이버를 쓰겠다 명시
            Class.forName("com.mysql.cj.jdbc.Driver");

            /* comment. connection 객체는 인터페이스이기 때문에
            *           직접적으로 인스턴스 생성이 불가능하다.
            * */

            // 어떤 데이터베이스 스키마에 접속할지 getConnection 에 정보를 준다.
            // localhost 란 우리가 가진 컴퓨터이다. 두번째 인자는 connection 에 대한 아이디 세번째 인자는 connection 에 대한 password
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee","ohgiraffers","ohgiraffers");
            // 통로를 열어주고 통로에 대한 정보를 출력
            System.out.println("con = " + con);

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
