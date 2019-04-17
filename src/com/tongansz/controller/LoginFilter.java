package com.tongansz.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginFilter extends HttpServlet implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void destroy() {
    }
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println(111);
		HttpServletRequest request=(HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();  //获取请求的地址
        String contextPath=request.getContextPath();  //得到相对路径
        String username = (String) session.getAttribute("username");
        String susername = (String) session.getAttribute("susername");
//        Integer roleId =  (Integer) session.getAttribute("roleId");
        //System.out.println("roleId:"+roleId);
        //String username = (String) session.getAttribute("user");
       // System.out.println("username:"+username);
       // System.out.println("susername:"+susername);
        //System.out.println(contextPath);
       // System.out.println(url);
         if(url.indexOf("welcome.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf("login_sch.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf("login_edu.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf("register_edu.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf("register_sch.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf("forget_pwd.html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 chain.doFilter(req, res);
         }
         else if(url.indexOf(".html")>-1&&(username==null || username == "" )&&( susername == null || susername == "")){
        	 response.sendRedirect(contextPath + "/welcome.html"); 
         }
         else{
        	 chain.doFilter(req, res);
         }
        
        
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
