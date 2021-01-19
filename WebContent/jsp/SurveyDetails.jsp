<%@page import="com.project.beans.Book"%>
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
	<%
		Book survey = (Book)request.getAttribute("survey");
			String error= (String)request.getAttribute("error");
			if(error !=null && error.length()>0){
	%>
		<div class="ml-5 mt-3">
	    	<div class="alert alert-danger w-50" role="alert">
  				<%=error %>
			</div>
	    </div>
	<%}else if(survey !=null){ %>
    <div class="ml-5 mt-3">
    	<span class="text-secondary">Survey Id: <%=survey.getId() %></span>
    	<span class="text-secondary ml-5">Survey Name: <%=survey.getSurveyName() %></span>
    </div>
    <div class="ml-5 mt-2">
        <form action="AddQuestion" method="Post">
         <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="surveyId" value="<%=survey.getId() %>" hidden>
        		<div class="input-group input-group-sm mb-3 w-25">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Question Id</span>
                  </div>
                  	<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="questionId" required>
                </div>
                <div class="input-group input-group-sm mb-3 w-50">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">New Question</span>
                  </div>
                 <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="question" required>
                
                 <button type="submit" class="btn btn-primary btn-sm ml-3">Add</button>
                </div>
        </form>
    </div>
    <div class="ml-5 mt-2">
   		<h6 class="border-bottom border-primary w-50 font-weight-normal">Existing Questions</h6>
   	</div>
   	<%
   		String message = (String)request.getAttribute("message");
   		if(message!=null){
   	%>
		<div class="ml-5 mt-3">
	    	<div class="alert alert-danger w-50" role="alert">
  				<span><%=message %></span>
			</div>
	    </div>   			
	 <%} %>
   	
   	<jsp:include page="/jsp/SurveyQuestions.jsp" />
   	<%} %>
   	
   	
   	
</div>
 <!-- Bootstrap files -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>