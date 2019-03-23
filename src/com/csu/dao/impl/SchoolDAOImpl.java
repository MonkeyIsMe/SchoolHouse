package com.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.csu.dao.SchoolDAO;
import com.csu.model.School;
import com.csu.utils.HibernateUtil;

public class SchoolDAOImpl implements SchoolDAO{

	@Override
	public boolean add(School school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.save(school);
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
	public boolean delete(School school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(school);
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
	public boolean update(School school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.update(school);
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
	public School query(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		School result = null;
		
		try {
			session.beginTransaction();
			result = session.get(School.class,id);
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
	public List<School> getAllSchool() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<School> list = null;
		
		try {
			session.beginTransaction();
			String hql = "from School";
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
	public List<School> getAllSchoolByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<School> list = null;
		try {
			session.beginTransaction();
			String hql = "from School";
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
