package com.ohgiraffers.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        // 하나의 메뉴를 삭제하고 싶어요 그러면 기준을 뭘로 잡으면 좋을까요
        // 고유숫자인 menu_code 로 해요
        // 기획의도에 따라 name 을 삭제해야할수도 이써요 근데 다 삭제될수 있음을 알아야해요
        // unique 한 것으로 삭제하는게 좋아요 아니면 같은 조건을 가진 녀석들도 다 삭제되니까요

        Scanner sc = new Scanner(System.in);
        // 클래스 사용할 필요가 없어요
        // 1개의 행을 삭제할 수 있는 값만 있으면 된다.
        System.out.print("삭제할 메뉴 코드를 입력해주세요 : ");
        int menuCode = sc.nextInt();

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu_query.xml"
                    )
            );

            // 키값설정
            String query = prop.getProperty("deleteMenu");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,menuCode);
            result = pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
        if(result>0){
            System.out.println(menuCode + "번 삭제 성공!!");
        }else{
            System.out.println("삭제 실패...");
        }
    }
}

// delete update insert 는 행이 영향을 받은 것을 반환해주는 result 
// dml 구문은 반환할 값이 없기 때문에 행의 변화가 생긴 만큼 정수값을 return 해준다
