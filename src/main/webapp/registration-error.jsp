<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Error</title>
</head>
<body>
    <h2>Registration Error</h2>
    
    <%-- Display the error message received from the servlet --%>
    <p><%= request.getAttribute("errorMessage") %></p>
    
    <%-- You can also provide a link to navigate back to the registration page --%>
    <p><a href="register.jsp">Back to Registration Page</a></p>
</body>
</html>
