package com.demo.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.IUserRoleDao;
import com.demo.dao.generic.impl.GenericDaoImpl;
import com.demo.exceptions.DaoException;
import com.demo.model.Roles;
import com.demo.model.UserRole;
@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements IUserRoleDao{
private Logger logger=LoggerFactory.getLogger(UserRoleDaoImpl.class);

	
	/**
	 * 
	 *method to create userrole for user 
	 *@param userrole of type userrole
	 *@return userrole
	 *
	 */
	@Override
	public UserRole create(UserRole userRole){
		logger.info("Creating userRole by userRole :"+userRole);
		try{
		return super.create(userRole);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :create with Finally Block 1");
		}
	}
	
	/**
	 * 
	 *method to update userRole 
	 *@param UserRole of type UserRole 
	 *@return update UserRole
	 *
	 */
	@Override
	public UserRole update(UserRole userRole){
	logger.info("Updating UserRole by userRole :"+userRole);
		try{
		return super.update(userRole);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :update with Finally Block 1");
		}
	}

	/**
	 * 
	 *method to delete userRole 
	 *@param userRole of type UserRole  
	 *
	 */
	/*@Override
	public void delete(UserRole userRole) {
	logger.info("Deleting UserRole by userRole :"+userRole);
		try{
		super.delete(userRole);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :delete with Finally Block 1");
		}
	}
*/
	

	/**
	 * 
	 *method to find role by primarykey 
	 *@param userRolePk of type Long
	 *@return role
	 *
	 */
	public UserRole findByPk(Integer userRolePk) throws DaoException{
	logger.info("Finding UserRole by userRolePk :"+userRolePk);
		try{
		return null;//(super.findByPk(userRolePk));
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :findByPk with Finally Block 1");
		}
	}
	@Override
	public Set<Roles> getRoleByUserId(Integer userId){
		logger.info("inside Class "+this.getClass().getName()+" method getRoleByUserId by userId :"+userId);
		try {
			Query query=getEntityManager().createNamedQuery("getRoleByUserId").setParameter("userId",userId); 
			List<Roles> listRoles=query.getResultList();
			if(listRoles!=null){
				Set<Roles> roles=new HashSet<Roles>(listRoles);
				return roles;
			}else{
				logger.info("inside Class "+this.getClass().getName()+" method getRoleByUserId Return null");
				return null;
			}
			
		} catch (Exception e) {
			logger.error("inside Class "+this.getClass().getName()+" method getRoleByUserId Ex :"+e);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getRoleByUserId with Finally Block 1");
		}
	}
	
	@Override
	public Set<UserRole> getUserRoleByUserId(Integer userId)throws DaoException{
		logger.info("inside Class "+this.getClass().getName()+" method getRoleByUserId by userId :"+userId);
		try {
			Query query=getEntityManager().createNamedQuery("getUserRoleByUserId").setParameter("userId",userId); 
			List<UserRole> listRoles=query.getResultList();
			if(listRoles!=null){
				Set<UserRole> roles=new HashSet<UserRole>(listRoles);
				return roles;
			}else{
				logger.info("inside Class "+this.getClass().getName()+" method getRoleByUserId Return null");
				return null;
			}
			
		} catch (Exception e) {
			logger.error("inside Class "+this.getClass().getName()+" method getRoleByUserId Ex :"+e);
			throw new DaoException(e);
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getUserRoleByUserId with Finally Block 1");
		}
	}
	@Override
	public UserRole getUserRoleByUserIdAndRoleId(Integer userId,Integer roleId)throws DaoException{
		logger.info("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndRoleId by userId :"+userId);
		try {
			Query query=getEntityManager().createNamedQuery("getUserRoleByUserIdAndRoleId").setParameter("userId",userId).setParameter("roleId", roleId); 
			UserRole userRole=(UserRole) query.getSingleResult();
			return userRole;
		} catch (NoResultException e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndRoleId @NoResultException :"+e);
			return null;
		}catch (Exception e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndRoleId @Exception :"+e);
			throw new DaoException(e);
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getUserRoleByUserIdAndRoleId with Finally Block 1");
		}
	}
	@Override
	public UserRole getUserRoleByUserIdAndWorkspace(Integer userId,Integer workspaceId)throws DaoException{
		logger.info("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspace by userId :"+userId +" workspaceId : "+workspaceId);
		try {
			Query query=getEntityManager().createNamedQuery("getUserRoleByUserIdAndWorkspace").setParameter("userId",userId).setParameter("workspaceId", workspaceId);
			UserRole userRole=(UserRole) query.getSingleResult();
			return userRole;
		} catch (NoResultException e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspace @NoResultException :"+e);
			return null;
		}catch (Exception e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspace @Exception :"+e);
			throw new DaoException(e);
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getUserRoleByUserIdAndWorkspace with Finally Block 1");
		}
		
	}	
	@Override
	public UserRole getUserRoleByUserIdAndWorkspaceName(Integer userId,String workspace) throws DaoException {
		logger.info("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspaceName by userId :"+userId +" workspace : "+workspace);
		try {
			Query query=getEntityManager().createNamedQuery("getUserRoleByUserIdAndWorkspaceName").setParameter("userId",userId).setParameter("workspace", workspace);
			UserRole userRole=(UserRole) query.getSingleResult();
			return userRole;
		} catch (NoResultException e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspaceName @NoResultException :"+e);
			return null;
		}catch (Exception e) {
			logger.error("inside Class "+this.getClass().getName()+" @method getUserRoleByUserIdAndWorkspaceName @Exception :"+e);
			throw new DaoException(e);
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getUserRoleByUserIdAndWorkspaceName with Finally Block 1");
		}
		
	}
}
