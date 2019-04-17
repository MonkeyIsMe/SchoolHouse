package com.tongansz.test;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.UserDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.dao.impl.UserDAOImpl;
import com.tongansz.model.Area;
import com.tongansz.model.Building;
import com.tongansz.model.House;
import com.tongansz.model.School;
import com.tongansz.model.Student;
import com.tongansz.model.User;
import com.tongansz.utils.DealDate;
import com.tongansz.utils.HibernateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class TestDAOImpl {

	@Test
	public void QueryUser() {
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.getAllUser();
		for(User user : list) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void AddUser() {
		UserDAO ud = new UserDAOImpl();
		for(int i = 0 ; i<10000 ;i++){
			User user = new User();
			user.setUserAccount("admin");
			ud.add(user);
		}
	}

	
	@Test
	public void QueryAllUserByPageSize() {
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.getAllUserByPageSize(2, 10);
		for(User user : list) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void AreaName() {
		String name = "1";
		AreaDAO ad = new AreaDAOImpl();
		List<Area> list = ad.GetByName(name);
		System.out.println(list.size());
	} 
	
	@Test
	public void MutiplyQuery() {
		String sname = "12312312";
		String house = "1";
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Student> list = null;
		String hql = "from Student where student_school = :sname AND student_housenum = :house order by student_usetime desc";
		Query query = session.createQuery(hql);
		query.setParameter("sname", sname);
		query.setParameter("house", house);
		list = query.list();
		for(Student stu : list) {
			System.out.println(stu.getStudentUseTime());
		}
	} 
	

	@Test
	public void TestDate() throws ParseException {
		String date = "20160501";
		DealDate dd = new DealDate();
		String time = dd.SixEarly(date);
		System.out.println("1." + time);
		String str = dd.StringDate(time);
		System.out.println("2." + dd.SixEarly(date)+" "+str);
	}
	
	@Test
	public void BuildingBySchoolId() {
		int sid = 1;
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getBuildingBySchoolId(sid);
		for(Building building : list) {
			System.out.println(building.toString());
		}
	}
	
	@Test
	public void AddStudentList() {
		
		JSONArray ja = new JSONArray();
		for(int i = 1 ; i<=5 ;i++) {
			Student stu = new Student();
			stu.setStudentName(String.valueOf(i));
			JSONObject jo = JSONObject.fromObject(stu);
			ja.add(jo);
		}
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = JSONArray.toList(ja,Student.class);
		for(Student stu : list) {
			sd.add(stu);
		}
		
	}
	
	@Test
	public void TestReadExcel() {
		String str = "[{\"学生姓名\":\"1\",\"学生身份证号码\":\"2\",\"父亲姓名\":\"3\",\"母亲姓名\":\"4\",\"业主姓名\":\"5\",\"业主身份证\":\"123\",\"与业主关系\":\"6\",\"房产证号\":\"7\",\"房屋预告登记号\":\"8\",\"所属楼盘\":\"9\",\"楼号\":\"10\",\"房号\":\"11\",\"是否有效\":\"undefined\",\"所属学校\":\"12\",\"学生联系电话\":\"13\",\"使用的日期\":\"123\",\"毕业小学\":\"12\",\"创建日期\":\"undefined\"}]";
		com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray().parseArray(str);
		for(int i = 0 ; i<ja.size() ; i++) {
			com.alibaba.fastjson.JSONObject jo = ja.getJSONObject(i);
			String platform = jo.getString("毕业小学");
			System.out.println(platform);
		}
	}
	
	@Test
	public void TestDates() throws ParseException {
		DealDate dd = new DealDate();
		System.out.println("1." + dd.getDate());
		System.out.println("2." + dd.getNowTime());
	}
	
	
	@Test
	public void TestHouseCare() {
		String str = "123";
		String s = "134";
		if(str.equals(s) == false) System.out.println(11111);
	}
	
	@Test
	public void StudentRepeat() {
		String one = "1";
		String tow = "2";
		String three = "3";
		String four = "4";
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list =sd.StudentByInfo(one, tow, three, four);
		System.out.println(list.size());
	}
	
	@Test
	public void SchoolByName() {
		String one = "1";
		SchoolDAO sd = new SchoolDAOImpl();
		List<School> list = sd.getSchoolByName(one);
		System.out.println(list.size());
	}
	
	@Test
	public void getHouseByName() {
		String building = "长塘山小区";
		String house = "4";
		String room = "501";
		HouseDAO hd = new HouseDAOImpl();
		List<House> list = hd.getHouseByName(building, house, room);
		System.out.println(list.size());
	}
	
	
}
