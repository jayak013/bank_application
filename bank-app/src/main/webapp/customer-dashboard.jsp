<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="service" class="com.zm.bankapp.service.CustomerServiceImpl"/>
<jsp:useBean id="accountService" class="com.zm.bankapp.service.AccountServiceImpl"/>
<jsp:useBean id="customer" class="com.zm.bankapp.dto.Customer"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="dashboard-style.css"> 
</head>
<body>
	<%if(request.getParameter("username")!=null)customer =  service.getCustomerByUserName(request.getParameter("username"));%>
        <h1>Welcome To Customer Dashboard</h1>
        <h3><c:out value="<%=session.getAttribute(\"custName\")%>"/></h3>
        <h3><c:out value="<%=session.getAttribute(\"custAcc\")%>"/></h3>
        <h3><c:out value="<%=session.getAttribute(\"custMobile\")%>"/></h3>
    <header>
        <a href="login.jsp?action=logout" 	class="logout-button">Logout</a>
    </header>

    <main>
        <div class="app-grid">
            <a href="customer?action=check" class="app-link">Check Balance</a>
            <a href="customer?action=tx" class="app-link">Transaction History</a>
        </div>
        <h2><c:out value="<%=session.getAttribute(\"bal\")%>"/></h2>
    </main>
</body>
</html>
<%request.setAttribute("login", "customer");
%>