package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tc")
	private String	tckn;
	private String	name;
	private int		age;
	
	public Customer() {}
	
	public Customer(String name, int age, String tckn) {
		this.name = name;
		this.age = age;
		this.tckn = tckn;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}
	
	@Override
	public String toString(){
		return "[ Id: "+id+", TCKN: "+tckn+", Name: "+name+", Age: "+age+" ]";
	}
}
