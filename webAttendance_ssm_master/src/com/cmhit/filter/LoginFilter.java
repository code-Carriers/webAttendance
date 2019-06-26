package com.cmhit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cmhit.po.Staff;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**

 * @Description

 * @Author 单继重

 * @Date 2017/04/16 10:16

 */

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		/*// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		 HttpServletRequest request = (HttpServletRequest)req;
		 HttpServletResponse response = (HttpServletResponse)resp;
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");
		 System.out.println("成功进入拦截页面");
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 Staff user = (Staff)session.getAttribute("user");
		 if(user != null){           
			 chain.doFilter(request,response);
		 } else{            
			 out.println("您还未登陆，三秒钟后跳转至登录页面");
		 response.setHeader("refresh","3;redirect:/login.jsp");
		 }*/
		  //1,doFilter 方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括表单数据、cookie和HTTP请求头）的完全访问。
        //第二个参数为ServletResponse，通常在简单的过滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或 JSP页。
		  HttpServletRequest request = (HttpServletRequest)req;
		  HttpServletResponse response = (HttpServletResponse)resp;
		  request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8");
          String currentURL = request.getRequestURI();//取得根目录所对应的绝对路径:
          String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());//截取到当前文件名用于比较
          System.out.println(targetURL);
          //HttpServletRequest.getSession(false) 等同于： 若当前存在Session则返回该会话，否则返回NULL，因此，一般情况下，尽量要明确使用参数为false的写法。
          HttpSession session = request.getSession(false);
          PrintWriter pw = response.getWriter();
          //首先判断是否当前界面是否为登录界面
            //判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
            if(session == null){
            	System.out.println("session为空");
               pw.write("<script>alert('您还没有登录!');</script>");
               //System.out.println("request.getContextPath()=" + request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/jsp/login_main/login.jsp");//如果session为空表示用户没有登录就重定向到login.jsp页面
            System.out.println(request.getContextPath());
            return;
          }
          //加入filter链继续向下执行
         //调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作为它的一个参数。
          //在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
          System.out.println("session为空2");
          chain.doFilter(request, response);
		 }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
