package com.fczyz.jdbcdemo.domain;

public class Developer {
	private long id;
	private String name;
	private int nip;

	
	public Developer()
	{		
	}
	public Developer(String name, int nip)
	{
		super();
		this.name = name;
		this.nip = nip;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNip()
	{
		return nip;
	}
	public void setNip(int nip)
	{
		this.nip = nip;
	}
}