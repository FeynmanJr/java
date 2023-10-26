package model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int	id;

	private String customerNo;
	private String password;
	private String name;
	private String email;
	private String creationDate;
	
	public Accounts() {}
		
	public Accounts(String customerNo, String password, String name, String email, String creationDate) {
		this.customerNo = customerNo;
		this.password = password;
		this.name = name;
		this.email = email;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", customerNo=" + customerNo + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", creationDate=" + creationDate + "]";
	}
}
