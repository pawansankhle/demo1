package com.demo.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.envers.Audited;

@Entity
@Table(name="address")
@XmlRootElement(name="Address")
@Audited
 @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Address implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Basic
	@Column(nullable=false, length=200)
	private String addressLine1;

	@Basic
	private String addressLine2;

	@Basic
	private String city;
	
	@Basic
	private String state;
	
	
	@Basic
	@Column
	private Integer pincode;


	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getPincode() {
		return pincode;
	}


	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
}
