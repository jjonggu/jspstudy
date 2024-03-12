package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

// 파일을 갖고있다는 어노테이션
// maxFileSize, maxRequestSize 익셉션 발생함 두개는
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50)
public class Upload extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 업로드 경로 (톰캣 내부 경로)
     String uploadPath = request.getServletContext().getRealPath("upload");
     
     File uploadDir = new File(uploadPath);
     if(!uploadDir.exists()) {
       uploadDir.mkdirs();
     }
     String originalFilename = null; 
     String filesystemName = null;
     String a = "";
     
     // 첨부된 파일 정보 (향상 for문 돌릴수있는 녀석 Collection)
     Collection<Part> parts = request.getParts();
     for(Part part : parts) {
//       System.out.println(part.getName() + "," + part.getContentType() + "," + part.getSize() + "," + part.getSubmittedFileName());
//       System.out.println(part.getHeader("Content-Disposition"));
       if(part.getHeader("Content-Disposition").contains("filename")) {
         if(part.getSize() > 0) {
           originalFilename = part.getSubmittedFileName();
         }
       }
       if(originalFilename != null) {
         int point = originalFilename.lastIndexOf(".");
         String extName = originalFilename.substring(point); // .jpg
         String fileName = originalFilename.substring(0, point);
         filesystemName = fileName + "_" + System.currentTimeMillis( ) + extName;
         
       }
       if(filesystemName != null) {
         part.write(uploadPath + File.separator + filesystemName);
       }
       if(filesystemName != null && originalFilename != null ) {
       a += "<div><a href=\"/servlet/download/filename=" + filesystemName + "\">" + originalFilename +"</a></div>";
       }
     }
     
     // 응답 
     response.setContentType("text/html; charset=UTF-8");
     PrintWriter out = response.getWriter();
     out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
     
     out.println("<div>첨부파일명 : " + originalFilename + "</div>");
     out.println("<div>저장파일명 : " + filesystemName + "</div>");
     out.println("<div>저장경로 : " + uploadPath + "</div>");
     out.println("<hr>");
     
     File[] files = uploadDir.listFiles();
     
   
     
     for(File file : files) {
       String filename1 =file.getName();  // 파일명_1234567890.jpg
       String ext = filename1.substring(filename1.lastIndexOf("."));
       String filename2 = filename1.substring(0, filename1.lastIndexOf("_"));
       out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(filename1, "UTF-8") + "\">" + filename2 + ext + "</a></div>");
     }
     
     out.flush();
     out.close();
     
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}