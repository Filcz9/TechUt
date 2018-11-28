package com.fczyz.jdbcdemo.domain;

public class AgeRestriction {
	private long id;
	
	private int age;


	
	public AgeRestriction()
	{		
	}
	public AgeRestriction(int age)
	{
		super();
		this.age = age;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}