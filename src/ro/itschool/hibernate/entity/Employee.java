package ro.itschool.hibernate.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DiscriminatorValue("Employee")
public class Employee extends Person {

	@Column
	private int wage;

	@Column
	private String jobTitle;

	@Column
	private int jobId;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private Set<Product> arrangedProducts = new HashSet<>();

	public Employee(String name, LocalDate birthDate, int wage, String jobTitle, int jobId) {
		super(name, birthDate);
		this.wage = wage;
		this.jobTitle = jobTitle;
		this.jobId = jobId;
	}

	public void addProduct(Product p) {
		this.arrangedProducts.add(p);
		p.setEmployee(this);
	}

	public void removeProduct(Product p) {
		this.arrangedProducts.remove(p);
		p.setEmployee(null);
	}

}
