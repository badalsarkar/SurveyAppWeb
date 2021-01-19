package com.project.beans;

/*
 * Stores information about answer
 */
public class Answer {
	private int answerId;
	private int surveyId;
	private String answerText;

	public Answer(int answerId, int surveyId, String answerText) {
		this.answerId = answerId;
		this.surveyId = surveyId;
		this.answerText = answerText;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	

}
