package com.project.survey;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.beans.Answer;
import com.project.beans.AnswerStatistic;
import com.project.beans.Question;
import com.project.beans.Book;
import com.project.dao.AnswerCRUD;
import com.project.dao.QuestionCRUD;
import com.project.dao.StatisticCRUD;
import com.project.dao.SurveyCRUD;
import com.project.dao.SurveyDataSource;

/**
 * Servlet implementation class SubmitSurvey
 */
@WebServlet("/SubmitSurvey")
public class SubmitSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int surveyId=Integer.parseInt(request.getParameter("surveyId"));
		ArrayList<AnswerStatistic> allAnswers = new ArrayList<AnswerStatistic>();
		
		// Get all parameter name
		Enumeration<String> parameterNames = request.getParameterNames();
		int totalAnswer=0;
		// Loop through all parameters and create AnswerStatistic
		// for each answer
		while(parameterNames.hasMoreElements()) {
			String parName= parameterNames.nextElement();
			if(isInt(parName)) {
				totalAnswer++;
				int questionId = Integer.parseInt(parName);
				int answerId = Integer.parseInt(request.getParameter(parName));
				allAnswers.add(new AnswerStatistic(surveyId, questionId, answerId));
			}
		}
		
		// get datasource and connection
		SurveyDataSource datasource = (SurveyDataSource)getServletContext().getAttribute("datasource");
		Connection connection = datasource.getConnection();

		// Update answer statistic in database
		boolean updateStatus=StatisticCRUD.updateAnswerStatistic(connection, allAnswers);
		
		// get survey
		// TODO this should be saved in session
		Book survey = SurveyCRUD.findById(connection, surveyId);
		ArrayList<Question> questions = QuestionCRUD.getAllQuestions(connection, surveyId);
		ArrayList<Answer> answers = AnswerCRUD.getAllanswers(connection, surveyId);
		ArrayList<AnswerStatistic> surveyStatistic = StatisticCRUD.getSurveyStatistic(connection, surveyId);
		Hashtable<Integer, Integer> answerSummaryPerQuestion = StatisticCRUD.getAnswerSummaryPerQuestion(connection, surveyId);
		
		ListIterator<AnswerStatistic> it = surveyStatistic.listIterator();
		// Loop through answer statistic and calculate percentage
		while(it.hasNext()) {
			AnswerStatistic as = it.next();
			as.setAsPercent(answerSummaryPerQuestion.get(as.getQuestionId()));
		}
		
		// close database connection
		datasource.returnConnection(connection);
		
		if(updateStatus ==true) {
			request.setAttribute("survey", survey);
			request.setAttribute("questions", questions);
			request.setAttribute("answers", answers);
			request.setAttribute("surveyStatistic", surveyStatistic);
		}
		else {
			//error
			request.setAttribute("error", "Sorry, some error occured. Please try again later");
		}
		request.getRequestDispatcher("/jsp/Statistics.jsp").forward(request, response);
	}
	
	/*
	 * Check if a string is an integer
	 */
	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	


}
