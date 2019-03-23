package com.csu.dao;

import java.util.List;

import com.csu.model.User;

public interface UserDAO {
	
	public boolean add(User user);
	
	public boolean update(User user);
	
	public boolean delete(User user);
	
	public User query(int id);
	
	public List<User> getAllUser();
	
	public List<User> QueryByAccount(String user_account);
	
	public List<User> QueryByFlag(int user_flag);
	
	public List<User> getAllUserByPageSize(int i,int PageSize);
	
	
	public List<User> QueryByFlagByPageSize(int user_flag,int i,int PageSize);
}
