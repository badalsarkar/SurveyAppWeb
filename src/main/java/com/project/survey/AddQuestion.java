package com.project.survey;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Question;
import com.project.dao.QuestionCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Extract parameters
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String questionText = request.getParameter("question");
				
		// Get datasource and database connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
		
		// Create question
		Question question = new Question(surveyId, questionId, questionText);
		
		// Save operation
		int result = QuestionCRUD.add(connection, question);
		datasource.returnConnection(connection);
		if(result ==1) {
			request.setAttribute("message", "Question added successfully");
		}
		else {
			request.setAttribute("message", "Some error occured, question not added");
		}
		request.getRequestDispatcher("/GetSurvey").forward(request, response);
	}

}
