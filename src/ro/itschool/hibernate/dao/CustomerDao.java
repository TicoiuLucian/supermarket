package ro.itschool.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.itschool.hibernate.entity.Customer;
import ro.itschool.hibernate.util.HibernateUtil;

public class CustomerDao {
	private Session currentSession;

	private Transaction currentTransaction;

	public CustomerDao() {
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

	public void persist(Customer entity) {
		currentSession.save(entity);
	}

	public void update(Customer entity) {
		currentSession.update(entity);
	}

	public Customer findById(Integer id) {
		Customer user = (Customer) currentSession.get(Customer.class, id);
		return user;
	}

	public void delete(Customer entity) {
		currentSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		List<Customer> users = (List<Customer>) currentSession.createQuery("from Customer").list();
		return users;
//		return currentSession.createQuery("SELECT a FROM User a", User.class).getResultList(); 
	}

	public void deleteAll() {
		List<Customer> entityList = findAll();
		for (Customer entity : entityList) {
			delete(entity);
		}
	}
}
