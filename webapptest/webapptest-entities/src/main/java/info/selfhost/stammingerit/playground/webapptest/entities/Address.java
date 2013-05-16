package info.selfhost.stammingerit.playground.webapptest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address extends BaseEntity {

	@Column(name = "COUNTRY", nullable = false)
	private String country;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "TOWN", nullable = false)
	private String town;
	
	@Column(name = "STREET", nullable = false)
	private String street;

	@Column(name = "STREETNUMBER", nullable = false)
	private String streetnumber;
	
	@Column(name = "ZIPCODE", nullable = false)
	private String zipcode;
	
}
