package com.tongansz.utils;

import java.text.ParseException;
import java.util.List;

import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.model.Student;

public class SchoolRule {
	
	public boolean IsValid(String name,String building, String house,String room,String Date,String HouseCard,String relation,String IDCard,String father,String mother) throws ParseException {
		
		boolean flag = false;
		boolean IDflag = false;
		boolean rflag = false;
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.StudentValid(building, house, room);
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
			//System.out.println(valid);
			if(flag == false) {
				if(HouseCard.equals(stu.getStudentHouseCard()) && IDCard.equals(stu.getStudentOwnerId())){
					IDflag = true;
				}
				if(relation.equals("父子")||relation.equals("母女")||relation.equals("父女")||relation.equals("母子")) {
					if(father.equals(stu.getStudentFather())&&mother.equals(stu.getStudentMother())) {
						rflag = true;
					}
					
				}
			}
			
 		}
		
		if(IDflag && rflag) flag = true;
		return flag;
		
	}
	
}
