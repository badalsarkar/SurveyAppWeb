package com.project.survey;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.project.beans.Book;
import com.project.dao.SurveyCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class CreateSurvey
 */
@WebServlet("/CreateSurvey")
public class CreateSurvey extends HttpServlet {
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get request parameters
		int id = Integer.parseInt(request.getParameter("id"));
		String surveyName= request.getParameter("surveyName");
		
		//get database connection
		SurveyDataSource dataSource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = dataSource.getConnection();

		// construct new survey and save to database
		Book survey = new Book(id, surveyName);
		Integer surveyCreated =SurveyCRUD.create(connection, survey);
		dataSource.returnConnection(connection);
		
		request.setAttribute("result", surveyCreated);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
		rd.forward(request, response);
	}

}
