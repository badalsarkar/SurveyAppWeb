package com.project.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.annotation.Resource;
import javax.sql.DataSource;

/*
 * Encapsulates database connectivity functions
 */
public class SurveyDataSource {
	private Context context;
	private DataSource dataSource;
	
	/*
	 * Constructor
	 */
	public SurveyDataSource() {
		try {
			// Retrieve datasource from JNDI
			this.context = new InitialContext();
			this.dataSource = (DataSource) this.context.lookup("java:/comp/env/jdbc/Survey");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns a connection from connection pool
	 */
	public Connection getConnection() {
		Connection connection=null;
		try {
			connection=this.dataSource.getConnection();
			System.out.println("From SurveyDataSource: "+"Connection received");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	/*
	 * Close database connection
	 */
	public void returnConnection(Connection connection) {
		try {
			connection.close();
			System.out.println("From SurveyDataSource: "+"Connection returned");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Close context
	 */
	public void closeContext() {
		try {
			context.close();
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
}