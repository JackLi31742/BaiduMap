package com.springmvc.dao;
 
import java.util.List;

import com.springmvc.entity.Temp;
 
/**
 * 
 */
public interface PersonDao2 {
 
    
    public List<Temp> findByName(String name,long start,long end);
 
    public List<Temp> findByNameAndCamerId(String name,long start,long end,List<String> canmerIds);
   
}
