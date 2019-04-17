package com.tongansz.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tongansz.dao.BuildingDAO;
import com.tongansz.model.Building;
import com.tongansz.utils.HibernateUtil;

public class BuildingDAOImpl implements BuildingDAO{

	@Override
	public boolean add(Building building) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.save(building);
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
	public boolean update(Building building) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.update(building);
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
	public List<Building> getAllBuilding() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<Building> list = null;
		
		try {
			session.beginTransaction();
			String hql = "from Building";
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
	public boolean delete(Building building) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(building);
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
	public Building query(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Building result = null;
		
		try {
			session.beginTransaction();
			result = session.get(Building.class,id);
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
	public List<Building> getAllBuildingByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Building> list = null;
		try {
			session.beginTransaction();
			String hql = "from Building";
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
	public List<Building> getBuildingBySchoolId(int SchoolId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Building> list = null;
		try {
			session.beginTransaction();
			String hql = "from Building where school_id = :SchoolId";
			Query query = session.createQuery(hql);
			query.setParameter("SchoolId", SchoolId);
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
	public List<Building> getBuildingBySchoolByName(String building_name) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Building> list = null;
		try {
			session.beginTransaction();
			String hql = "from Building where building_name = :building_name";
			Query query = session.createQuery(hql);
			query.setParameter("building_name", building_name);
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
	public List<Building> getBuildingByAreaId(int area_id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Building> list = null;
		try {
			session.beginTransaction();
			String hql = "from Building where area_id = :area_id";
			Query query = session.createQuery(hql);
			query.setParameter("area_id", area_id);
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
