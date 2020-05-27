package model;

public class Customer {
private int customerId;
private String name;
private String phoneNo;
private String email;
private String address;
private String country;
private String cardNo;
private int zipCode;
private String city;
public Customer(String name, String phoneNo, String email, String address, String country, String cardNo, int zipCode,
		String city) {
	super();
	this.name = name;
	this.phoneNo = phoneNo;
	this.email = email;
	this.address = address;
	this.country = country;
	this.cardNo = cardNo;
	this.zipCode = zipCode;
	this.city = city;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCardNo() {
	return cardNo;
}
public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}
public int getZipCode() {
	return zipCode;
}
public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}


}
