<%@page import="java.util.Iterator"%>
<%@page import="com.project.beans.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<title>Insert title here</title>
</head>

<body>
<div class="ml-5 mt-4">
	<%
		ArrayList<Question> allQuestions = (ArrayList)request.getAttribute("questions");
		if(allQuestions ==null || allQuestions.isEmpty()){
	%>
		<h6>There are no questions</h6>
	<%
		}
		else{
	%>
	<%
		Iterator<Question> it = allQuestions.iterator();
		int count=0;
		while(it.hasNext()){
			Question question = it.next();
			count++;
			
	%>
		<table class="table table-bordered table-sm w-75">
		  <tbody>
		    <tr>
		      <th scope="row"><%=count %></th>
		      <td class="w-75"><%=question.getQuestion() %></td>
		      <td>
			      <div class="ml-4">
			      	<%--<a href="EditQuestion?survey_id=<%=question.getSurveyId()%>&question_id=<%=question.getQuestionId()%>"><button class="btn-sm btn-primary">Edit</button></a> --%>
			      	<button data-toggle="modal" data-target="#question-modal" 
			      		data-questionid="<%=question.getQuestionId()%>"
			      		data-surveyid="<%=question.getSurveyId() %>"
			      		data-question="<%=question.getQuestion() %>" 
			      		class="btn btn-sm btn-success editquestion" >Edit</button>
					<a href="DeleteQuestion?questionId=<%=question.getQuestionId()%>&surveyId=<%=question.getSurveyId()%>"><button class="btn-sm btn-danger">Delete</button></a>
			      </div>
			  </td>
		    </tr>
		  </tbody>
		</table>
	<%}
	}%>
	
	
	
	
	<%-- Modal --%>
	<div class="modal fade" id="question-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
		      <form action="EditQuestion" method="POST">
				  <div class="form-group">
				    <label for="exampleInputEmail1">Existing Question</label>
				    <input type="number" class="form-control surveyId" name="surveyId" value="" hidden/>
				    <input type="number" class="form-control questionId" name="questionId" value="" hidden/>
				    <textarea name="question" cols="30" rows="10" class="form-control modal-text"></textarea>
				  </div>
				  <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button type="submit" class="btn btn-primary">Save changes</button>
			      </div>
			  </form>
		  </div>
		</div>
	  </div>
	</div>
 </div>
 
 
 
 
 
 
 <%--Script --%>
 <script type="text/javascript">
 	$(document).ready(function(){
 		$('.editquestion').click(function(){
 			var surveyId= $(this).data("surveyid");
 			var questionId = $(this).data("questionid");
 			var question = $(this).data("question");
 			$('.modal-text').html(question);
 			$('.questionId').val(questionId);
 			$('.surveyId').val(surveyId);
 		});
 	});
 </script>
 
 <!-- Bootstrap files -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>