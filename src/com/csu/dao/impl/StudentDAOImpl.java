package com.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.csu.dao.StudentDAO;
import com.csu.model.Student;
import com.csu.utils.HibernateUtil;

public class StudentDAOImpl implements StudentDAO{

	@Override
	public boolean add(Student student) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		session.close();
		return true;
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		session.close();
		return true;
	}

	@Override
	public boolean delete(Student student) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(student);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		session.close();
		return true;
	}

	@Override
	public Student query(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Student result = null;
		
		try {
			session.beginTransaction();
			result = session.get(Student.class,id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			return result;
		}
		session.close();
		return result;
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<Student> list = null;
		
		try {
			session.beginTransaction();
			String hql = "from Student";
			Query query = session.createQuery(hql);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return list;
		}
		session.close();
		return list;
	}

	@Override
	public List<Student> getAllStudentByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student";
			Query query = session.createQuery(hql);
			query.setFirstResult(PageSize*(i-1));
			query.setMaxResults(PageSize);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return list;
		}
		// TODO Auto-generated method stub
		session.close();
		return list;
	}

}
