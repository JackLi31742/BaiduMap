package com.springmvc.dao;
 
import java.util.List;

import com.springmvc.entity.Temp;
 
/**
 * 
 */
public interface EventDao {
 
    
    public List<Temp> findByType(String event_type,long start,long end);
    
 
    public List<Temp> findByTypeAndCamerId(String event_type,long start,long end,List<String> canmerIds);
   
}
