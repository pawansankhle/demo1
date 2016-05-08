package com.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.demo.model.Users;



public interface IUsersService {
	 
	   Users findByName(String username);
	   List<Users> search(SearchContext context, Integer lowerLimit,
				Integer upperLimit, String orderBy, String orderType);
	Users Customlogin(String j_username, String j_password, HttpSession session);
	void customLogout(HttpServletRequest request, HttpSession session);
}
