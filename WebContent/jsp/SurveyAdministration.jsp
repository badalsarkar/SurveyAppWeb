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
    <%@ include file="/jsp/Header.jsp" %>
    <h4 class="text-center">Survey Administration Page</h4>

    <%--Create Survey --%>
    
    <div class="ml-5 mt-5">
        <form action="CreateSurvey" method="POST">
                <h5 class="border-bottom border-primary w-50 font-weight-normal">Create Survey</h5>
                
                <div class="input-group input-group-sm mb-3 w-25">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Survey Id</span>
                  </div>
                  	<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="id" required>
                </div>
                
                <div class="input-group input-group-sm mb-3 w-25">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Survey Name</span>
                  </div>
                 	<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="surveyName" required>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary btn-sm">Create Survey</button>
                </div>
        </form>
        <%
			Integer surveyCreated= (Integer)request.getAttribute("result");
			if(surveyCreated !=null && surveyCreated == 1 ){
		%>
			<h6>Survey Successfully Created</h6>
		<%
			}
			%>
    </div>
	
	

    <%--Maintain Survey --%>
    
    <div class="ml-5 mt-5">
        <form action="GetSurvey" method="GET">
                <h5 class="border-bottom border-primary w-50 font-weight-normal">Maintain Survey</h5>
                <div class="input-group input-group-sm mb-3 w-25">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Survey Id</span>
                  </div>
                 <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="surveyId" required>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary btn-sm">Search Survey</button>
                </div>
        </form>
    </div>
    <jsp:include page="/jsp/SurveyDetails.jsp"></jsp:include>
   
	






	<!-- Bootstrap files -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
