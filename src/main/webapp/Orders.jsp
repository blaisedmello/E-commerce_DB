<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DBhandling.dao.MySQLOrderDAO" %>
<%@ page import="DBhandling.entity.Order" %>
<%@ page import="DBhandling.entity.OrderItem" %>
<%@ page import="DBhandling.entity.Item" %>
<%@ page import="DBhandling.ConnectionManager" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <style>
        .company-name {
            font-size: 24px;
            font-weight: bold;
            color: #007bff; /* You can adjust the color to match your brand */
            text-align: center;
            margin-top: 20px; /* Adjust the margin as needed */
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
        }
        h1 {
            color: #007bff;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="company-name">CartWise Online Store</div>
    </div>
    <h1>Order Details</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <% 
            int customerId = Integer.parseInt(request.getParameter("custId"));
            MySQLOrderDAO orderDAO = new MySQLOrderDAO(ConnectionManager.getConnection());
            List<String> orderItems = orderDAO.getOrderItemsByCustomerID(customerId);

            for (String orderItem : orderItems) {
                // Split the order item string into individual values
                String[] values = orderItem.split(",");
        %>
            <tr>
                <td><%= values[0].split(":")[1].trim() %></td> <!-- Extracting Order ID from the string -->
                <td><%= values[1].split(":")[1].trim() %></td> <!-- Extracting Item Name from the string -->
                <td>$<%= values[2].split(":")[1].trim() %></td> <!-- Extracting Price from the string -->
                <td><%= values[3].split(":")[1].trim() %></td> <!-- Extracting Quantity from the string -->
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>
