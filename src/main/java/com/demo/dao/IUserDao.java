package com.demo.dao;

import java.util.List;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.demo.dao.generic.IGenericDao;
import com.demo.model.Users;

public interface IUserDao extends IGenericDao<Users>
{
	
	Users findUserByName(String username);

	List<Users> search(SearchContext searchContext, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType);

	
	

}
