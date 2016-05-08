package com.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Users;
import com.demo.service.IUsersService;
import com.demo.dao.IUserDao;

@Service
@Transactional
public class UserServiceImpl implements IUsersService{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	IUserDao dao;
	
	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;
	
	@Override
	public Users findByName(String username) {
		logger.info("inside @class UserServiceImpl @method findByName entry...");
		try{
			return dao.findUserByName(username);
		}catch(Exception ex){
			logger.error("@class UserServiceImpl @method findByName  cause: "+ex.toString());
			return null;
		}
	}
	
	@Override
	public Users Customlogin(String j_username,String j_password,HttpSession session) {
		logger.info("inside @class UserServiceImpl @method Customlogin entry...");

		Authentication token = new UsernamePasswordAuthenticationToken(j_username, j_password);
		try{
			logger.info("inside @class userServiceImpl @method customlogin token is: "+token.toString());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute("authentication",authentication);
			String username = authentication.getName();
			Users user = dao.findUserByName(username);
			if(user!=null && authentication!=null)
				return user;
		}catch(org.springframework.security.core.AuthenticationException ex){
			logger.error("@class UserServiceImpl @method Customlogin  cause: "+ex.toString());
			return null;
		}
		return null;
	}
	
	
	@Override
	public void customLogout(HttpServletRequest request,HttpSession session) {
		logger.info("inside @class UserServiceImpl @method customLogout entry...");

		try{
			Authentication authentication = (Authentication) session.getAttribute("authentication");
			if (authentication != null){ 
				new SecurityContextLogoutHandler().logout(request,null,authentication);
			}
			SecurityContextHolder.getContext().setAuthentication(null);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("@class UserServiceImpl @method customLogout  cause: "+ex.toString());

		}
	}

	
	@Override
	public List<Users> search(SearchContext context, Integer lowerLimit,
			Integer upperLimit, String orderBy, String orderType) {
      logger.info("inside @class"+this.getClass().getName()+"@method findByName entry...");
		return dao.search(context, lowerLimit, upperLimit, orderBy, orderType);
	}

}
