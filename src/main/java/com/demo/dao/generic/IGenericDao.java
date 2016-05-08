package com.demo.dao.generic;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.jaxrs.ext.search.SearchContext;




public interface IGenericDao<T> {
	
	
	long countAll( Map<String, Object> params);
	
    T create(T t);

    void delete(Object id);

    T find(Object object);

    T update(T t); 
    
    List<T> search(SearchContext context,Integer lowerLimit, Integer upperLimit,
			String orderBy, String orderType);
    
   
    
    
    
    
}
