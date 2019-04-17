package com.tongansz.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.model.SchoolUser;
import com.tongansz.utils.HibernateUtil;

public class SchoolUserDAOImpl implements SchoolUserDAO{

	@Override
	public boolean add(SchoolUser user) {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					session.beginTransaction();
					session.save(user);
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
	public boolean update(SchoolUser user) {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					session.beginTransaction();
					session.update(user);
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
	public boolean delete(SchoolUser user) {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				try {
					session.beginTransaction();
					session.delete(user);
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
	public SchoolUser query(int id) {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				SchoolUser result = null;
				
				try {
					session.beginTransaction();
					result = session.get(SchoolUser.class,id);
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
	public List<SchoolUser> getAllUser() {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				
				List<SchoolUser> list = null;
				
				try {
					session.beginTransaction();
					String hql = "from SchoolUser";
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
	public List<SchoolUser> QueryByAccount(String user_account) {
		// TODO Auto-generated method stub
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				List<SchoolUser> list = null;
				try {
					session.beginTransaction();
					String hql = "from SchoolUser where user_account = :user_account";
					Query query = session.createQuery(hql);
					query.setParameter("user_account", user_account);
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

	@Override
	public List<SchoolUser> QueryByFlag(int user_flag) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<SchoolUser> list = null;
		try {
			session.beginTransaction();
			String hql = "from SchoolUser where user_flag = :user_flag";
			Query query = session.createQuery(hql);
			query.setParameter("user_flag", user_flag);
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

	@Override
	public List<SchoolUser> getAllUserByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<SchoolUser> list = null;
		try {
			session.beginTransaction();
			String hql = "from SchoolUser";
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

	@Override
	public List<SchoolUser> QueryByFlagByPageSize(int user_flag, int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<SchoolUser> list = null;
		try {
			session.beginTransaction();
			String hql = "from SchoolUser where user_flag = :user_flag";
			Query query = session.createQuery(hql);
			query.setParameter("user_flag", user_flag);
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

	@Override
	public List<SchoolUser> getAllUserBySchoolId(int school_id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<SchoolUser> list = null;
		try {
			session.beginTransaction();
			String hql = "from SchoolUser where school_id = :school_id";
			Query query = session.createQuery(hql);
			query.setParameter("school_id", school_id);
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
