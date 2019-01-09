package com.fczyz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name="developer.all",query="Select p from Developer p"),
	@NamedQuery(name="developer.name",query="Select p from Developer p where p.name = :name")
})

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