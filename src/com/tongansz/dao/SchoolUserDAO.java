package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.SchoolUser;

public interface SchoolUserDAO {
	
	public boolean add(SchoolUser user);
	
	public boolean update(SchoolUser user);
	
	public boolean delete(SchoolUser user);
	
	public SchoolUser query(int id);
	
	public List<SchoolUser> getAllUser();
	
	public List<SchoolUser> QueryByAccount(String user_account);
	
	public List<SchoolUser> QueryByFlag(int user_flag);
	
	public List<SchoolUser> getAllUserByPageSize(int i,int PageSize);
	
	public List<SchoolUser> getAllUserBySchoolId(int school_id);
	
	public List<SchoolUser> QueryByFlagByPageSize(int user_flag,int i,int PageSize);
}
