package com.project.beans;

/*
 * Stores information about a question
 */
public class Question {
	private int surveyId;
	private int questionId;
	private String question;
	
	/*
	 * Constructor
	 */
	
	public Question(int surveyId, int questionId, String question) {
		super();
		this.surveyId = surveyId;
		this.questionId = questionId;
		this.question = question;
	}
	
	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
