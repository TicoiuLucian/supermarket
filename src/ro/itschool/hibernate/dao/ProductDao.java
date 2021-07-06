package ro.itschool.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.itschool.hibernate.entity.Product;
import ro.itschool.hibernate.service.NotEnoughProductsException;
import ro.itschool.hibernate.util.HibernateUtil;

public class ProductDao {
	private Session currentSession;

	private Transaction currentTransaction;

	public ProductDao() {
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

	public void persist(Product entity) {
		currentSession.save(entity);
	}

	public void update(Product entity) {
		currentSession.update(entity);
	}

	public Product findById(Integer id) {
		Product user = (Product) currentSession.get(Product.class, id);
		return user;
	}

	public void delete(Product entity) {
		currentSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		List<Product> users = (List<Product>) currentSession.createQuery("from Product").list();
		return users;
//		return currentSession.createQuery("SELECT a FROM User a", User.class).getResultList(); 
	}

	public void deleteAll() {
		List<Product> entityList = findAll();
		for (Product entity : entityList) {
			delete(entity);
		}
	}

	public void checkOut(int productId, int quantity) throws NotEnoughProductsException {
		Product p = findById(productId);
		if (p.getQuantity() < quantity) {
			throw new NotEnoughProductsException("Not enough products of this type");
		}
		p.setQuantity(p.getQuantity() - quantity);
		update(p);
	}
}
