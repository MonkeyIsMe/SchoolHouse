package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.model.Student;
import com.tongansz.utils.DealDate;
import com.tongansz.utils.SchoolRule;

public class StudentAction extends ActionSupport{

		public String AddStudent() throws IOException, ParseException{
				
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
				String usetime = request.getParameter("usetime");
				
				
				//System.out.println("stu_sch = " + student_school);
				DealDate dd = new DealDate();
				String ut = dd.StringDate(usetime);
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
				stu.setStudentSchool(student_school);
				stu.setStudentPhone(student_tele);
				stu.setStudentPreSchool(student_preschool);
				stu.setStudentUseTime(ut);
				SchoolRule sr = new SchoolRule();
				//boolean valid = sr.IsValid(student_name,student_budingid, student_housenum, student_roomid,usetime,student_housecard,student_ownerid,student_relation);
				stu.setStudentValid(1);
				//else stu.setStudentValid(0);
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
					return null;
					}
			}
		
		public String AddValidStudent() throws IOException{
			
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
			String usetime = request.getParameter("usetime");
			
			
			DealDate dd = new DealDate();
			String CreatTime = dd.getDate();
			
			String ut = dd.StringDate(usetime);
			
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
			stu.setStudentPreSchool(student_preschool);
			stu.setStudentSchool(student_school);
			stu.setStudentPhone(student_tele);
			stu.setStudentValid(1);
			stu.setStudentUseTime(ut);
			
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
				return null;
				}
		}
		
		public String AddInValidStudent() throws IOException{
			
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
			String usetime = request.getParameter("usetime");
			
			
			DealDate dd = new DealDate();
			String ut = dd.StringDate(usetime);
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
			stu.setStudentSchool(student_school);
			stu.setStudentPreSchool(student_preschool);
			stu.setStudentPhone(student_tele);
			stu.setStudentValid(0);
			stu.setStudentUseTime(ut);
			
			
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
				return null;
				}
		}
			
			
			public String UpdateStudent() throws IOException{
				
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
				String usetime = request.getParameter("usetime");
		
				
				int s_id = Integer.valueOf(student_id);
				
				DealDate dd = new DealDate();
				String ut = dd.StringDate(usetime);
				
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
				stu.setStudentSchool(student_school);
				stu.setStudentPreSchool(student_preschool);
				stu.setStudentPhone(student_tele);
				stu.setStudentUseTime(ut);
				
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
					return null;
					}
			}
			
			
			public String DeleteStudent() throws IOException{
				
				ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
				HttpServletRequest request= ServletActionContext.getRequest();
				
				String student_id = request.getParameter("student_id");
				
				int s_id = Integer.valueOf(student_id);
				
				
				StudentDAO ad = new StudentDAOImpl();
				Student stu = ad.query(s_id);
				
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
					return null;
					}
			}
}
