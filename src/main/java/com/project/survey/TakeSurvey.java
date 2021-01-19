package com.project.survey;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Answer;
import com.project.beans.Question;
import com.project.beans.Book;
import com.project.dao.AnswerCRUD;
import com.project.dao.QuestionCRUD;
import com.project.dao.SurveyCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class TakeSurvey
 */
@WebServlet("/TakeSurvey")
public class TakeSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// extract parameters
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		ArrayList<Question> allQuestions =null;
		ArrayList<Answer> allAnswers = null;

		// get data source and connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();
	
		// get survey
		Book survey = SurveyCRUD.findById(connection, surveyId);
		
		// get all questions
		allQuestions= QuestionCRUD.getAllQuestions(connection, surveyId);
		
		//get all answers
		allAnswers = AnswerCRUD.getAllanswers(connection, surveyId);
		datasource.returnConnection(connection);
		
		request.setAttribute("survey", survey);
		request.setAttribute("questions", allQuestions);
		request.setAttribute("answers", allAnswers);
		request.getRequestDispatcher("/jsp/Questions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
