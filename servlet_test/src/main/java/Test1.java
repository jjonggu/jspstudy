

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   // 요청
    request.setCharacterEncoding("UTF-8");
    
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String name = request.getParameter("name");
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    
    String country = request.getParameter("country");
    String tel = request.getParameter("tel");
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    
    PrintWriter out = response.getWriter();
    out.println("<div>");
    out.println("<ul>");
    out.println("<li>아이디: " + id + "</li>");
    out.println("<li>비밀번호: " + pw + "</li>");
    out.println("<li>이름: " + name + "</li>");
    out.println("<li>생년월일: " + year +"년 "+ month+"월 "+ day +"일"+"</li>");
    out.println("<li>성별: " + gender + "</li>");
    out.println("<li>이메일: " + email  + "</li>");
    out.println("<li>휴대전화: " + country + " "+ tel + "</li>");
    out.println("</ul>");
    out.println("</div>");
    out.flush();
    out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
