package com.project.beans;

/*
 * Stores information about survey
 */
public class Book {
	private int id;
	private String surveyName;
	
	public Book(int id, String surveyName) {
		this.id = id;
		this.surveyName = surveyName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	

}
