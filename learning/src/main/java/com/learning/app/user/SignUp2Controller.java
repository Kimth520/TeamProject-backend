package com.learning.app.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learning.app.Execute;
import com.learning.app.Result;
import com.learning.app.dao.UserDAO;

public class SignUp2Controller implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // DAO 객체 생성
        UserDAO userDAO = new UserDAO();

        // 결과 객체 생성
        Result result = new Result();
        
        // 폼 데이터 가져오기
        String userPhone = request.getParameter("userPhone");
        
        // 전화번호 중복 확인 
        int checkPhone = userDAO.checkPhone(userPhone);
        System.out.println("중복이면 1, 미중복이면 0: " + checkPhone);
        
        if (checkPhone == 0) {  // 중복 없음
            // 세션에 전화번호 저장
            HttpSession session = request.getSession();
            session.setAttribute("userPhone", userPhone);
            
            // 다음 페이지로 이동 forward 방식
            result.setPath("/app/user/signUp/signUp3.jsp");
            result.setRedirect(false);
        } else {  // 중복
            // 현재 페이지로 이동
            // forward 방식
            result.setPath("/app/user/signUp/signUp2.jsp");
            result.setRedirect(false);
            request.setAttribute("checkPhone", checkPhone);
        }

        return result;
    }
}
