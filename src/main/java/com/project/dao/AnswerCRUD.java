package com.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.beans.Answer;

/*
 * Encapsulates database operation on Answer table
 */
public class AnswerCRUD {
	
	/*
	 * Get all answers
	 */
	public static ArrayList<Answer> getAllanswers(Connection connection, int surveyId){
		String sql="select * from Answer where survey_id="+surveyId;
		ArrayList<Answer> allanswers = new ArrayList<>();
		ResultSet resultSet= null;
		try (Statement statement = connection.createStatement(); ResultSet resultset=null;){
			resultSet = statement.executeQuery(sql);
			// read all the rows, create Answer object and add to ArrayList
			while(resultSet.next()) {
				Answer answer= new Answer(resultSet.getInt("answer_id"), resultSet.getInt("survey_id"), resultSet.getString("answer_text"));
				allanswers.add(answer);
				
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allanswers;
	}

}
