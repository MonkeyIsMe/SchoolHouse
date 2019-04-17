package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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



public class ReadExcelAction extends ActionSupport{
	
	public String AddStudentFromExcel() throws IOException, ParseException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String StudentInfo = request.getParameter("student_info");
		String SchoolName = request.getParameter("school_name");
		String area_id = request.getParameter("area_id");
		JSONArray ja = JSONArray.parseArray(StudentInfo);
		JSONArray add_ja = new JSONArray();
		
		BuildingDAO bd = new BuildingDAOImpl();
		int aid = Integer.valueOf(area_id);
		
		int schoolcnt = 0;
		int buildingcnt = 0;
		int rcnt = 0;
		int hcnt = 0;
		
		for(int i = 0 ;i < ja.size() ; i++) {
			
			JSONObject jo = ja.getJSONObject(i);
			String student_name = jo.getString("学生姓名");
			String student_idcard = jo.getString("学生身份证号码");
			String student_father = jo.getString("父亲姓名");
			String student_mother = jo.getString("母亲姓名");
			String student_houseowner = jo.getString("业主姓名");
			String student_ownerid = jo.getString("业主身份证");
			String student_relation = jo.getString("与业主关系");
			String student_housecard = jo.getString("房产证号");
			String student_houseprecard = jo.getString("房屋预告登记号");
			String student_budingid = jo.getString("所属楼盘");
			String student_housenum = jo.getString("楼号");
			String student_school = jo.getString("所属学校");
			String student_tele = jo.getString("学校联系电话");
			String student_preschool = jo.getString("毕业小学");
			String student_roomid = jo.getString("房号");
			String usetime = jo.getString("使用的日期");
			
			if(student_name == "" || student_name == null) continue;
			
			HouseDAO hd = new HouseDAOImpl();
			
			List<Building> blist = bd.getBuildingByAreaId(aid);
			
			boolean Sflag = false;  //判定重复
			boolean Bflag = false;  //判断楼盘
			boolean Rflag = false; //判定房屋
			
			DealDate dd = new DealDate();
			String CreatTime = dd.getDate();
			
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
			stu.setStudentRoomId(student_roomid);
			stu.setStudentUseTime(usetime);
			
			//判断学校是否合法
			if(SchoolName.equals(student_school)) {
				
				int bid = 0;
				//判断楼盘和房屋是否合法
				for(Building building : blist) {
					String bname = building.getBuildingName();
					if(bname.equals(student_budingid)) {
						bid = building.getBuildingId();
						Bflag = true;

					}
				}
				
				if(Bflag == false) {
					stu.setStudentReason("楼盘不匹配");
					String jsonString = JSONObject.toJSONString(stu);
					JSONObject add_jo = JSONObject.parseObject(jsonString);
					add_ja.add(add_jo);
					buildingcnt ++;
				}
				
				//判断信息是否重复
				if(Bflag) {
					StudentDAO sd = new StudentDAOImpl();
					List<Student> repeat_stu = sd.StudentByInfoName(student_name,student_budingid, student_housenum, student_roomid, student_ownerid);

					if(repeat_stu.size() == 0 ) {
						Sflag = true;
					}
					else {
/*						stu.setStudentReason("信息重复");
						String jsonString = JSONObject.toJSONString(stu);
						JSONObject add_jo = JSONObject.parseObject(jsonString);
						add_ja.add(add_jo);*/
						rcnt++;
					}
				}
				
				if(Bflag&&Sflag) {
					List<House> hlist = hd.getHouseByBuilding(bid);
					for(House house : hlist) {
						String hname = house.getHouseName();
						String rname = house.getHouseRoom();
						if(hname.equals(student_housenum)&&rname.equals(student_roomid)) Rflag = true;
					}
					
					if(Rflag == false) {
						hcnt++;
						stu.setStudentReason("房屋信息不匹配");
						String jsonString = JSONObject.toJSONString(stu);
						JSONObject add_jo = JSONObject.parseObject(jsonString);
						add_ja.add(add_jo);
					}
				}
				

				//判断是不是六年内
				if(Bflag&&Sflag&&Rflag) {

					SchoolRule sr = new SchoolRule();
					boolean valid = sr.IsValid(student_name,student_budingid, student_housenum, student_roomid,usetime,student_housecard,student_relation,student_ownerid,student_father,student_mother);
					if(valid) stu.setStudentValid(1);
					else {
						stu.setStudentValid(0);
						stu.setStudentReason("未满足六年");
					}
					String jsonString = JSONObject.toJSONString(stu);
					JSONObject add_jo = JSONObject.parseObject(jsonString);
					add_ja.add(add_jo);
				}
				
			}
			else {
				schoolcnt++;
				stu.setStudentReason("学校不匹配");
				String jsonString = JSONObject.toJSONString(stu);
				JSONObject add_jo = JSONObject.parseObject(jsonString);
				add_ja.add(add_jo);
			}
		}
		StudentDAO sd = new StudentDAOImpl();
		TempStudentDAO tsd = new TempStudentDAOImpl();
		List<TempStudent> list = JSONArray.parseArray(add_ja.toJSONString(),TempStudent.class);
		for(TempStudent stu : list) {
			
			int flag = stu.getStudentValid();
			if(flag == 1) {
				tsd.add(stu);
				Student student = new Student();
				student.setStudentName(stu.getStudentName());
				student.setStudentIdCard(stu.getStudentIdCard());
				student.setStudentFather(stu.getStudentFather());
				student.setStudentMother(stu.getStudentMother());
				student.setStudentHouseOwner(stu.getStudentHouseOwner());
				student.setStudentOwnerId(stu.getStudentOwnerId());
				student.setStudentRelation(stu.getStudentRelation());
				student.setStudentHouseCard(stu.getStudentHouseCard());
				student.setStudentHousePreCard(stu.getStudentHousePreCard());
				student.setStudentBuildingId(stu.getStudentBuildingId());
				student.setStudentHouseNumber(stu.getStudentHouseNumber());
				student.setStudentCreatTime(stu.getStudentCreatTime());
				student.setStudentSchool(stu.getStudentSchool());
				student.setStudentPhone(stu.getStudentPhone());
				student.setStudentPreSchool(stu.getStudentHousePreCard());
				student.setStudentRoomId(stu.getStudentRoomId());
				student.setStudentUseTime(stu.getStudentUseTime());
				student.setStudentValid(1);
				sd.add(student);
			}
			else tsd.add(stu);
		}
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		
		JSONObject jo = new JSONObject();
		jo.put("all",ja.size());
		jo.put("school",schoolcnt);
		jo.put("building",buildingcnt);
		jo.put("repeat",rcnt);
		jo.put("house",hcnt);
		
		out.println(jo);
        out.flush(); 
        out.close();
		
		return null;
	}
	
	
	public String AddStudentFromExcelValid() throws IOException, ParseException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String StudentInfo = request.getParameter("student_info");
		String SchoolName = request.getParameter("school_name");
		String area_id = request.getParameter("area_id");
		//System.out.println(StudentInfo);
		JSONArray ja = JSONArray.parseArray(StudentInfo);
		
		BuildingDAO bd = new BuildingDAOImpl();
		int aid = Integer.valueOf(area_id);
		JSONArray add_ja = new JSONArray();
		
		int cnt = 0;
		int bcnt = 0;
		int rcnt = 0;
		
		String binfo = "";
		String sinfo = "";
		String rinfo = "";
		
		for(int i = 0 ;i < ja.size() ; i++) {
			
			JSONObject jo = ja.getJSONObject(i);
			String student_name = jo.getString("学生姓名");
			String student_idcard = jo.getString("学生身份证号码");
			String student_father = jo.getString("父亲姓名");
			String student_mother = jo.getString("母亲姓名");
			String student_houseowner = jo.getString("业主姓名");
			String student_ownerid = jo.getString("业主身份证");
			String student_relation = jo.getString("与业主关系");
			String student_housecard = jo.getString("房产证号");
			String student_houseprecard = jo.getString("房屋预告登记号");
			String student_budingid = jo.getString("所属楼盘");
			String student_housenum = jo.getString("楼号");
			String student_school = jo.getString("所属学校");
			String student_tele = jo.getString("学校联系电话");
			String student_preschool = jo.getString("毕业小学");
			String student_roomid = jo.getString("房号");
			String usetime = jo.getString("使用的日期");
			if(student_name == "" || student_name == null) continue;
			HouseDAO hd = new HouseDAOImpl();
			List<Building> blist = bd.getBuildingByAreaId(aid);
			
			boolean Bflag = false;
			boolean Sflag = false;
			
			int bid = 0;
			

			
			if(SchoolName.equals(student_school)) {
				
				
				for(Building building :blist) {
					String bname = building.getBuildingName();
					if(bname.equals(student_budingid)) {
						Bflag = true;
						bid = building.getBuildingId();
						break;
					}
				}
				
				if(Bflag == false) {
					binfo = binfo + student_name;
					binfo = binfo + " ,";
					bcnt ++;
				}
				

				

				
				//判断信息是否重复
				if(Bflag) {
					StudentDAO sd = new StudentDAOImpl();
					List<Student> repeat_stu = sd.StudentByInfoName(student_name,student_budingid, student_housenum, student_roomid, student_ownerid);

					if(repeat_stu.size() == 0 ) {
						Sflag = true;
					}
					else {
						rcnt ++;
						rinfo = rinfo + student_name;
						rinfo = rinfo + " ,";
					}
				}
				
				DealDate dd = new DealDate();
				String CreatTime = dd.getDate();
				StudentDAO sd = new StudentDAOImpl();
				Student stu = new Student();
				
				if(Bflag&&Sflag) {
					String sb = String.valueOf(bid);
					List<House> hlist = hd.getHouseByName(sb,student_housenum,student_roomid);
					if(hlist.size() == 0 ) {
						//System.out.println(student_budingid + " " +student_housenum + " " + student_roomid);
						Building building = bd.query(bid);
						System.out.println(student_budingid + " " + student_housenum + " " + student_roomid);
						String bname = building.getBuildingName();
						
						House house = new House();
						house.setBuildingId(bid);
						house.setBuildingName(bname);
						house.setHouseName(student_housenum);
						house.setHouseRoom(student_roomid);
						String date = dd.getDate();
						house.setHouseCreatTime(date);
						hd.add(house);
					}
					
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
					stu.setStudentRoomId(student_roomid);
					stu.setStudentUseTime(usetime);
					stu.setStudentValid(1);
					
					String jsonString = JSONObject.toJSONString(stu);
					JSONObject add_jo = JSONObject.parseObject(jsonString);
					add_ja.add(add_jo);
				}

			}
			else {
				sinfo = sinfo + student_name;
				sinfo = sinfo + " ,";
				cnt++;
			}
			
			//System.out.println(student_housenum);
			
			
		}
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = JSONArray.parseArray(add_ja.toJSONString(),Student.class);
		//System.out.println("size = " + list.size());
		for(Student stu : list) {
			sd.add(stu);
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		JSONObject jo = new JSONObject();
		System.out.println("cnt =" + cnt);
		jo.put("all",ja.size());
		jo.put("school",cnt);
		jo.put("building",bcnt);
		jo.put("repeat", rcnt);
		jo.put("sinfo", sinfo);
		jo.put("binfo", binfo);
		jo.put("rinfo", rinfo);
		
		out.println(jo);
        out.flush(); 
        out.close();
        return null;
	}
}
