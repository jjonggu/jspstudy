package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {
  
  ActionForward getDate(HttpServletRequest reqeust);
  ActionForward getTime(HttpServletRequest reqeust);
  ActionForward getDateTime(HttpServletRequest reqeust);
  
  
}
