<%@page import="java.util.ListIterator"%>
<%@page import="com.project.beans.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
  integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
  crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
  <%@include file="/jsp/Header.jsp"%>
  <div class="row row-cols-1 row-cols-md-3 mx-auto m-4">
    <%
    	List<Book> allSurveys = (List) request.getAttribute("allSurveys");
        	if (allSurveys == null) {
    %>
    <div class="alert alert-info" role="alert">Sorry, no surveys
      available</div>
    <%
    	} else {
        		ListIterator<Book> it = allSurveys.listIterator();
        		while (it.hasNext()) {
        			Book survey = it.next();
    %>
    <div class="col mb-4">
      <div class="card text-white bg-info mb-3"
        style="max-width: 18rem;">
        <div class="card-body">
          <h5 class="card-title"><%=survey.getSurveyName() %></h5>
          <p class="card-text">Employee satisfaction survey</p>
            <a href="TakeSurvey?surveyId=<%=survey.getId() %>" class="btn btn-primary">Participate</a>
        </div>
      </div>
    </div>

    <%
    	}
    	}
    %>
  </div>



  <!-- Bootstrap files -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
    integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
    crossorigin="anonymous"></script>
  <script
    src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
    crossorigin="anonymous"></script>

</body>
</html>