package com.project.survey;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Question;
import com.project.dao.QuestionCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/GetQuestions")
public class GetQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Question> allQuestions = new ArrayList<>();
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		
		// Get datasource and database connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
		
		// Get all questions for the survey
		allQuestions= QuestionCRUD.getAllQuestions(connection, surveyId);
		datasource.returnConnection(connection);
		if(!allQuestions.isEmpty()) {
			request.setAttribute("questions", allQuestions);
		}
		request.getRequestDispatcher("/jsp/SurveyAdministration.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
