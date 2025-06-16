<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AI Dungeon Master</title>
<link rel="stylesheet" href="CSS/style.css" />

<script>
	function NewGame() {
		document.fn.action = "start_new_game";
		document.fn.submit();
	}

	function AllStories() {
		document.fn.action = "FindPreviousStories";
		document.fn.submit();
	}
	
	 function showLoading() {
		    document.getElementById("loadingSpinner").style.display = "block";
		  }
	
	window.onload = function() {
	    document.fn.addEventListener('submit', function() {
	      showLoading();
	    });
	  };
</script>
</head>
<body>
	<h1 style="color: white;">🧙AI-powered interactive text based
		story generator game⚔️</h1>
	<div class="container">

		<div id="chatbox">
			<h5 style="color: red;">Story So Far:</h5>
			<pre>${chatHistory}</pre>
		</div>

		<!-- User Input Form (Send) -->
		<form action="/message_response" method="get" class="input-form"
			name="fn">
			<input type="hidden" name="sessionId" value="${sessionId}" /> <input
				type="text" id="userInput" name="query"
				placeholder="Enter Your First/next action">
			<div class="button-group">
				<button id="sendBtn" type="submit">Send</button>
				<button onclick="NewGame()" id="newgame-button">Start New Game</button>
				<button onclick="AllStories()" id="previousStories-button">Previous Stories</button>
			</div>
		</form>
	</div>
<div id="loadingSpinner" style="display: none;">
  <img src="Images/spinner.gif" alt="Loading..." />
</div>
</body>
</html>










