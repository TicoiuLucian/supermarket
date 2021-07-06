package ro.itschool.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.itschool.hibernate.entity.Employee;
import ro.itschool.hibernate.util.HibernateUtil;

public class EmployeeDao {
	private Session currentSession;

	private Transaction currentTransaction;

	public EmployeeDao() {
	}

	public Session openCurrentSession() {
		currentSession = HibernateUtil.getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = HibernateUtil.getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public void persist(Employee entity) {
		currentSession.save(entity);
	}

	public void update(Employee entity) {
		currentSession.update(entity);
	}

	public Employee findById(Integer id) {
		Employee user = (Employee) currentSession.get(Employee.class, id);
		return user;
	}

	public void delete(Employee entity) {
		currentSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		List<Employee> users = (List<Employee>) currentSession.createQuery("from Employee").list();
		return users;
//		return currentSession.createQuery("SELECT a FROM User a", User.class).getResultList(); 
	}

	public void deleteAll() {
		List<Employee> entityList = findAll();
		for (Employee entity : entityList) {
			delete(entity);
		}
	}
}
