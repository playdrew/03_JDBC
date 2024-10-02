package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application5 {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 성씨를 입력해주세요 : ");
        String surname = sc.nextLine();
        List<EmployeeDTO> empDTO = null;

        //프로퍼티파일에 SQL 작성
        Properties prop = new Properties();

        try {

            // prop 에 query.xml파일을 읽었으느까
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml")
            );

            // prop 에 값을 가져오기 위한 키값을 적으면 벨류도 나옴
            // properties 는 키(entry key)와 벨류(entry내부)가 모두 문자열
            String query = prop.getProperty("selectByFamilyName");
            System.out.println("query = " + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,surname);
            rset = pstmt.executeQuery();

            empDTO = new ArrayList<>();

            while (rset.next()){
                emp = new EmployeeDTO();
                emp.setEmpId(rset.getString("EMP_ID"));
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("PHONE"));
                emp.setDeptCode(rset.getString("DEPT_CODE"));
                emp.setJobCode(rset.getString("JOB_CODE"));
                emp.setSalLevel(rset.getString("SAL_LEVEL"));
                emp.setSalary(rset.getInt("SALARY"));
                emp.setBonus(rset.getDouble("BONUS"));
                emp.setManagerId(rset.getString("MANAGER_ID"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));

                empDTO.add(emp);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        for(EmployeeDTO emp1 : empDTO) {
            System.out.println("emp1 = " + emp1);
        }
    }
}

