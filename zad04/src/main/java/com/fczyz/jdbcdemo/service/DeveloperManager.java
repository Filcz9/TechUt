package com.fczyz.jdbcdemo.service;

import java.sql.Date;
import java.util.List;

import com.fczyz.jdbcdemo.domain.*;

public interface DeveloperManager {

	
	public int addDeveloper(Developer computergame);
	public List<Developer> getAllDevelopers();
	
	/* batch insert - transactional */
	public void addAllDevelopers(List<Developer> developers);
	//public void findByName(String name);
	//public void findOlderThan(Date string);
}
