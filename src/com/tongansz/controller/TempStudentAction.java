package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.TempStudentDAO;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.dao.impl.TempStudentDAOImpl;
import com.tongansz.model.Building;
import com.tongansz.model.House;
import com.tongansz.model.Student;
import com.tongansz.model.TempStudent;
import com.tongansz.utils.DealDate;
import com.tongansz.utils.SchoolRule;

public class TempStudentAction extends ActionSupport {

	public String AddTempStudent() throws IOException, ParseException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

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

		// System.out.println("stu_sch = " + student_school);
		DealDate dd = new DealDate();
		String ut = dd.StringDate(usetime);
		String CreatTime = dd.getDate();

		TempStudentDAO sd = new TempStudentDAOImpl();
		TempStudent stu = new TempStudent();
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
		boolean valid = sr.IsValid(student_name, student_budingid, student_housenum, usetime, student_housecard,
				student_ownerid, student_relation, student_father, student_mother);
		if (valid)
			stu.setStudentValid(0);
		else
			stu.setStudentValid(-1);
		boolean flag = false;

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		flag = sd.add(stu);

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String AddValidTempStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

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

		TempStudentDAO sd = new TempStudentDAOImpl();
		TempStudent stu = new TempStudent();
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
		stu.setStudentValid(0);
		stu.setStudentUseTime(ut);

		boolean flag = false;

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		flag = sd.add(stu);

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String AddInValidTempStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

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

		TempStudentDAO sd = new TempStudentDAOImpl();
		TempStudent stu = new TempStudent();
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
		stu.setStudentValid(-1);
		stu.setStudentUseTime(ut);

		boolean flag = false;

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		flag = sd.add(stu);

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String UpdateTempStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

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

		TempStudentDAO sd = new TempStudentDAOImpl();
		TempStudent stu = sd.query(s_id);

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

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		flag = sd.update(stu);

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String DeleteTempStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		String student_id = request.getParameter("student_id");

		int s_id = Integer.valueOf(student_id);

		TempStudentDAO ad = new TempStudentDAOImpl();
		TempStudent stu = ad.query(s_id);

		boolean flag = false;

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		flag = ad.delete(stu);

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String DeleteAllTempStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		TempStudentDAO tad = new TempStudentDAOImpl();
		List<TempStudent> list = tad.getAllStudent();

		boolean flag = false;

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		for (TempStudent ts : list) {
			flag = tad.delete(ts);
		}

		if (flag) {
			out.println("Success");
			out.flush();
			out.close();
			return null;
		} else {
			out.println("Fail");
			return null;
		}
	}

	public String TempToStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		String student_id = request.getParameter("student_id");

		int s_id = Integer.valueOf(student_id);

		TempStudentDAO tsd = new TempStudentDAOImpl();
		TempStudent tstu = tsd.query(s_id);

		tstu.setStudentRoomId("check");
		boolean flag = tsd.update(tstu);

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		if (flag) {
			out.println("Success");
		} else {
			out.println("Fail");
		}

		out.flush();
		out.close();
		return null;

	}

	public String SaveStudent() throws IOException {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		String student_id = request.getParameter("student_id");

		int s_id = Integer.valueOf(student_id);

		TempStudentDAO tsd = new TempStudentDAOImpl();
		TempStudent tstu = tsd.query(s_id);

		String student_name = tstu.getStudentName();
		String student_idcard = tstu.getStudentIdCard();
		String student_father = tstu.getStudentFather();
		String student_mother = tstu.getStudentMother();
		String student_houseowner = tstu.getStudentHouseOwner();
		String student_ownerid = tstu.getStudentOwnerId();
		String student_relation = tstu.getStudentRelation();
		String student_housecard = tstu.getStudentHouseCard();
		String student_houseprecard = tstu.getStudentHousePreCard();
		String student_budingid = tstu.getStudentBuildingId();
		String student_housenum = tstu.getStudentHouseNumber();
		String student_school = tstu.getStudentSchool();
		String student_tele = tstu.getStudentPhone();
		String student_preschool = tstu.getStudentPreSchool();
		String usetime = tstu.getStudentUseTime();

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		HouseDAO hd = new HouseDAOImpl();
		List<House> HouseList = hd.getAllHouse();
		boolean flag = false;
		for(House house :HouseList) {
			String hname = house.getHouseName(); 
			if(hname.equals(student_housenum)) {
				flag = true;
				break;
			}
		}
		
		boolean bflag = false;
		Building building = null;
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> BuildingList = bd.getBuildingBySchoolByName(student_budingid);
		if(BuildingList.size() != 0 ) {
			bflag = true;
			building = BuildingList.get(0);
		}
		DealDate dd = new DealDate();
		if(flag == false) {
			House h = new House();
			if(bflag == true) {
				h.setBuildingId(building.getBuildingId());
				h.setBuildingName(building.getBuildingName());
				h.setHouseName(student_housenum);
				h.setHouseCreatTime(dd.getDate());
				hd.add(h);
			}
			else {
				h.setHouseName(student_housenum);
				h.setHouseCreatTime(dd.getDate());
				hd.add(h);
			}
		}
		
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
		stu.setStudentHousePreCard(student_housecard);
		stu.setStudentBuildingId(student_budingid);
		stu.setStudentHouseNumber(student_housenum);
		stu.setStudentSchool(student_school);
		stu.setStudentPreSchool(student_preschool);
		stu.setStudentPhone(student_tele);
		stu.setStudentUseTime(usetime);
		stu.setStudentValid(1);

		sd.add(stu);
		tsd.delete(tstu);
		out.println("Success");
		out.flush();
		out.close();
		return null;
	}
}
