<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Hashtable"%>
<%@page import="com.project.beans.AnswerStatistic"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.project.beans.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.project.beans.Question"%>
<%@page import="com.project.beans.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
  integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
  crossorigin="anonymous">
</head>



<body>
  <%@include file="/jsp/Header.jsp"%>
  <%
  	Book survey = (Book) request.getAttribute("survey");
    	ArrayList<Question> questions = (ArrayList) request.getAttribute("questions");
    	ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
    	if (answers == null) {
  %>

  <div class="alert alert-info" role="alert">Sorry! This survey
    has no question yet. Try again later</div>
  <%
  	} else {
  		if (questions != null) {
  %>



  <h4 class="text-center"><%=survey.getSurveyName() %> Summary</h4>
  <%-- <h4><%=survey.getSurveyName() %></h4>--%>
  <table class="table table-striped w-auto mw-100 mx-auto">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Question</th>
        <%
        	int answerCount = 0;
        			ListIterator<Answer> it = answers.listIterator();
        			while (it.hasNext()) {
        				Answer answer = it.next();
        				answerCount++;
        %>
        <th scope="col"><%=answer.getAnswerText()%></th>
        <%
        	}
        %>
      </tr>
    </thead>
    <%-- End of table header --%>




    <%-- table body --%>
    <tbody>
      <%
        // Loop through question collection and print question
      	int count = 0;
      	ListIterator<Question> itQ = questions.listIterator();
      	while (itQ.hasNext()) {
      	 Question question = itQ.next();
         count++;
      %>
      <tr>
        <td><%=count %></td>
        <td><%=question.getQuestion()%></td>
        <%
            ArrayList<AnswerStatistic> surveyStatistic= (ArrayList)request.getAttribute("surveyStatistic");
            AnswerStatistic as = null;
            Hashtable<Integer, AnswerStatistic> answer= new Hashtable<>();
            for(AnswerStatistic a: surveyStatistic){
              if(a.getQuestionId()==question.getQuestionId()){
                answer.put(a.getAnswerId(), a);
              }
            }
            it=answers.listIterator();
            while(it.hasNext()){
              Answer ans=it.next();
              //System.out.println("The has is null" +(answer.get(ans.getAnswerId())==null));
              if(answer.get(ans.getAnswerId())==null){ %>
                  <td scope="col">
                  <div>
                    <div class="d-inline p-2 bg-primary text-white w-75 h-75">0</div>
                    <div class="d-inline p-2 bg-success text-white w-75 h-75">0</div>
                  </div>
                </td>
              <%}else{
                NumberFormat format = NumberFormat.getPercentInstance();
                
               %>
              <td scope="col">
                  <div>
                  <div class="d-inline p-2 bg-primary text-white w-75 h-75"><%=answer.get(ans.getAnswerId()).getNoOfAnswer() %></div>
                  <div class="d-inline p-2 bg-success text-white w-75 h-75"><%=format.format(answer.get(ans.getAnswerId()).getAsPercent()) %></div>
                </div>
              </td>         	  
       <%
              }}
        			}
        %>
      </tr>
    </tbody>
  </table>

  <%
  	} else {
  %>
  <div class="alert alert-info" role="alert">Sorry! This survey
    has no question yet. Try again later</div>
  <%
  	}
  	}
  %>




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