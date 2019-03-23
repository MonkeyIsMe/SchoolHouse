package com.csu.dao;

import java.util.List;

import com.csu.model.Student;

public interface StudentDAO {
	
	public boolean add(Student student);
	
	public boolean update(Student student);
	
	public boolean delete(Student student);
	
	public Student query(int id);
	
	public List<Student> getAllStudent();
	
	public List<Student> getAllStudentByPageSize(int i,int PageSize);
	
}
