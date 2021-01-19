package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.beans.Book;


/*
 * Encapsulates database operation on Survey table
 */
public class SurveyCRUD {


	/*
	 * Save a survey into database
	 */
	public static int create(Connection connection, Book survey) {
		int rowsAffected = 0;
		String insertQuery = "insert into Survey values(?,?)";
		PreparedStatement statement=null;
		try {
			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, Integer.toString(survey.getId()));
			statement.setString(2, survey.getSurveyName());
			// execute the statement
			rowsAffected = statement.executeUpdate();
		}
		catch(SQLException e) {
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
		return rowsAffected;
	}
	public void update() {}
	
	/*
	 * Get a survey by id
	 */
	public static Book findById(Connection connection, int id) {
		Book survey = null;
		String query = "select * from Survey where survey_id="+id;
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				survey = new Book(result.getInt("survey_id"), result.getString("survey_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return survey;
	}
	
	/*
	 * Get all survey
	 */
	public static ArrayList<Book> getAllSurvey(Connection connection){
		String sql = "select * from Survey";
		ArrayList<Book> allSurveys = new ArrayList<Book>();
		ResultSet resultSet = null;
		try(Statement statement = connection.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				allSurveys.add(new Book(resultSet.getInt("survey_id"), resultSet.getString("survey_name")));
			}
			resultSet.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return allSurveys;
	}
	public void delete() {}

}
