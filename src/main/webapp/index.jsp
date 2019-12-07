<!DOCTYPE html>
<html lang="en">
<head>
<title>QALD Application</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/question-answering-semantics/css/style_admin.css">
<link rel="stylesheet" href="/question-answering-semantics/css/style_common.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<body bgcolor="white">

	<header class="header">
		<nav class="navbar navbar-inverse" style="margin-bottom:0px !important; ">
  			<div class="container-fluid">
    			<div class="navbar-header">
    				<a class="navbar-brand titleText" href="#">QALD Application</a>
    			</div>
			</div>
		</nav>
	</header>
	<header>
		<h3 style="color: black" align="center">Enter your Question:</h3>
	</header>
	<form id="myForm" action="query.action" method="post">
		<div class="container">
			<hr>
			<input type="text" placeholder="Question" name="input" required/> <br/>
		</div>

		<div class="clearfix" style="margin-left: 25%;">
			<button type="button" class="cancelbtn" onclick="cancelFunction()">Cancel</button>
			<button class="signupbtn" onclick="submitFunction()">Submit</button>
		</div>
	</form>
	<section style="display:inline-block; text-align:center; margin-left:23%">
                <div id="message" class="alert alert-info display-none"></div>
                <div class="table-users" id="result"></div>
    </section>
<script>

    function myFunction() {
		alert("Sign up complete!");
	}

	$(document).ready(function() {
		var message = "${message}";
		if (message) {
			alert(message);
		}
	});
	function submitFunction() {
		document.getElementById("myForm").submit();
	}

	function cancelFunction() {
		window.location = "/arithmetic-evaluator/login_page.action";
	}
</script>
</body>
</html>