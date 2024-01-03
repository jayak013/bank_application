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
        <h1><c:out value="<%=session.getAttribute("custName")%>"/></h1>
        <h1><c:out value="<%=session.getAttribute("custAcc")%>"/></h1>
        <h1><c:out value="<%=session.getAttribute("custMobile")%>"/></h1>
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