<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Amount</title>
    <style>
    	body {
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

header {
    background-color: grey;
    color: white;
    text-align: center;
    padding: 5px;
}

section {
    max-width: 400px;
    margin: 20px auto;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
}

form {
    display: flex;
    flex-direction: column;
}

label {
    margin-bottom: 8px;
}

input {
    padding: 8px;
    margin-bottom: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    background-color: grey;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
h4{
	color: green;	
}
button:hover {
    background-color: #BEBEBE;
}

footer {
    text-align: center;
    margin-top: 20px;
}

    	
    </style>
</head>
<body>

    <header>
        <h1>Check Balance</h1>
    </header>

    <section>
        <form action="admin?action=checkBalance" method="post">
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber" required><br>

            <button type="submit">Submit</button>
        </form>
    </section>

    <footer>
        <button onclick="redirectForm()">Back</button>
        <h4><c:out value="<%=request.getAttribute(\"closingbal\")%>" /></h4>
    </footer>
	<script>
	function redirectForm(){
		window.location.href = "admin-dashboard.jsp";
	}
</script>
</body>
</html>