package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int key;

	private String country;
	private String city;
	private String district;
	private String addressrest;

	public int getKey() {
		return this.key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return this.addressrest;
	}

	public void setAddress(String address) {
		this.addressrest = address;
	}

	public Address() {}

	public Address(String country, String city, String district, String address) {
		this.country = country;
		this.city = city;
		this.district = district;
		this.addressrest = address;
	}

	@Override
	public String toString(){
		return "[ country: "+country+", city: "+city+", district: "+district+", address: "+addressrest+" ]";
	}
}
