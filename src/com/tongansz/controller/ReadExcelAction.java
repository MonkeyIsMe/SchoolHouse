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
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.TempStudentDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.dao.impl.TempStudentDAOImpl;
import com.tongansz.model.Area;
import com.tongansz.model.Building;
import com.tongansz.model.House;
import com.tongansz.model.School;
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
		
		DealDate dd = new DealDate();
		TempStudentDAO tsd = new TempStudentDAOImpl();
		
		boolean BuildingEmptyFlag = true;
		
		int schoolcnt = 0;
		int buildingcnt = 0;
		int rcnt = 0;
		int hcnt = 0;
		
		for(int i = 0 ;i < ja.size() ; i++) {
			
			JSONObject jo = ja.getJSONObject(i);
			String student_name = jo.getString("学生姓名");
			String student_idcard = jo.getString("身份证号码");
			String student_father = jo.getString("父亲姓名");
			String student_mother = jo.getString("母亲姓名");
			
			String student_houseowner = jo.getString("业主姓名");
			String student_relation = jo.getString("学生与业主关系");
			String student_housecard = jo.getString("不动产证号或房产证号");
			String student_houseprecard = jo.getString("房屋预告登记号");
			
			String student_housenum = jo.getString("房产详细地址");
			String student_tele = jo.getString("学生联系电话");
			String student_preschool = jo.getString("毕业小学");
			//String student_roomid = jo.getString("房号");
			String usetime = jo.getString("学位使用年月");
			if(student_name == "" || student_name == null) continue;
			String ut = "";
			
			if(usetime == "" || usetime == null) {
				usetime = jo.getString("使用的日期");
			}
			
			if(student_relation == "" || student_relation == null) {
				student_relation = jo.getString("与业主关系");
			}
			
			if(student_idcard == "" || student_idcard == null) {
				student_idcard = jo.getString("学生身份证号码");
			}
			
			if(student_housecard == "" || student_housecard == null) {
				student_housecard = jo.getString("房产证号");
			}
			
			if(student_housenum == "" || student_housenum == null) {
				student_housenum = jo.getString("地址");
			}
			
			if(usetime == "" || usetime == null) continue;
			
			boolean DateFlag = dd.JudgeDate(usetime);
			if(DateFlag == false) {
				ut = dd.GetTrueDate(usetime);
			}
			else ut = usetime;
			String student_school;
			student_school = jo.getString("所属学校");
			if(student_school == null || student_school =="") student_school = jo.getString("就读学校");
			//System.out.println(student_school + " " + SchoolName);
			if(student_school.equals("undefined")) student_school = SchoolName;
			String student_ownerid;
			student_ownerid = jo.getString("业主身份证号");
			if(student_ownerid == null || student_ownerid =="") student_ownerid = jo.getString("业主身份证号码");
			if(student_ownerid == null || student_ownerid =="") student_ownerid = jo.getString("业主身份证");
			
			String student_budingid;
			student_budingid = jo.getString("所属楼盘");
			if(student_budingid == null || student_budingid =="") student_budingid = jo.getString("楼盘名称");
			
			if(student_name == "" || student_name == null) continue;
			if(student_budingid == null || student_budingid == "") BuildingEmptyFlag = false;
			
			
			
			HouseDAO hd = new HouseDAOImpl();
			
			List<Building> blist = bd.getBuildingByAreaId(aid);
			
			boolean Sflag = false;  //判定重复
			boolean Bflag = false;  //判断楼盘
			boolean Rflag = false; //判定房屋
			
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
			stu.setStudentUseTime(ut);
			
			
			//判断学校是否合法
			if(SchoolName.equals(student_school)) {
				
				int bid = 0;
				//判断楼盘和房屋是否合法
				for(Building building : blist) {
/*					String bname = building.getBuildingName();
					if(bname.equals(student_budingid)) {
						bid = building.getBuildingId();
						Bflag = true;
					}*/
					bid = building.getBuildingId();
					List<House> HouseList = hd.getHouseByBuilding(bid);
					for(House house : HouseList) {
						String HouseName = house.getHouseName();
						if(HouseName.equals(student_housenum)) {
							Bflag = true;
						}
					}
				}
				
				if(Bflag == false) {
					stu.setStudentReason("地址不匹配");
					stu.setStudentValid(-1);
					String jsonString = JSONObject.toJSONString(stu);
					JSONObject add_jo = JSONObject.parseObject(jsonString);
					add_ja.add(add_jo);
					buildingcnt ++;
				}
				
				//判断信息是否重复
				if(Bflag) {
					StudentDAO sd = new StudentDAOImpl();
					List<Student> repeat_stu = sd.StudentByInfoName(student_name,student_budingid, student_housenum, student_ownerid,student_school);

					if(repeat_stu.size() == 0) {
						Sflag = true;
					}
					else {
						rcnt++;
						stu.setStudentReason("信息重复");
						stu.setStudentValid(-1);
						String jsonString = JSONObject.toJSONString(stu);
						JSONObject add_jo = JSONObject.parseObject(jsonString);
						add_ja.add(add_jo);
					}
				}
				
				List<TempStudent> repeat_temp = tsd.StudentByInfoName(student_name,student_budingid, student_housenum, student_ownerid,student_school);
				if(repeat_temp.size() != 0) continue;
				
				//判断是不是六年内
				if(Bflag&&Sflag) {
					SchoolRule sr = new SchoolRule();
					boolean valid = sr.IsValid(student_name,student_budingid, student_housenum, usetime,student_housecard,student_relation,student_ownerid,student_father,student_mother);
					if(valid) stu.setStudentValid(0);
					else {
						stu.setStudentValid(-1);
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
				stu.setStudentValid(-1);
				String jsonString = JSONObject.toJSONString(stu);
				JSONObject add_jo = JSONObject.parseObject(jsonString);
				add_ja.add(add_jo);
			}
		}
		
		List<TempStudent> list = JSONArray.parseArray(add_ja.toJSONString(),TempStudent.class);
		
		tsd.SaveListTempStudent(list);
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		
		JSONObject jo = new JSONObject();
		jo.put("all",ja.size());
		jo.put("school",schoolcnt);
		jo.put("building",buildingcnt);
		jo.put("repeat",rcnt);
		
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
		
		JSONArray ja = JSONArray.parseArray(StudentInfo);
		//System.out.println(StudentInfo);
		SchoolDAO scd = new SchoolDAOImpl();
		List<School> sch_list = scd.getSchoolByName(SchoolName);
		School sch = sch_list.get(0);
		
		BuildingDAO bd = new BuildingDAOImpl();
		int aid = Integer.valueOf(area_id);
		JSONArray add_ja = new JSONArray();
		
		int cnt = 0;
		int bcnt = 0;
		int rcnt = 0;
		
		//String binfo = "";
		String sinfo = "";
		String rinfo = "";
		
		
		
		DealDate dd = new DealDate();
		
		for(int i = 0 ;i < ja.size() ; i++) {
			
			boolean BuildingEmptyFlag = true;
			
			JSONObject jo = ja.getJSONObject(i);
			String student_name = jo.getString("学生姓名");
			String student_idcard = jo.getString("身份证号码");
			String student_father = jo.getString("父亲姓名");
			String student_mother = jo.getString("母亲姓名");
			
			String student_houseowner = jo.getString("业主姓名");
			String student_relation = jo.getString("学生与业主关系");
			String student_housecard = jo.getString("不动产证号或房产证号");
			String student_houseprecard = jo.getString("房屋预告登记号");
			
			String student_housenum = jo.getString("房产详细地址");
			String student_tele = jo.getString("学生联系电话");
			String student_preschool = jo.getString("毕业小学");
			//String student_roomid = jo.getString("房号");
			String usetime = jo.getString("学位使用年月");
			//System.out.println(student_name);
			String ut = "";
			boolean DateFlag = dd.JudgeDate(usetime);
			if(DateFlag == false) {
				ut = dd.GetTrueDate(usetime);
			}
			else ut = usetime;
			
			String student_school;
			student_school = jo.getString("所属学校");
			if(student_school == null || student_school =="") student_school = jo.getString("就读学校");
			
			String student_ownerid;
			student_ownerid = jo.getString("业主身份证号");
			if(student_ownerid == null || student_ownerid =="") student_ownerid = jo.getString("业主身份证号码");
			String student_budingid;
			student_budingid = jo.getString("所属楼盘");
			if(student_budingid == null || student_budingid =="") student_budingid = jo.getString("楼盘名称");
			
			if(student_name == "" || student_name == null) continue;
			//System.out.println("building " + student_budingid);
			if(student_budingid == null ) BuildingEmptyFlag = false;
			HouseDAO hd = new HouseDAOImpl();
			List<Building> blist = bd.getBuildingByAreaId(aid);
			
			boolean Bflag = false;
			boolean Sflag = false;
			
			int bid = 0;
			
			//System.out.println(SchoolName + " " + student_school);
			
			if(SchoolName.equals(student_school)) {
				for(Building building :blist) {
					String bname = building.getBuildingName();
					//System.out.println(bname + " " + student_budingid + " " + BuildingEmptyFlag + " " + student_name);
						if(bname.equals(student_budingid)) {
							Bflag = true;
							bid = building.getBuildingId();
							break;
						}

				}
				
				if(Bflag == false) {
					if(BuildingEmptyFlag == true) {
						Building building = new Building();
						building.setBuildingName(student_budingid);
						building.setBuildingCreatTime(dd.getDate());
						building.setAreaId(aid);
						building.setSchoolName(student_school);
						building.setSchoolId(sch.getSchoolId());
						bd.add(building);
					}
				}
				
				
				//判断信息是否重复
				StudentDAO sd = new StudentDAOImpl();
				List<Student> repeat_stu = sd.StudentByInfoName(student_name,student_budingid, student_housenum, student_ownerid,student_school);

				if(repeat_stu.size() == 0 ) {
					Sflag = true;
				}
				else {
					rcnt ++;
					rinfo = rinfo + student_name;
					rinfo = rinfo + " ,";
				}
				
				String CreatTime = dd.getDate();
				Student stu = new Student();
				
				if(Sflag) {
					String sb = String.valueOf(bid);
					List<House> hlist = hd.getHouseByName(sb,student_housenum);
					if(hlist.size() == 0 ) {
						
						if(BuildingEmptyFlag == false) {
							House house = new House();
							house.setHouseName(student_housenum);
							//house.setHouseRoom(student_roomid);
							String date = dd.getDate();
							house.setHouseCreatTime(CreatTime);
							hd.add(house);
						}
						else {
							List<Building> buildinglist = bd.getBuildingBySchoolByName(student_budingid);
							Building building = buildinglist.get(0);
							int bbid = building.getBuildingId();
							String bname = building.getBuildingName();
							House house = new House();
							house.setBuildingId(bbid);
							house.setBuildingName(bname);
							house.setHouseName(student_housenum);
							//house.setHouseRoom(student_roomid);
							String date = dd.getDate();
							house.setHouseCreatTime(CreatTime);
							hd.add(house);
						}
						

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
					//stu.setStudentRoomId(student_roomid);
					stu.setStudentUseTime(ut);
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
			
			
		}
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = JSONArray.parseArray(add_ja.toJSONString(),Student.class);
		//System.out.println("size = " + list.size());
		sd.SaveListStudent(list);
/*		for(Student stu : list) {
			sd.add(stu);
		}*/
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		JSONObject jo = new JSONObject();
		jo.put("all",ja.size());
		jo.put("school",cnt);
		jo.put("repeat", rcnt);
		jo.put("sinfo", sinfo);
		jo.put("rinfo", rinfo);
		
		out.println(jo);
        out.flush(); 
        out.close();
        return null;
	}


	public String AddAreaFromExcel() throws Exception{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String AreaInfo = request.getParameter("area_info");
		JSONArray ja = JSONArray.parseArray(AreaInfo);
		
		AreaDAO ad = new AreaDAOImpl();
		DealDate dd = new DealDate();
		
		int success = 0;
		int fail = 0;
		//System.out.println(AreaInfo);
		for(int i = 0 ;i< ja.size() ; i++) {
			
			boolean flag = false;
			
			JSONObject jo = ja.getJSONObject(i);
			String area_name = jo.getString("学区名称");
			//System.out.println(jo.toJSONString());
			//System.out.println(area_name);
			List<Area> list = ad.GetByName(area_name);
			if(list.size() == 0) {
				Area area = new Area();
				area.setAreaName(area_name);
				area.setAreaCreatTime(dd.getDate());
				ad.add(area);
				success++;
			}
			else fail++;
			
			
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		JSONObject jos = new JSONObject();
		jos.put("success",success);
		jos.put("fail",fail);
		
		out.println(jos);
        out.flush(); 
        out.close();
        return null;
	}
	
	public String AddSchoolFromExcel() throws Exception{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String SchoolInfo = request.getParameter("school_info");
		JSONArray ja = JSONArray.parseArray(SchoolInfo);
		
		SchoolDAO sd = new SchoolDAOImpl();
		DealDate dd = new DealDate();
		AreaDAO ad = new AreaDAOImpl();
		
		int success = 0;
		int fail = 0;
		//System.out.println(AreaInfo);
		for(int i = 0 ;i< ja.size() ; i++) {
			
			boolean flag = false;
			
			JSONObject jo = ja.getJSONObject(i);
			String school_name = jo.getString("学校名称");
			String school_place = jo.getString("学校地址");
			String school_street = jo.getString("所属街道");
			String school_area = jo.getString("所属学区");
			String school_legel = jo.getString("法人代表");
			String school_phone = jo.getString("联系电话");
			
			School school = new School();
			
			List<Area> list = ad.GetByName(school_area);
			if(list.size() == 0) {
				fail ++;
			}
			else {
				
				List<School> slist = sd.getSchoolByName(school_name);
				if(slist.size() == 0) {
					
					Area area = list.get(0);
					int aid = area.getAreaId();
					school.setAreaId(aid);
					school.setSchoolCreatTime(dd.getDate());
					school.setAreaName(school_area);
					school.setSchoolIegalPerson(school_legel);
					school.setSchoolName(school_name);
					school.setSchoolPhone(school_phone);
					school.setSchoolStreet(school_street);
					school.setSchoolAddress(school_place);
					success ++;
					sd.add(school);
				}
				else {
					fail ++;
				}
				
				
			}

			
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		JSONObject jos = new JSONObject();
		jos.put("success",success);
		jos.put("fail",fail);
		
		out.println(jos);
        out.flush(); 
        out.close();
        return null;
	}
}
