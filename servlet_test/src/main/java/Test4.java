

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 요청
    request.setCharacterEncoding("UTF-8");
   
    String author = request.getParameter("author");
    String title = request.getParameter("title");
    String text = request.getParameter("text");
    
    LocalDate now = LocalDate.now();        
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");     
    String formatedNow = now.format(formatter);
   
    FileWriter fileWriter = new FileWriter("./"+formatedNow+"-"+author+"-"+title+".txt");
    fileWriter.write(text);
    fileWriter.close();
    
   //응답
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<script> alert(\""+formatedNow+"-"+author+"-"+title+".txt 파일이 생성되었습니다\")</script>");
    out.flush();
    out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  doGet(request, response);
	}

}
