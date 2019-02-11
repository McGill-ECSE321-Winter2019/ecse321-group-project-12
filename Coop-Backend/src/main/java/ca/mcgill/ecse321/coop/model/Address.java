package ca.mcgill.ecse321.coop.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Address {
	private int streetNumber;

	public void setStreetNumber(int value) {
		this.streetNumber = value;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	private String streetName;

	public void setStreetName(String value) {
		this.streetName = value;
	}

	public String getStreetName() {
		return this.streetName;
	}

	private String city;

	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return this.city;
	}

	private String postalCode;

	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	private String province;

	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return this.province;
	}

	private String country;

	public void setCountry(String value) {
		this.country = value;
	}

	public String getCountry() {
		return this.country;
	}

	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	private int telephone;

	public void setTelephone(int value) {
		this.telephone = value;
	}

	public int getTelephone() {
		return this.telephone;
	}

	private User user;

	@OneToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private EventNotifcation eventNotifcation;

	@OneToOne(optional = false)
	public EventNotifcation getEventNotifcation() {
		return this.eventNotifcation;
	}

	public void setEventNotifcation(EventNotifcation eventNotifcation) {
		this.eventNotifcation = eventNotifcation;
	}

	private String addressId;

	public void setAddressId(String value) {
		this.addressId = value;
	}

	@Id
	public String getAddressId() {
		return this.addressId;
	}
}
