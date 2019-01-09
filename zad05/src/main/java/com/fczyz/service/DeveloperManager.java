package com.fczyz.service;

import java.util.List;
import com.fczyz.domain.Developer;
public interface DeveloperManager {
	void addDeveloper(Developer developer);
	List<Developer> getAllDevelopers();
	Developer getDeveloperById(long id);
	void deleteDeveloper(Developer developer);
	void updateDeveloper(Developer developer);
	Developer getDeveloperByName(String name);
	void clearTable();
}
