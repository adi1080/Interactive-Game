<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game Stories</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7"
	crossorigin="anonymous">
<link rel="stylesheet" href="CSS/AllStories.css" />
<script type="text/javascript">
    function ContinueStory(){
    	document.functional_buttons_form.action = "/continueStory";
    	document.functional_buttons_form.submit();
    }
    
    function deleteStory(){
    	document.functional_buttons_form.action = "/delete";
    	document.functional_buttons_form.submit();
    }
</script>

</head>
<body>
	<h3>Previous Stories</h3>
<form name = "functional_buttons_form">
		<table class="table table-success table-striped-columns"
			style="font-size: small" border="2">
			<thead>
				<tr>
				    <th>Select</th>
					<th style="text-align: center;">Chat History</th>
					<th>Last Updated</th>
                    <th colspan=2>Options</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="s">
					<tr>
					    <td><input type="radio" name = "sessionId" value="${s.sessionId}"></td>
						<td>${s.chatHistory}</td>
						<td>${fn:replace(fn:substringBefore(s.lastUpdated, "."), "T", " ")}</td>
						<td>
						  <button class = "btn btn-outline-warning" onclick="ContinueStory()">Continue</button>
						  <button class = "btn btn-outline-danger" onclick="deleteStory()">Remove</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
</body>
</html>