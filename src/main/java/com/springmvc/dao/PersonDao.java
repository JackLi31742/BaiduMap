package com.springmvc.dao;
 
import java.util.List;

import com.springmvc.entity.Person;
 
/**
 * 
 */
public interface PersonDao {
 
    
    public List<Person> find(int camId);
 
   
}
