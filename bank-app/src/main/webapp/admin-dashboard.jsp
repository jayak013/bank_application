<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<jsp:useBean id="service" class="com.zm.bankapp.service.CustomerServiceImpl"/>
<jsp:useBean id="customer" class="com.zm.bankapp.dto.Customer"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="dashboard-style.css"> 
</head>

<body>
    <header>
        <h1>Welcome To Admin Dashboard </h1>
        <h3><c:out value="<%=session.getAttribute(\"adminName\")%>"/></h3>
        <h3><c:out value="<%=session.getAttribute(\"adminEmail\")%>"/></h3>
        <a href="login.jsp?action=logout" 	class="logout-button">Logout</a>
    </header>

    <main>
        <div class="app-grid">
            <a href="create-account.jsp" class="app-link">Create Account</a>
            <a href="deposit.jsp" class="app-link">Deposit</a>
            <a href="withdraw.jsp" class="app-link">Withdraw</a>
            <a href="transfer.jsp" class="app-link">Transfer</a>
            <a href="check-balance.jsp" class="app-link">Check Balance</a>
            <a href="transaction-history.html" class="app-link">Transaction History</a>
        </div>
    </main>
</body>
</html>
<%request.setAttribute("login", "admin");
%>