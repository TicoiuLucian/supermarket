package ro.itschool.hibernate.service;

import java.util.List;

import ro.itschool.hibernate.dao.EmployeeDao;
import ro.itschool.hibernate.entity.Employee;

public class EmployeeService {
	private static EmployeeDao employeeDao;

	public EmployeeService() {
		employeeDao = new EmployeeDao();
	}

	public void persist(Employee entity) {
		employeeDao.openCurrentSessionwithTransaction();
		employeeDao.persist(entity);
		employeeDao.closeCurrentSessionwithTransaction();
	}

	public void update(Employee entity) {
		employeeDao.openCurrentSessionwithTransaction();
		employeeDao.update(entity);
		employeeDao.closeCurrentSessionwithTransaction();
	}

	public Employee findById(Integer id) {
		employeeDao.openCurrentSession();
		Employee employee = employeeDao.findById(id);
		employeeDao.closeCurrentSession();
		return employee;
	}

	public void delete(Integer id) {
		employeeDao.openCurrentSessionwithTransaction();
		Employee employee = employeeDao.findById(id);
		employeeDao.delete(employee);
		employeeDao.closeCurrentSessionwithTransaction();
	}

	public List<Employee> findAll() {
		employeeDao.openCurrentSession();
		List<Employee> employees = employeeDao.findAll();
		employeeDao.closeCurrentSession();
		return employees;
	}

	public void deleteAll() {
		employeeDao.openCurrentSessionwithTransaction();
		employeeDao.deleteAll();
		employeeDao.closeCurrentSessionwithTransaction();
	}

	public EmployeeDao employeeDao() {
		return employeeDao;
	}
}
