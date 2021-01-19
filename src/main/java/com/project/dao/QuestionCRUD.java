package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.beans.Question;

/*
 * Encapsulates database operation on Question table
 */
public class QuestionCRUD {
	/*
	 * Insert question into Question table
	 */
	public static int add(Connection connection, Question question) {
		int result=0;
		String sql = "insert into Question (survey_id,question_id,question_text) values(?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, Integer.toString(question.getSurveyId()));
			statement.setString(2, Integer.toString(question.getQuestionId()));
			statement.setString(3, question.getQuestion());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Get all questions
	 */
	public static ArrayList<Question> getAllQuestions(Connection connection, int surveyId){
		String sql="select * from Question where survey_id="+surveyId;
		ArrayList<Question> allQuestions = new ArrayList<>();
		ResultSet resultSet= null;
		Statement statement = null;
		try {
			statement= connection.createStatement();
			resultSet = statement.executeQuery(sql);
			// read all the rows, create question object and add to ArrayList
			while(resultSet.next()) {
				Question question= new Question(resultSet.getInt("survey_id"), resultSet.getInt("question_id"), resultSet.getString("question_text"));
				allQuestions.add(question);
				
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allQuestions;
	}
	
	/*
	 * Update one question
	 */
	public static int updateOne(Connection connection, Question question) {
		String sql = "Update Question set question_text=? where survey_id="+question.getSurveyId()+" and question_id="+question.getQuestionId();
		System.out.println(sql);
		int result=0;
		try(PreparedStatement statement=connection.prepareStatement(sql);){
			statement.setString(1, question.getQuestion());
			result=statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Delete one question
	 */
	public static int deleteOne(Connection connection, int question_id) {
		String sql = "delete from Question where question_id="+question_id;
		int result=0;
		try(Statement statement = connection.createStatement();){
			result = statement.executeUpdate(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
