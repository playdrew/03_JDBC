package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4_1 {
    public static void main(String[] args) {

        /*Index. 1. EMPLOYEE 테이블에서 조회할 사원의 성씨를 입력받아
         *           해당하는 성을 가지고 있는 사원의 정보를 모두 출력*/
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 성씨를 입력해주세요 : ");
        String surname = sc.nextLine();
        List<EmployeeDTO> empDTO = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,surname+"%");
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
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        for(EmployeeDTO emp1 : empDTO){
            System.out.println("emp1 = " + emp1);
        }
    }
}
