package com.csu.dao;

import java.util.List;

import com.csu.model.School;

public interface SchoolDAO {
	
	public boolean add(School school);
	
	public boolean delete(School school);
	
	public boolean update(School school);
	
	public School query(int id);
	
	public List<School> getAllSchool();
	
	public List<School> getAllSchoolByPageSize(int i,int PageSize);
}
