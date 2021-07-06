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
import lombok.extern.java.Log;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Customer")
@Log
public class Customer extends Person {

	@Column
	private int moneyToSpend;

	@Column
	private int loyaltyPoints;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> boughtItems = new HashSet<>();

	public Customer(String name, LocalDate birthDate, int moneyToSpend, int loyaltyPoints) {
		super(name, birthDate);
		this.moneyToSpend = moneyToSpend;
		this.loyaltyPoints = loyaltyPoints;
	}

	public void addToBoughtItems(Product p) {
		this.boughtItems.add(p);
		log.info("Customerul " + name + " a cumparat " + p.getName());
//		p.setQuantity(p.getQuantity() - quantity);
	}

}
