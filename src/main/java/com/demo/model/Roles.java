package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name="Roles") 
@Entity
@Table(name="roles")
public class Roles {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Basic
	@Column(nullable=false, length=200)
	private String description;

	@Basic
	@Column(nullable=false, length=50)
	private String rolename;
	
	@JsonIgnore
	@ManyToMany(targetEntity=Permissions.class, fetch=FetchType.EAGER)
	@JoinTable(name="rolepermission", joinColumns=@JoinColumn(name="roleid"), inverseJoinColumns=@JoinColumn(name="permissionid"))
	private Set<Permissions> permissions = new HashSet<Permissions>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permissions> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", description=" + description
				+ ", rolename=" + rolename + ", permissions=" + permissions
				+ "]";
	}

	

}
