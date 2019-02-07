package ug.fczyz.javaut.zad04.service;

import java.util.List;

import ug.fczyz.javaut.zad04.domain.Developer;

public interface DeveloperManager {
	void addDeveloper(Developer developer);
	List<Developer> getAllDevelopers();
	Developer getDeveloperById(long id);
	void deleteDeveloper(Developer developer);
	void updateDeveloper(Developer developer);
	Developer getDeveloperByName(String name);
	void clearTable();
}
