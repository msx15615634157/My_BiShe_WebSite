package orz.doublexi.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @ClassName : HtmlServlet
 * @author : 孟双喜
 * @desc :拦截html请求，转发给相应的do控制器
 * * @Date :2020/5/24 21:37
 */
@WebServlet(name = "HtmlServlet",value = "*.html")
public class HtmlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getServletPath();
        contextPath= contextPath.replace("/", "").replace("html","do");
        request.getRequestDispatcher(contextPath).forward(request,response);
    }
}
