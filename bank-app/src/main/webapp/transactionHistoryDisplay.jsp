
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Transaction History</title>
    <link rel ="stylesheet"  href="transactionHistoryDisplay.css">
</head>
<body>
    <div class="center">
        <h2>Account Transaction History</h2>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Transaction Date</th>
                    <th>Transaction Type</th>
                    <th>Amount Transferred</th>
                    <th>Sender Account No</th>
                    <th>Receiver Account No</th>
                    <th>Closing Balance</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaction" items="${transList}">
                    <tr>
                        <td><c:out value="${transaction.txId}" /></td>
                        <td><c:out value="${transaction.txDate}" /></td>
                        <td><c:out value="${transaction.txType}" /></td>
                        <td><c:out value="${transaction.amount}" /></td>
                        <td><c:out value="${transaction.senderAccountNo}" /></td>
                        <td><c:out value="${transaction.receiverAccountNo}" /></td>
                        <td><c:out value="${transaction.closingBalance}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button onclick="redirectPage()">Go Back</button>
    </div>

    <script>
        function redirectPage() {
            window.location.href = "transaction-history.html";
        }
    </script>
</body>
</html>
