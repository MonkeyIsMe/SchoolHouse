package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.StudentDAO;
import com.csu.dao.impl.StudentDAOImpl;
import com.csu.model.Student;
import com.csu.utils.DealDate;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport{

public String AddStudent() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String student_name = request.getParameter("student_name");
		String student_idcard = request.getParameter("student_idcard");
		String student_father = request.getParameter("student_father");
		String student_mother = request.getParameter("student_mother");
		String student_houseowner = request.getParameter("student_houseowner");
		String student_ownerid = request.getParameter("student_ownerid");
		String student_relation = request.getParameter("student_relation");
		String student_housecard = request.getParameter("student_housecard");
		String student_houseprecard = request.getParameter("student_houseprecard");
		String student_budingid = request.getParameter("student_budingid");
		String student_housenum = request.getParameter("student_housenum");
		String student_school = request.getParameter("student_school");
		String student_tele = request.getParameter("student_tele");
		String student_preschool = request.getParameter("student_preschool");
		String student_roomid = request.getParameter("student_roomid");
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		StudentDAO sd = new StudentDAOImpl();
		Student stu = new Student();
		stu.setStudentName(student_name);
		stu.setStudentIdCard(student_idcard);
		stu.setStudentFather(student_father);
		stu.setStudentMother(student_mother);
		stu.setStudentHouseOwner(student_houseowner);
		stu.setStudentOwnerId(student_ownerid);
		stu.setStudentRelation(student_relation);
		stu.setStudentHouseCard(student_housecard);
		stu.setStudentHousePreCard(student_houseprecard);
		stu.setStudentBuildingId(student_budingid);
		stu.setStudentHouseNumber(student_housenum);
		stu.setStudentCreatTime(CreatTime);
		stu.setStudentSchool(student_tele);
		stu.setStudentPhone(student_preschool);
		stu.setStudentRoomId(student_roomid);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = sd.add(stu);
		
		if(flag) {
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
			}
		else {
			out.println("Fail");
			return ERROR;
			}
	}
	
	
	public String UpdateArea() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String student_id = request.getParameter("student_id");
		String student_name = request.getParameter("student_name");
		String student_idcard = request.getParameter("student_idcard");
		String student_father = request.getParameter("student_father");
		String student_mother = request.getParameter("student_mother");
		String student_houseowner = request.getParameter("student_houseowner");
		String student_ownerid = request.getParameter("student_ownerid");
		String student_relation = request.getParameter("student_relation");
		String student_housecard = request.getParameter("student_housecard");
		String student_houseprecard = request.getParameter("student_houseprecard");
		String student_budingid = request.getParameter("student_budingid");
		String student_housenum = request.getParameter("student_housenum");
		String student_school = request.getParameter("student_school");
		String student_tele = request.getParameter("student_tele");
		String student_preschool = request.getParameter("student_preschool");
		String student_roomid = request.getParameter("student_roomid");

		int s_id = Integer.valueOf(student_id);
		
		StudentDAO sd = new StudentDAOImpl();
		Student stu = sd.query(s_id);
		
		stu.setStudentName(student_name);
		stu.setStudentIdCard(student_idcard);
		stu.setStudentFather(student_father);
		stu.setStudentMother(student_mother);
		stu.setStudentHouseOwner(student_houseowner);
		stu.setStudentOwnerId(student_ownerid);
		stu.setStudentRelation(student_relation);
		stu.setStudentHouseCard(student_housecard);
		stu.setStudentHousePreCard(student_houseprecard);
		stu.setStudentBuildingId(student_budingid);
		stu.setStudentHouseNumber(student_housenum);
		stu.setStudentSchool(student_tele);
		stu.setStudentPhone(student_preschool);
		stu.setStudentRoomId(student_roomid);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = sd.update(stu);
		
		if(flag) {
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
			}
		else {
			out.println("Fail");
			return ERROR;
			}
	}
	
	
	public String DeleteArea() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id = request.getParameter("area_id");
		
		int a_id = Integer.valueOf(area_id);
		
		
		StudentDAO ad = new StudentDAOImpl();
		Student stu = ad.query(a_id);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.delete(stu);
		
		if(flag) {
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
			}
		else {
			out.println("Fail");
			return ERROR;
			}
	}
}
