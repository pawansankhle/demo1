package com.demo.rest.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.model.LoginForm;
import com.demo.model.Users;
import com.demo.service.IUsersService;

@Path("/Users")
@Produces("application/json")
@Consumes("application/json")
@Service("UsersRestImpl")
public class UsersRestImpl {
	
	private Logger logger=LoggerFactory.getLogger(UsersRestImpl.class);

	@Autowired
	private IUsersService service;
	
	@Context
	private SearchContext context;
	
	@GET
	@Path("/profile")
	public Users isUserAvailable(){
		HttpSession  session = getSession();
        Authentication authentication  = (Authentication) session.getAttribute("authentication");
		if(authentication!=null){
			String username = authentication.getName();
			Users user =  service.findByName(username);
			return user;
		}else{
			return null;
		}

	}

	
	@POST
	@Path("/login")
	public Users cutomLogin(@Valid LoginForm loginForm){
		logger.info("inside @class UserRestImpl  @method loginUser entry...");
		HttpSession  session = getSession();
		String j_username = loginForm.getusername();
		String j_password = loginForm.getpassword();

		Users usr =  service.Customlogin(j_username,j_password,session);
		return usr;
	}

	@GET
	@Path("/logout")
	public String customLogout(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpSession  session = getSession();
		service.customLogout(request,session);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null)
		{
			return "{\"status\": \"success\",\"message\":\"logout successfully\"}"; 

		}else
		{
			return "{\"status\": \"error\",\"message\":\"could not logout\"}";
		}

	}

	/*@POST
	@Path("/changePassword/{oldpassword}/{password}")
	public String submitChangePassword(@PathParam("oldpassword") String oldPassword,@PathParam("password") String newPassword){

		HttpSession session = getSession();
		Authentication auth = (Authentication) session.getAttribute("authentication");
		if(auth !=null){
			String username = auth.getName();
			logger.info("@class @method username is: "+username);
			String res = userService.changePassword(username,oldPassword,newPassword);
			if(res !=null){
				SecurityContextHolder.clearContext();
				return res;
			}
		}
		return null;
	}*/


	
	
	private HttpSession getSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
	    HttpSession  session = request.getSession(true);
		return session;
	}
}
