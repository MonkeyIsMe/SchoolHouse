package com.tongansz.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tongansz.dao.TempStudentDAO;
import com.tongansz.model.Student;
import com.tongansz.model.TempStudent;
import com.tongansz.utils.HibernateUtil;

public class TempStudentDAOImpl implements TempStudentDAO{

	@Override
	public boolean add(TempStudent student) {
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
	public boolean update(TempStudent student) {
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
	public boolean delete(TempStudent student) {
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
	public TempStudent query(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		TempStudent result = null;
		
		try {
			session.beginTransaction();
			result = session.get(TempStudent.class,id);
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
	public List<TempStudent> getAllStudent() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<TempStudent> list = null;
		
		try {
			session.beginTransaction();
			String hql = "from TempStudent";
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
	public List<TempStudent> getAllStudentByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<TempStudent> list = null;
		try {
			session.beginTransaction();
			String hql = "from TempStudent";
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
	public List<TempStudent> getStudentBySchool(String StudentSchool) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<TempStudent> list = null;
		try {
			session.beginTransaction();
			String hql = "from TempStudent where student_school = :StudentSchool";
			Query query = session.createQuery(hql);
			query.setParameter("StudentSchool", StudentSchool);
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
	public List<TempStudent> StudentValid(String student_school, String student_building, String student_housenum,
			String student_roomid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<TempStudent> list = null;
		try {
			session.beginTransaction();
			String hql = "from TempStudent where student_school = :student_school AND student_housenum = :student_housenum AND student_roomid = :student_roomid AND student_budingid = :student_buding order by student_usetime desc";
			Query query = session.createQuery(hql);
			query.setParameter("student_school", student_school);
			query.setParameter("student_buding", student_building);
			query.setParameter("student_housenum", student_housenum);
			query.setParameter("student_roomid", student_roomid);
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
	public List<TempStudent> getStudentBySchoolPageSize(String StudentSchool, int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<TempStudent> list = null;
		try {
			session.beginTransaction();
			String hql = "from TempStudent where student_school = :StudentSchool";
			Query query = session.createQuery(hql);
			query.setFirstResult(PageSize*(i-1));
			query.setMaxResults(PageSize);
			query.setParameter("StudentSchool", StudentSchool);
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
	public List<TempStudent> getTempStudentInvalidByPageSize(int i, int PageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveListTempStudent(List<TempStudent> student) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession(); // 获取Session
		Transaction tx = session.beginTransaction(); // 开启事物		
		TempStudent stu = null;
		try {
			for(int i = 0; i < student.size(); i ++) {
				
				stu = student.get(i);
				session.save(stu);
				
				if(i%100 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit(); // 提交事物
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 打印错误信息
			session.getTransaction().rollback(); // 出错将回滚事物
		}finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}

	}

	@Override
	public List<TempStudent> StudentByInfoName(String student_name, String student_building, String student_housenum,
			String student_ownerid, String student_school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<TempStudent> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student where  student_housenum = :student_housenum AND  student_budingid = :student_buding AND student_ownerid = :student_ownerid AND student_name = :student_name AND student_school = :student_school order by student_usetime desc";
			Query query = session.createQuery(hql);
			query.setParameter("student_buding", student_building);
			query.setParameter("student_housenum", student_housenum);
			query.setParameter("student_ownerid", student_ownerid);
			query.setParameter("student_school", student_school);
			query.setParameter("student_name", student_name);
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
