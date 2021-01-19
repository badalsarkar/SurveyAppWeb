package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;

import com.project.beans.AnswerStatistic;

/*
 * Encapsulates database operation on Statistic table
 */
public class StatisticCRUD {
	
	/*
	 * Get the whole statistic table
	 */
	public static ArrayList<AnswerStatistic> getSurveyStatistic(Connection connection, int surveyId){
		ArrayList<AnswerStatistic> surveyStatistic= new ArrayList<>();
		ResultSet resultSet=null;
		String sql = "select * from Statistic";
		try(Statement statement= connection.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				AnswerStatistic answerStatistic = new AnswerStatistic(resultSet.getInt("survey_id"), resultSet.getInt("question_id"), resultSet.getInt("answer_id"));
				answerStatistic.setNoOfAnswer(resultSet.getInt("number_of_answer"));
				surveyStatistic.add(answerStatistic);
			}
			resultSet.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return surveyStatistic;
	}
	
	/*
	 * Get a summary of total answers for each question for a given survey
	 */
	public static Hashtable<Integer, Integer> getAnswerSummaryPerQuestion(Connection connection, int surveyId){
		Hashtable<Integer, Integer> answerSummary = new Hashtable<Integer, Integer>();
		String sql = "select question_id, sum(number_of_answer) from Statistic where survey_id="+surveyId+" group by question_id";
		ResultSet resultSet = null;
		try(Statement statement = connection.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				answerSummary.put(resultSet.getInt("question_id"), resultSet.getInt("sum(number_of_answer)"));
			}
			resultSet.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return answerSummary;
	}
	
	
	/*
	 * Increment the field value of number_of_answer by 1
	 */
	private static int updateNoOfAnswer(Connection connection, int surveyId, int questionId, int answerId) {
		String sql = "insert into Statistic (survey_id, question_id, answer_id, number_of_answer) values (?,?,?,1)"+
					"on duplicate key update number_of_answer = number_of_answer+1";
		int result=0;
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, surveyId);
			statement.setInt(2, questionId);
			statement.setInt(3, answerId);
			result =statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	return result;
	}
	
	/*
	 * Increment the field value of number_of_answer by 1 for multiple rows
	 */
	public static boolean updateAnswerStatistic(Connection connection, ArrayList<AnswerStatistic> answerStatistic) {
		ListIterator<AnswerStatistic> it = answerStatistic.listIterator();
		int rowsAffected=0;
		boolean updateStatus=true;
		while(it.hasNext()) {
			AnswerStatistic as = it.next();
			rowsAffected=updateNoOfAnswer(connection, as.getSurveyId(), as.getQuestionId(), as.getAnswerId());
			if(rowsAffected==0) {
				updateStatus = false;
				return updateStatus;
			}
		}
		return updateStatus;
	}

}
