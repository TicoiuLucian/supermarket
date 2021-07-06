package ro.itschool.hibernate.service;

import java.util.List;

import ro.itschool.hibernate.dao.ProductDao;
import ro.itschool.hibernate.entity.Product;

public class ProductService {
	private static ProductDao productDao;

	public ProductService() {
		productDao = new ProductDao();
	}

	public void persist(Product entity) {
		productDao.openCurrentSessionwithTransaction();
		productDao.persist(entity);
		productDao.closeCurrentSessionwithTransaction();
	}

	public void update(Product entity) {
		productDao.openCurrentSessionwithTransaction();
		productDao.update(entity);
		productDao.closeCurrentSessionwithTransaction();
	}

	public Product findById(Integer id) {
		productDao.openCurrentSession();
		Product user = productDao.findById(id);
		productDao.closeCurrentSession();
		return user;
	}

	public void delete(Integer id) {
		productDao.openCurrentSessionwithTransaction();
		Product user = productDao.findById(id);
		productDao.delete(user);
		productDao.closeCurrentSessionwithTransaction();
	}

	public List<Product> findAll() {
		productDao.openCurrentSession();
		List<Product> users = productDao.findAll();
		productDao.closeCurrentSession();
		return users;
	}

	public void deleteAll() {
		productDao.openCurrentSessionwithTransaction();
		productDao.deleteAll();
		productDao.closeCurrentSessionwithTransaction();
	}

	public void checkOut(int productId, int quantity) throws NotEnoughProductsException {
		productDao.openCurrentSessionwithTransaction();
		productDao.checkOut(productId, quantity);
		productDao.closeCurrentSessionwithTransaction();
	}

	public ProductDao userDao() {
		return productDao;
	}
}
