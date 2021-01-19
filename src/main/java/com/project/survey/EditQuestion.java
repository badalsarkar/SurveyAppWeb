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
 * Servlet implementation class EditQuestion
 */
@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		int questionId= Integer.parseInt(request.getParameter("questionId"));
		String questionText = request.getParameter("question");
		
		// Instantiate Question object
		Question question = new Question(surveyId, questionId, questionText);
		
		// Get datasource and database connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
		int result = QuestionCRUD.updateOne(connection, question);
		String message = result==1?"Question successfully updated":"Some error occured";
		datasource.returnConnection(connection);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/GetSurvey").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
