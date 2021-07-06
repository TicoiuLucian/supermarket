package ro.itschool.hibernate.service;

import java.util.List;

import ro.itschool.hibernate.dao.CustomerDao;
import ro.itschool.hibernate.dao.ProductDao;
import ro.itschool.hibernate.entity.Customer;
import ro.itschool.hibernate.entity.Product;

public class CustomerService {
	private static CustomerDao customerDao;
	private static ProductDao productDao = new ProductDao();

	public CustomerService() {
		customerDao = new CustomerDao();
	}

	public void persist(Customer entity) {
		customerDao.openCurrentSessionwithTransaction();
		customerDao.persist(entity);
		customerDao.closeCurrentSessionwithTransaction();
	}

	public void update(Customer entity) {
		customerDao.openCurrentSessionwithTransaction();
		customerDao.update(entity);
		customerDao.closeCurrentSessionwithTransaction();
	}

	public Customer findById(Integer id) {
		customerDao.openCurrentSession();
		Customer customer = customerDao.findById(id);
		customerDao.closeCurrentSession();
		return customer;
	}

	public void delete(Integer id) {
		customerDao.openCurrentSessionwithTransaction();
		Customer customer = customerDao.findById(id);
		customerDao.delete(customer);
		customerDao.closeCurrentSessionwithTransaction();
	}

	public List<Customer> findAll() {
		customerDao.openCurrentSession();
		List<Customer> customers = customerDao.findAll();
		customerDao.closeCurrentSession();
		return customers;
	}

	public void deleteAll() {
		customerDao.openCurrentSessionwithTransaction();
		customerDao.deleteAll();
		customerDao.closeCurrentSessionwithTransaction();
	}

	public CustomerDao customerDao() {
		return customerDao;
	}

	public void buy(Customer c1, Product p1) throws NotEnoughProductsException {
		customerDao.openCurrentSessionwithTransaction();
		c1.addToBoughtItems(p1);
		customerDao.update(c1);
		customerDao.closeCurrentSessionwithTransaction();
	}
}
