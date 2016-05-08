package com.demo.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.ICandidateDao;
import com.demo.dao.generic.impl.GenericDaoImpl;
import com.demo.exceptions.DaoException;

import com.demo.model.Candidate;
import com.demo.model.Roles;


@Repository
public class CandidateDaoImpl extends GenericDaoImpl<Candidate> implements ICandidateDao {

	/** The logger. */
	private Logger logger=LoggerFactory.getLogger(CandidateDaoImpl.class);


	@Override
	public Candidate create(@Valid Candidate candidate) {

		logger.info("Create record by an entity :"+candidate);

		try
		{
			return super.create(candidate);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :create with Finally Block 1");
		}

	}
	
	
	@Override
	public List<Candidate> search(SearchContext searchContext,
			Integer lowerLimit, Integer upperLimit, String orderBy,
			String orderType) {
		return super.search(searchContext, lowerLimit, upperLimit, orderBy, orderType);
	}

	/**
	 * 
	 *Returns the updated Candidate record
	 *@parameter anEntity of type  Candidate
	 *@returns a updated Candidate record
	 * 
	 */
	@Override
	public Candidate update(@Valid Candidate candidate)  {
		logger.info("update record by an entity of Id:"+candidate.getId());
		try
		{
			return super.update(candidate);
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
	 *Method to remove Candidate record
	 *@parameter Candidate of type  Candidate
	 * 
	 */
	public void delete(@Valid Candidate candidate)  {
		logger.info("Deleting record by an entity :"+candidate.getId());
		try
		{
			super.delete(candidate);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :delete with Finally Block 1");
		}

	}


	/**
	 * 
	 *Method to remove Candidate record by primary key
	 *@parameter primary key of type Integer  
	 * 
	 */
	/*public void deleteByPk(@NotNull Integer integerPk) {
		logger.info("Deleting record by primary key :"+integerPk);
		try
		{
			super.deleteByPk(integerPk);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :deleteByPk with Finally Block 1");
		}

	}

	/**
	 * 
	 *Returns the list of Candidate record 
	 *@returns  Candidate record
	 * 
	 */
	/**public List<Candidate> findAll() {
		try
		{
			//return super.findAll();
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :findAll with Finally Block 1");
		}
	}

	/**
	 * 
	 *Returns  the record of Candidate  finding by primary key 
	 *@parameter primary key  of type Integer
	 *@returns a Candidate record
	 * 
	 */
/*	public Candidate findByPk(@NotNull Integer integerPk) {
		logger.info("Find record by Primary Key :"+integerPk);

		try
		{
			return super.findByPk(integerPk);
		}catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			throw new DaoException(ExceptionUtil.generateExceptionCode("Dao","Tag",ex));
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :findByPk with Finally Block 1");
		}
	}
*/




	public Long getTotalCount(){
		return ((Long)this.getEntityManager().createQuery("select count(x) from Candidate x  ").getSingleResult());		
	}


	@Override
	public Integer getSearchRecordCount(SearchContext context, Integer userId,
			Set<Roles> roleSet)  {

		logger.info("Inside  @class :"+this.getClass().getName()+" @Method :getSearchRecordCount @Param:");
		Integer count=null;
		try{
			SearchCondition<Candidate> sc = null;
			if(context!=null){
				sc=context.getCondition(Candidate.class);
				if(sc!=null){
					JPATypedQueryVisitor<Candidate> visitor = new JPATypedQueryVisitor<Candidate>(getEntityManager(), getType());
					sc.accept(visitor);
					visitor.visit(sc);
					TypedQuery<Candidate> typedQuery = visitor.getTypedQuery();
					count=typedQuery.getResultList().size();
				}else
				{
					Long l=getTotalCount();
					count=l.intValue();
				}
			}	

		}
		catch(Exception ex)
		{
			logger.error("Error  occurred  @class"   + this.getClass().getName()  , ex);
			return null;

		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @method :getSearchRecordCount with Finally Block 1");
		}
		return count;
	}


	@Override
	public Candidate findByName(String candidateName) {
		Candidate candidate=new Candidate();
		try {
			logger.info("In Side Class "+this.getClass().getName()+" @Method findByName ");
			Query query = getEntityManager().createNamedQuery("findByName").setParameter("name", candidateName);
			candidate = (Candidate)query.getResultList().get(0);
			logger.info("In Method findByName @return subproject : "+candidate);
		} catch (Exception e) {
			logger.error("Error @class :"+this.getClass().getName()+" @Method :findByName ",e);
			return candidate;
		}finally{
			logger.info("Inside @class:"+this.getClass().getName()+" @findByName with Finally Block 1");
		}
		return candidate;
	}
}
