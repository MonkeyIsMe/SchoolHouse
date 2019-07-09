package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.Student;

public interface StudentDAO {
	
	public boolean add(Student student);
	
	public boolean update(Student student);
	
	public boolean delete(Student student);
	
	public Student query(int id);
	
	public List<Student> getAllStudent();
	
	public List<Student> getAllStudentByPageSize(int i,int PageSize);
	
	public List<Student> getStudentBySchool(String StudentSchool);
	
	public List<Student> getStudentBySchoolPageSize(String StudentSchool,int i,int PageSize);
	
	public List<Student> StudentValid(String student_building,String student_housenum);
	
	public List<Student> StudentByInfo(String student_building,String student_housenum,String student_roomid,String student_ownerid);
	
	public List<Student> StudentByInfoName(String student_name,String student_building,String student_housenum,String student_ownerid,String student_school);
	
	public void SaveListStudent(List<Student> student);
	
	public List<Student> VagueByPlace(String student_housenum);
	
	public List<Student> VagueByPlaceAndSchool(String student_housenum,String student_school);
	
}
