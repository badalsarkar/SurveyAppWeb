package com.project.survey;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Book;
import com.project.dao.SurveyCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class TakeSurvey
 */
@WebServlet("/Surveys")
public class Surveys extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Surveys() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> allSurveys = null;
		
		// Get datasource and database connection
		SurveyDataSource datasource = (SurveyDataSource)request.getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
		allSurveys = SurveyCRUD.getAllSurvey(connection);
		datasource.returnConnection(connection);
		request.setAttribute("allSurveys", allSurveys);
		request.getRequestDispatcher("/jsp/SurveyOptions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
