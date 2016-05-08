package com.demo.dao;

import java.util.Set;

import com.demo.dao.generic.IGenericDao;
import com.demo.exceptions.DaoException;
import com.demo.model.Roles;
import com.demo.model.UserRole;

public interface IUserRoleDao extends IGenericDao<UserRole>{

	public Set<Roles> getRoleByUserId(Integer userId);
	public Set<UserRole> getUserRoleByUserId(Integer userId)throws DaoException;
	UserRole getUserRoleByUserIdAndRoleId(Integer userId, Integer roleId)
			throws DaoException;
	UserRole getUserRoleByUserIdAndWorkspace(Integer userId, Integer workspaceId)
			throws DaoException;
	public UserRole getUserRoleByUserIdAndWorkspaceName(Integer userId,
			String workspace)throws DaoException;
}
