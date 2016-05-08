package com.demo.rest.impl;

import java.io.File;
import java.util.List;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.cxf.jaxrs.ext.search.SearchContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Candidate;
import com.demo.model.Users;
import com.demo.service.ICandidateService;

import javax.validation.Valid;
 
/**
 * 
 * Rest 
 *
 */
 
@Path("/Candidate")
@Produces("application/json")
@Consumes("application/json")
@Service("CandidateRestImpl")

public class CandidateRestImpl{
private Logger logger=LoggerFactory.getLogger(CandidateRestImpl.class);
	
	@Autowired
	private ICandidateService candidateService;

		
	@Context
	private SearchContext context;


	

	
	
	/**
	 * 
	 *Returns the record by searching Candidate name
	 *@parameter Candidate of typeCandidate 
	 *@returns a list of Candidate record
	 * 
	 */	
	@GET
	public List<Candidate> search(@QueryParam("") Candidate candidate) {
		try{
		return candidateService.search(candidate);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			 logger.info("Inside @class:"+this.getClass().getName()+" @method :search with Finally Block 1");
		}
	}
	
		
	/**
	 * 
	 *Returns the list of Candidate by using lowerlimit and upper limit
	 *@path get path and produce Candidate list
	 *@parameter llimit ulimit of type integer in query param
	 *@returns a list of Candidate record
	 * 
	 */		
	@GET
	@Path("search")
	public List<Candidate> search(@QueryParam("llimit") Integer lowerLimit, @QueryParam("ulimit") Integer upperLimit,@QueryParam("orderBy") String orderBy,@QueryParam("orderType") String orderType){
			try{
			return candidateService.search(context,upperLimit,lowerLimit,orderBy,orderType);
			}
			catch(Exception ex)
			{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
			}finally{
				 logger.info("Inside @class:"+this.getClass().getName()+" @method :search with Finally Block 1");
			}
		
	}


	
	/**
	 * 
	 *Returns the new Candidate record
	 *@path get path and produce Candidate record
	 *@parameter valid Candidate entity
	 *@returns a new Candidate record
	 * 
	 */	
	@POST
	@Path("create")
		public Candidate create(@Valid Candidate candidate){
		logger.info("Create record for Canddate : "+candidate);
		//Users username =CustomerInfo.getUserInContext();
		try{
				return candidateService.create(candidate);
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
	 *Returns the updated Candidate record
	 *@path get path and produces updated Candidate record
	 *@parameter valid Candidate entity
	 *@returns a updated Candidate record
	 * 
	 */	
	@POST
	@Path("update")
		public Candidate update(@Valid Candidate candidate) {
	logger.info("Update record for candidate by id : "+candidate.getId());
	//Users username =CustomerInfo.getUserInContext();
	try{
		Candidate newCandidate = candidateService.update(candidate);
				
					
				
				return newCandidate;
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
	 *Returns the removed Candidate record
	 *@path get path and delete Candidate record 
	 *@parameter valid Candidate entity
	 *@returns a removed Candidate record
	 * 
	 */	
	@Path("delete")
		public boolean remove(Candidate candidate) {
	//Users username =CustomerInfo.getUserInContext();
	logger.info("Removing record by Candidate of Id :"+candidate.getId());
	try{
		//candidateService.remove(candidate);
				
				
				
			
		return true;
		}catch(Exception ex)
			{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return false;
			}finally{
				 logger.info("Inside @class:"+this.getClass().getName()+" @method :remove with Finally Block 1");
			}
	}

	/**
	 * 
	 *method remove audit action
	 *@path get path to remove audit action
	 *@parameter id of type Integer in path param
	 * 
	 */
	@POST
	@Path("delete/{id}")
		public void removeById(@PathParam("id") Integer primaryKey) {
//	Users username =CustomerInfo.getUserInContext();
	logger.info("Remove record by primary key :"+primaryKey);
		try{
		//Candidate candidate=candidateService.findById(primaryKey);
		//candidateService.removeById(primaryKey);
				
				
				
				}catch(Exception ex)
			{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			//return null;
			}finally{
				 logger.info("Inside @class:"+this.getClass().getName()+" @method :removeById with Finally Block 1");
			}
	
	}
	
	public SearchContext getSearchContext() {
		return context;
	}
		
	
	@GET
	@Path("totalCount")
      public Long getTotalCount(){
				return candidateService.getTotalCount();
	}
	
	
	@GET
	@Path("getSearchRecordCount")
	@Produces("application/json")
    public Integer getSearchRecordCount(){	
		try{
			return candidateService.getSearchRecordCount(context);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			 logger.info("Inside @class:"+this.getClass().getName()+" @method :getSearchRecordCount with Finally Block 1");
		}
		
	}
	
	



}
