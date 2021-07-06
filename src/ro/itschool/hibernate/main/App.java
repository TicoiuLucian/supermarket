package ro.itschool.hibernate.main;

import java.time.LocalDate;

import ro.itschool.hibernate.entity.Customer;
import ro.itschool.hibernate.entity.Employee;
import ro.itschool.hibernate.entity.Product;
import ro.itschool.hibernate.service.CustomerService;
import ro.itschool.hibernate.service.EmployeeService;
import ro.itschool.hibernate.service.NotEnoughProductsException;
import ro.itschool.hibernate.service.ProductService;

public class App {

	public static void main(String[] args) {
		ProductService productService = new ProductService();

		Product p1 = new Product("iaurt grecesc", 1.49f, 12);
		Product p2 = new Product("paine", 2.99f, 31);
		Product p3 = new Product("pateu", 3.79f, 60);
		Product p4 = new Product("salam uscat", 11.49f, 6);

		productService.persist(p1);
		productService.persist(p2);
		productService.persist(p3);
		productService.persist(p4);

		// -----------------inheritanceMapping single table--------------

		Employee e1 = new Employee("Gigel", LocalDate.of(1945, 8, 1), 15000, "Account Manager", 1234);
		e1.addProduct(p1);
		e1.addProduct(p2);

		Employee e2 = new Employee("Victorel", LocalDate.of(1955, 1, 19), 9000, "Casier", 1235);
		e2.addProduct(p3);
		e2.addProduct(new Product("Bere", 8, 55));

		Employee e3 = new Employee("Dorel", LocalDate.of(1965, 3, 14), 6000, "Manipulator marfa", 1236);
		e3.addProduct(p4);

		Customer c1 = new Customer("Clientul 1", LocalDate.of(1945, 8, 1), 25, 3);
		Customer c2 = new Customer("Clientul 2", LocalDate.of(1925, 1, 3), 950, 300);
		Customer c3 = new Customer("Clientul 3", LocalDate.of(1995, 2, 4), 250, 31);

		EmployeeService employeeService = new EmployeeService();
		employeeService.persist(e1);
		employeeService.persist(e2);
		employeeService.persist(e3);

		CustomerService customerService = new CustomerService();
		customerService.persist(c1);
		customerService.persist(c2);
		customerService.persist(c3);

		try {
			productService.checkOut(p4.getId(), 4);
			customerService.buy(c1, p4);
		} catch (NotEnoughProductsException e) {
			e.printStackTrace();
		}

	}

}
