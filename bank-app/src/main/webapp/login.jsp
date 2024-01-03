<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet" href="login-style.css">
</head>
<body>
	<%if(session.getAttribute("page")==null)session.setAttribute("page", request.getParameter("action")); %>
	<div class="login-container">
		<h1>Login</h1>	
		<form class="login-form" id="loginForm" action="login" method="post">
			<div class="form-group">
				<h2>
					<label>Username</label> <input type="text"
						name="username" required>
				</h2>
			</div>
			<div class="form-group">
				<h2>
					<label>Password</label> <input type="password"
						name="password" required>
				</h2>
			</div>
			<div class="form-group">
				<button type="submit">Login</button>
				<button type="button" class="cancel" onclick="redirectForm()">Cancel</button>
			</div>
			<h2 style="color: red">
				<c:out value="<%=request.getAttribute(\"error\")%>" />
			</h2>
		</form>
	</div>

</body>
<script>
	function redirectForm() {
		window.location.href = "welcome.html";
	}
</script>
</html>

