package com.demo.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="candidate_company_details")
@Audited
public class CandidateCompanyDetails implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Basic
	private String companyName;
	
	@Basic
	private String companyAddress;
	
	@Basic
	private String currentPackage;
	
	@Basic
	private Integer experience;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCurrentPackage() {
		return currentPackage;
	}

	public void setCurrentPackage(String currentPackage) {
		this.currentPackage = currentPackage;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "CandidateCompanyDetails [id=" + id + ", companyName="
				+ companyName + ", companyAddress=" + companyAddress
				+ ", currentPackage=" + currentPackage + ", experience="
				+ experience + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((currentPackage == null) ? 0 : currentPackage.hashCode());
		result = prime * result
				+ ((experience == null) ? 0 : experience.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateCompanyDetails other = (CandidateCompanyDetails) obj;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (currentPackage == null) {
			if (other.currentPackage != null)
				return false;
		} else if (!currentPackage.equals(other.currentPackage))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
