package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.TempStudent;

public interface TempStudentDAO {
	
	public boolean add(TempStudent student);
	
	public boolean update(TempStudent student);
	
	public boolean delete(TempStudent student);
	
	public TempStudent query(int id);
	
	public List<TempStudent> getAllStudent();
	
	public List<TempStudent> getAllStudentByPageSize(int i,int PageSize);
	
	public List<TempStudent> getStudentBySchool(String StudentSchool);
	
	public List<TempStudent> getStudentBySchoolPageSize(String StudentSchool,int i,int PageSize);
	
	public List<TempStudent> StudentValid(String student_school,String student_building,String student_housenum,String student_roomid);
}
