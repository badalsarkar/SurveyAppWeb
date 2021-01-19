package com.project.survey;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Book;
import com.project.dao.SurveyCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class GetSurvey
 */
@WebServlet("/GetSurvey")
public class GetSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get parameter
		int id = Integer.parseInt(request.getParameter("surveyId"));
		
		// Get datasource and database connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
		Book survey = SurveyCRUD.findById(connection, id);
		datasource.returnConnection(connection);
		if(survey !=null) {
			request.setAttribute("survey", survey);
		}
		else {
			request.setAttribute("error", "No survey found with the id");
		}
		//pass control to question
		request.getRequestDispatcher("/GetQuestions").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
