package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.demo.dao.IUserRoleDao;
import com.demo.security.ContextProvider;

@NamedQueries({
@NamedQuery(name="findUserByName",query="Select u From Users u where username=:username"),
@NamedQuery(name="findUserById",query="Select u From Users u where id=:id"),
})

@XmlRootElement(name="Users")
@Entity
@Table(name = "users")
@Audited
public class Users {
	
	@Id
	@GeneratedValue
	private Integer userid;
	
	@Basic
	@Column(length=50)
	private String firstname;

	@Basic
	@Column(length=50)
	private String lastname;
	
	@Basic
	@Column(nullable=false, length=50)
	private String email;

	@Basic
	private Boolean enabled;
	
	@Basic
	@Column(nullable=false, length=50)
	private String password;

	@Basic
	@Column(length=20)
	private String contactNumber;
	
	@Basic
	@Column(nullable=false, length=50)
	private String username;

	@Basic
	private Boolean deleted;
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="user_address")
	private Address userAddress;
	
	@ManyToMany(targetEntity=com.demo.model.Roles.class,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="userrole", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="roleid"))
	@NotAudited
	private Set<Roles> roles = new HashSet<Roles>();

	public Address getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Users [firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", enabled=" + enabled + ", password="
				+ password + ", contactNumber=" + contactNumber + ", username="
				+ username + ", deleted=" + deleted + ", userAddress="
				+ userAddress + "]";
	}
	
	
	
	
	/*public Set<UserRole> getUserRole(){
		IUserRoleDao userRoleDao =ContextProvider.getContext().getBean(IUserRoleDao.class);
		Set<UserRole> userRolesSet=null;
		try {
			userRolesSet = userRoleDao.getUserRoleByUserId(userid);
		} catch (Exception e) {
			return null;
		}
		return userRolesSet;
	}*/

}
