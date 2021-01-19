package com.project.beans;

/*
 * Stores statistic about an answer
 */
public class AnswerStatistic {
	private int surveyId;
	private int questionId;
	private int answerId;
	private int noOfAnswer;
	private double asPercent;
	
	public AnswerStatistic(int surveyId, int questionId, int answerId) {
		this.surveyId = surveyId;
		this.questionId = questionId;
		this.answerId = answerId;
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
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getNoOfAnswer() {
		return noOfAnswer;
	}
	public void setNoOfAnswer(int noOfAnswer) {
		this.noOfAnswer = noOfAnswer;
	}
	
	public double getAsPercent() {
		return asPercent;
	}
	public void setAsPercent(int total) {
		if(this.noOfAnswer==0 || total ==0) {
			this.asPercent=0.0;
		}
		else {
			this.asPercent = (double)(this.noOfAnswer/(double)total);
		}
	}
}
