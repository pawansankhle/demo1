package com.demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.IUserDao;
import com.demo.dao.generic.impl.GenericDaoImpl;
import com.demo.model.Users;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Users> implements IUserDao{

	private Logger logger = LoggerFactory.getLogger(UserRoleDaoImpl.class);
	@Override
	public Users findUserByName(String username) {
		logger.info("inside @class UserDaoimpl @method: findUserByName entry...");
		try{
			Query query=getEntityManager().createNamedQuery("findUserByName").setParameter("username",username);
			return  (Users) query.getSingleResult();
		  }catch(Exception ex){
			logger.error("Exception occured @class: UserDaoimpl @method: findUserByName @cause: "+ex.getMessage());
		}
		return null;
		
	}
	
	

	@Override
	public List<Users> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
		try{
			return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
		}catch(Exception ex){
			logger.error("inside @class Userdaoimpl cause:"+ex.toString());
			return null;
		}
		
	}

}
