package com.fczyz.jdbcdemo.domain;

public class Review {
	private long id;
	
	private String review;


	
	public Review()
	{		
	}
	public Review(String review)
	{
		super();
		this.review = review;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

}