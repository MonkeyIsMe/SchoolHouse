package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.UserDAO;
import com.csu.dao.impl.UserDAOImpl;
import com.csu.model.User;
import com.csu.utils.MD5;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport{

	public String Login() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		String user_password =  request.getParameter("user_password");
		String code =  request.getParameter("code");
		String uflag = request.getParameter("uflag");
		
		int flag = Integer.valueOf(uflag);
		
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		//System.out.println(code + " " + checkcode +" " + code.equals(checkcode));
		String password = MD5.md5(user_password);
		
		//0代表密码错误，1代表登录成功，2代表用户不存在，3代表验证错误
		int is_exit = 2;
		
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.QueryByAccount(user_account);
		
		
		if(list.size() == 0) {
			is_exit = 2; //1.如果用户不存在
		}
		else if(code.equals(checkcode) == false) {
			is_exit = 3; //2.验证码错误
		}
		else {
			User user = list.get(0);
			if(user.getUserPassword().equals(password) && user.getUserFlag() == flag) {
				is_exit = 1; //3.都正确
			}
			else if(user.getUserPassword().equals(password) && user.getUserFlag() != flag) {
				is_exit = 2; //4.用户身份不匹配
			}
			else{
				is_exit = 0; //5.密码错误
			}
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		System.out.println(is_exit);
		if(is_exit == 2) {
			out.println("NoUser");
			return null;
		}
		else if(is_exit ==1 && flag == 1) {
			out.println("JYJ");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else if(is_exit ==1 && flag == 0) {
			out.println("XX");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else if(is_exit == 0) {
			out.println("WrongAnswer");
			return null;
		}
		else{
			out.println("WrongCode");
			return null;
		}
	}
	
	public String Logout() throws IOException{
		
		HttpServletRequest request= ServletActionContext.getRequest();
		request.getSession().invalidate();//清除 session 中的所有信息
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println("Success");
        out.flush(); 
        out.close(); 
		return null;
	}
	
	
	public void SetUserInfo(String account) throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.QueryByAccount(account);
		User user = list.get(0);
		request.getSession().setAttribute("username", user.getUserName());
		request.getSession().setAttribute("userflag", user.getUserFlag());
		request.getSession().setAttribute("usertele", user.getUserPhone());
		request.getSession().setAttribute("useremail", user.getUserEmail());
		request.getSession().setAttribute("userlogintime", user.getUserLoginTime());
	}
	
	
	public String GetUserInfo() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String username = (String)request.getSession().getAttribute("username");
		String userflag = (String)request.getSession().getAttribute("userflag");
		String usertele = (String)request.getSession().getAttribute("usertele");
		String useremail = (String)request.getSession().getAttribute("useremail");
		String userlogintime = (String)request.getSession().getAttribute("userlogintime");
		
		PrintWriter out = null;
		JSONObject  jo = new JSONObject();
		jo.put("username", username);
		jo.put("userflag", userflag);
		jo.put("usertele", usertele);
		jo.put("useremail", useremail);
		jo.put("userlogintime", userlogintime);
		JSONObject arr = JSONObject.fromObject(jo);
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr);
        out.flush(); 
        out.close();
        return SUCCESS;
	}
}
