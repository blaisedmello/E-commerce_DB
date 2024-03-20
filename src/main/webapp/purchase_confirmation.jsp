<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="DBhandling.entity.Order" %>
<%@ page import="DBhandling.entity.OrderItem" %>
<%@ page import="DBhandling.service.ServiceFacade" %>
<%@ page import="DBhandling.ConnectionManager" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Purchase Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
        }
        h1 {
            color: #007bff;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .success-message {
            color: #4CAF50;
            font-weight: bold;
        }
        .error-message {
            color: #FF5722;
            font-weight: bold;
        }
        .company-name {
            font-size: 24px;
            font-weight: bold;
            color: #007bff; /* You can adjust the color to match your brand */
            text-align: center;
            margin-top: 20px; /* Adjust the margin as needed */
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="company-name">CartWise Online Store</div>
    </div>
    <div class="container">
        <h1>Purchase Confirmation</h1>
        <% 
            String itemIdString = request.getParameter("itemId");
            String custIdString = request.getParameter("custId");
            String itemName = request.getParameter("itemName");

            if (itemIdString != null && !itemIdString.isEmpty() &&
                custIdString != null && !custIdString.isEmpty() &&
                itemName != null && !itemName.isEmpty()) {
                
                int itemId = Integer.parseInt(itemIdString);
                int custId = Integer.parseInt(custIdString);

                OrderItem orderItem = new OrderItem(itemId, 1); // Assuming quantity is 1 for now
                List<OrderItem> orderItemList = new ArrayList<>();
                orderItemList.add(orderItem);

                Order order = new Order(custId, orderItemList, "2024-03-02"); // Replace with appropriate date

                ServiceFacade serviceFacade = new ServiceFacade(ConnectionManager.getConnection());
                serviceFacade.processOrder(order);
        %>
                <p class="success-message">Item <%= itemName %> purchased successfully!</p>
        <% } else { %>
                <p class="error-message">Error: Missing parameters for purchase confirmation.</p>
        <% } %>
    </div>
</body>
</html>
