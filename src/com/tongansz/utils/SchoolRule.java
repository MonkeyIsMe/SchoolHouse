package com.tongansz.utils;

import java.text.ParseException;
import java.util.List;

import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.model.Student;

public class SchoolRule {
	
	public boolean IsValid(String name,String building, String house,String Date,String HouseCard,String relation,String IDCard,String father,String mother) throws ParseException {
		System.out.println("IDCard =" + IDCard );
		boolean flag = false;
		boolean IDflag = false;
		//boolean rflag = false;
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.StudentValid(building, house);
		//System.out.println(list.size());
		if(list.size() == 0) flag = true;
		else {
			
			DealDate dd = new DealDate();
			String before = dd.SixEarly(Date);
			String UseTime = dd.StringDate(before);
			Student stu = list.get(0);
			String Use = stu.getStudentUseTime();
			int valid = UseTime.compareTo(Use);
			if(valid >= 1) flag = true;
			//System.out.println(valid);ddddd
			//System.out.println(HouseCard.equals(stu.getStudentHouseCard()) +" " + IDCard.equals(stu.getStudentOwnerId()));
			if(flag == false) {
				if(stu.getStudentHouseCard() == null || stu.getStudentOwnerId() == null) {
					IDflag = false;
				}
				else if(stu.getStudentHouseCard() == "" || stu.getStudentOwnerId() == "") {
					IDflag = false;
				}
				else if(HouseCard == "" || HouseCard == null) {
					IDflag = false;
				}
				else if(HouseCard.equals(stu.getStudentHouseCard()) && IDCard.equals(stu.getStudentOwnerId())){
					IDflag = true;
				}
			}
			
 		}
		
		if(IDflag) flag = true;
		return flag;
		
	}
	
}
