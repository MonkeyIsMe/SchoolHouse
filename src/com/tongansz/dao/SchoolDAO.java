package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.School;

public interface SchoolDAO {
	
	public boolean add(School school);
	
	public boolean delete(School school);
	
	public boolean update(School school);
	
	public School query(int id);
	
	public List<School> getAllSchool();
	
	public List<School> getAllSchoolByPageSize(int i,int PageSize);
	
	public List<School> getSchoolByName(String school_name);
	
	public List<School> getSchoolByArea(int area_id);
}
