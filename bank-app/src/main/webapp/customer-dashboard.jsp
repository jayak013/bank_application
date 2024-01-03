<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="dashboard-style.css"> 
</head>
<body>
	<%if(session.getAttribute("uname")==null)
	session.setAttribute("uname", request.getParameter("username")); 
	%>
    <header>
        <h1>Welcome To Customer Dashboard </h1>
        <h1>Username: <c:out value="<%=session.getAttribute(\"uname\")%>"/></h1>
        <a href="login.jsp?action=logout" 	class="logout-button">Logout</a>
    </header>

    <main>
        <div class="app-grid">
            <a href="check-balance.jsp" class="app-link">Check Balance</a>
            <a href="transaction-history.jsp" class="app-link">Transaction History</a>
        </div>
    </main>
</body>
</html>
<%request.setAttribute("login", "customer");
%>