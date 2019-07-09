package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.UserDAO;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.dao.impl.UserDAOImpl;
import com.tongansz.model.SchoolUser;
import com.tongansz.model.User;
import com.tongansz.utils.DealDate;
import com.tongansz.utils.MD5;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport{

	public String Login() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		String user_password =  request.getParameter("user_password");
		String code =  request.getParameter("code");
		
		
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
			if(user.getUserPassword().equals(password)) {
				is_exit = 1; //3.都正确
				DealDate dd = new DealDate();
				user.setUserLoginTime(dd.getNowTime());
				ud.update(user);
			}
			else{
				is_exit = 0; //5.密码错误
			}
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		//System.out.println(is_exit);
		if(is_exit == 2) {
			out.println("NoUser");
			return null;
		}
		else if(is_exit ==1) {
			SetUserInfo(user_account);
			out.println("Success");
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
	
	
	public String SchLogin() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		String user_password =  request.getParameter("user_password");
		String code =  request.getParameter("code");
		
		
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		//System.out.println(code + " " + checkcode +" " + code.equals(checkcode));
		String password = MD5.md5(user_password);
		
		//0代表密码错误，1代表登录成功，2代表用户不存在，3代表验证错误
		int is_exit = 2;
		int flag = -1;
		SchoolUserDAO sud = new SchoolUserDAOImpl();
		List<SchoolUser> list = sud.QueryByAccount(user_account);
		
		
		if(list.size() == 0) {
			is_exit = 2; //1.如果用户不存在
		}
		else if(code.equals(checkcode) == false) {
			is_exit = 3; //2.验证码错误
		}
		else {
			SchoolUser schooluser = list.get(0);
			if(schooluser.getUserPassword().equals(password)) {
				is_exit = 1; //3.都正确
				DealDate dd = new DealDate();
				flag = schooluser.getUserFlag();
				schooluser.setUserLoginTime(dd.getNowTime());
				sud.update(schooluser);
			}
			else if(schooluser.getUserPassword().equals(password) && schooluser.getUserFlag() == -1) {
				is_exit = 2;
			}
			else{
				is_exit = 0; //5.密码错误
			}
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		//System.out.println(is_exit);
		if(is_exit == 2) {
			out.println("NoUser");
			return null;
		}
		else if(is_exit ==1&&flag == 0) {
			SetSchoolUserInfo(user_account);
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else if(is_exit == 0) {
			out.println("WrongAnswer");
			return null;
		}
		else if(is_exit ==1&&flag == -1){
			out.println("NoUser");
			return null;
		}
		else {
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
		request.getSession().invalidate();//清除 session 中的所有信息
		
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.QueryByAccount(account);
		User user = list.get(0);
		request.getSession().setAttribute("username", user.getUserName());
		request.getSession().setAttribute("userflag", user.getUserFlag());
		request.getSession().setAttribute("usertele", user.getUserPhone());
		request.getSession().setAttribute("useremail", user.getUserEmail());
		request.getSession().setAttribute("userlogintime", user.getUserLoginTime());
		request.getSession().setAttribute("userid", user.getUserId());
		request.getSession().setAttribute("password", user.getUserPasswords());
	}
	
	
	public String GetUserInfo() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		
		String username = (String)request.getSession().getAttribute("username");
		int userflag = (int)request.getSession().getAttribute("userflag");
		String usertele = (String)request.getSession().getAttribute("usertele");
		String useremail = (String)request.getSession().getAttribute("useremail");
		String userlogintime = (String)request.getSession().getAttribute("userlogintime");
		int userid = (int)request.getSession().getAttribute("userid");
		String password = (String)request.getSession().getAttribute("password");
		
		PrintWriter out = null;
		JSONObject  jo = new JSONObject();
		jo.put("username", username);
		jo.put("userflag", userflag);
		jo.put("usertele", usertele);
		jo.put("useremail", useremail);
		jo.put("userlogintime", userlogintime);
		jo.put("userid", userid);
		jo.put("password", password);
		JSONObject arr = JSONObject.fromObject(jo);
		//System.out.println(username +" " + usertele);
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr);
        out.flush(); 
        out.close();
        return null;
	}
	
	public void SetSchoolUserInfo(String account) throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		request.getSession().invalidate();//清除 session 中的所有信息
		
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByAccount(account);
		SchoolUser user = list.get(0);
		request.getSession().setAttribute("susername", user.getUserName());
		request.getSession().setAttribute("suserflag", user.getUserFlag());
		request.getSession().setAttribute("susertele", user.getUserPhone());
		request.getSession().setAttribute("suseremail", user.getUserEmail());
		request.getSession().setAttribute("suserlogintime", user.getUserLoginTime());
		request.getSession().setAttribute("sschoolid", user.getSchoolId());
		request.getSession().setAttribute("suserid", user.getUserId());
		request.getSession().setAttribute("spassword", user.getUserPasswords());
	}
	
	
	public String GetSchoolUserInfo() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		System.out.println(request.getSession().getAttribute("susername"));
		String username = (String)request.getSession().getAttribute("susername");
		int userflag = (int)request.getSession().getAttribute("suserflag");
		int schoolid = (int)request.getSession().getAttribute("sschoolid");
		String usertele = (String)request.getSession().getAttribute("susertele");
		String useremail = (String)request.getSession().getAttribute("suseremail");
		String userlogintime = (String)request.getSession().getAttribute("suserlogintime");
		int userid = (int)request.getSession().getAttribute("suserid");
		String password = (String)request.getSession().getAttribute("spassword");
		//System.out.println(password);
		PrintWriter out = null;
		JSONObject  jo = new JSONObject();
		jo.put("username", username);
		jo.put("userflag", userflag);
		jo.put("usertele", usertele);
		jo.put("useremail", useremail);
		jo.put("userlogintime", userlogintime);
		jo.put("schoolid", schoolid);
		jo.put("userid", userid);
		jo.put("password", password);
		JSONObject arr = JSONObject.fromObject(jo);
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr);
        out.flush(); 
        out.close();
        return SUCCESS;
	}
}
