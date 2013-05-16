package info.selfhost.stammingerit.playground.webapptest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseEntity{
	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "SALT", nullable = false)
	private String salt;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;
	
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "DATEOFBIRTH", nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "GENDER", nullable = false)
	private String gender;
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS", nullable = false)
	private Address address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}