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
	public void GetStudentByPlace() {
		String place = "招商南山郦景";
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.VagueByPlace(place);
		System.out.println(list.size());
	}
	
	@Test
	public void GetStudentByPlaceAndStudent() {
		String place = "招商南山郦景";
		String school = "南山外国语学校";
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.VagueByPlaceAndSchool(place, school);
		System.out.println(list.size());
	}
	
}
