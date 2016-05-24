package com.demo.rest.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import net.minidev.json.JSONObject;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Candidate;
import com.demo.service.ICandidateService;
import com.demo.util.ExcelUtils;
 
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
	private ICandidateService service;

		
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
		return service.search(candidate);
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
			return service.search(context,upperLimit,lowerLimit,orderBy,orderType);
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
				return service.create(candidate);
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
		Candidate newCandidate = service.update(candidate);
				
					
				
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
				return service.getTotalCount();
	}
	
	
	@GET
	@Path("getSearchRecordCount")
	@Produces("application/json")
    public Integer getSearchRecordCount(){	
		try{
			return service.getSearchRecordCount(context);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			 logger.info("Inside @class:"+this.getClass().getName()+" @method :getSearchRecordCount with Finally Block 1");
		}
		
	}
	
    @POST
	@Path("bulkUploadCandidate")
	@Consumes("multipart/form-data")
	@Produces("text/html")
	public String bulkUploadCandidate(@Multipart(value = "filedata") InputStream in,@QueryParam(value = "filename") String fileName) throws Exception {
	logger.info("Inside  @class" + this.getClass().getName()+ " @Method : bulkUploadCandidate @Param filePath " + fileName);
		File fileNew = new File(fileName);
		List<JSONObject> jsonExceptionList = new java.util.ArrayList<JSONObject>();
		int success = 0;
		int failure = 0;
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = workbook.getSheetAt(0);
			JSONObject exceptionMessage = new JSONObject();
			Iterator<Row> rowIterator = sheet.iterator();

			String headerNotMatch = ExcelUtils.checkExcelImportHeaderFormat(sheet,getCandidteImportFileHeader());
			if (headerNotMatch.isEmpty()) {
				int j = 0;
				boolean isMaxRecord=false;
				while (rowIterator.hasNext()) 
				{
					Row row = null;
					try 
					{
						if(j>100){
							isMaxRecord=true;
							throw (new Exception("Maximum 100 records can be uploaded at a time "));
						}
						row = rowIterator.next();
						if(ExcelUtils.isEmptyRow(row)){
							if (j != 0) {
								
								service.bulkUploadCandidate(row);
								++success;
							}
						}
						j++;	
					}catch (Exception e) {
						j++;
						exceptionMessage=new JSONObject();
						logger.error("Error  occurred  @class"+this.getClass().getName()+" @Method : bulkUploadCandidate ",e);		
						String msg="";
						if(e.getMessage()!=null)
						{
							msg=msg+e.getMessage();
						}
						if(msg==null || msg.equals("")){
							msg="Please import .xls with proper format";
						}
						exceptionMessage.put("error",msg);						
						jsonExceptionList.add(exceptionMessage);
						if(isMaxRecord){
							break;
						}
						failure++;			
						//throw new RestException(ExceptionUtil.generateExceptionCode("Rest","Issue",e,j));
					}finally{
						 logger.info("Inside @class:"+this.getClass().getName()+" @method :bulkUploadIssue with Finally Block 1");
					}
				}
			}else
			{
				exceptionMessage.put("error",headerNotMatch);
				jsonExceptionList.add(exceptionMessage);
				return "{\"status\":\"200\",\"excpmesg\":"+jsonExceptionList.toString()+"}";
			}

			fileNew.delete();
			exceptionMessage=new JSONObject();
			exceptionMessage.put("success",success);
			exceptionMessage.put("failure",failure);
			exceptionMessage.put("Total",(success+failure));
			jsonExceptionList.add(exceptionMessage);
			if (!jsonExceptionList.isEmpty()) {
				return "{\"status\":\"200\",\"excpmesg\":"+ jsonExceptionList.toString() + "}";
			}
		} catch (Exception e) {
			logger.error("Error  occurred  @class" + this.getClass().getName()
					+ " @Method : bulkUploadCandidate ", e);

		}finally{
			 logger.info("Inside @class:"+this.getClass().getName()+" @method :bulkUploadCandidate with Finally Block 2");
		}
		return "{\"data\":\"Candidate Uploaded successfully\"}";

	}


private List<String> getCandidteImportFileHeader() {
		List<String> list = new ArrayList<String>();
		list.add("FIRSTNAME");
		list.add("LASTNAME");
		list.add("MOBILE");
		list.add("EMAIL");
		return list;
	}




}
