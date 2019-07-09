package com.tongansz.dao.impl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tongansz.dao.StudentDAO;
import com.tongansz.model.Student;
import com.tongansz.utils.HibernateUtil;

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
			System.out.println(i +"  " + PageSize);
			Query query = session.createQuery(hql);
			query.setFirstResult(PageSize*(i-1));
			query.setMaxResults(PageSize);
			list = query.list();
			System.out.println(list.size());
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
	public List<Student> getStudentBySchool(String StudentSchool) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student where student_school = :StudentSchool";
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
	public List<Student> StudentValid(String student_building, String student_housenum
			) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student where  student_housenum = :student_housenum AND student_budingid = :student_buding order by student_usetime desc";
			Query query = session.createQuery(hql);
			query.setParameter("student_buding", student_building);
			query.setParameter("student_housenum", student_housenum);
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
	public List<Student> StudentByInfo(String student_building, String student_housenum, String student_roomid,
			String student_ownerid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student where  student_housenum = :student_housenum AND student_roomid = :student_roomid AND student_budingid = :student_buding AND student_ownerid = :student_ownerid order by student_usetime desc";
			Query query = session.createQuery(hql);
			query.setParameter("student_buding", student_building);
			query.setParameter("student_housenum", student_housenum);
			query.setParameter("student_roomid", student_roomid);
			query.setParameter("student_ownerid", student_ownerid);
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
	public List<Student> StudentByInfoName(String student_name, String student_building, String student_housenum,
			String student_ownerid,String student_school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
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

	@Override
	public List<Student> getStudentBySchoolPageSize(String StudentSchool, int i, int PageSize) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student where student_school = :StudentSchool";
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
	public void SaveListStudent(List<Student> student) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession(); // 获取Session
		Transaction tx = session.beginTransaction(); // 开启事物		
		Student stu = null;
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
	public List<Student> VagueByPlace(String student_housenum) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student as a where a.StudentHouseNumber like :StudentHouseNumber";
			Query query = session.createQuery(hql);
			query.setString("StudentHouseNumber", "%"+student_housenum+"%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return list;
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Student> VagueByPlaceAndSchool(String student_housenum, String student_school) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<Student> list = null;
		try {
			session.beginTransaction();
			String hql = "from Student as a where a.StudentHouseNumber like :StudentHouseNumber AND student_school = :StudentSchool";
			Query query = session.createQuery(hql);
			query.setString("StudentHouseNumber", "%"+student_housenum+"%");
			query.setParameter("StudentSchool", student_school);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return list;
		}
		// TODO Auto-generated method stub
		return list;
	}

}
