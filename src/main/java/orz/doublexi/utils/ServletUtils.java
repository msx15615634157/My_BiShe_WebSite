package orz.doublexi.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtils {
public static HttpServletRequest getRequest() {
	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	return request;
}
public static HttpServletResponse getResponse() {
	HttpServletResponse response = (HttpServletResponse) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	return response;
}
public static HttpSession getSession(){
	HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	return session;
}
public static ServletContext getContext(){
	ServletContext context = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
	return  context;
}
public static boolean checkStatus() {
	if(getRequest().getSession().getAttribute("loginuser")!=null) {
	return true;
	}
	return false;
}
}
