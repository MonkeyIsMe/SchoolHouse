package com.csu.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.csu.dao.UserDAO;
import com.csu.dao.impl.UserDAOImpl;
import com.csu.model.User;
import com.csu.utils.HibernateUtil;
import com.mysql.jdbc.Connection;



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
	

	
}
