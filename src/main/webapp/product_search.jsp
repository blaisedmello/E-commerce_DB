<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DBhandling.dao.MySQLInventoryDAO" %>
<%@ page import="DBhandling.ConnectionManager" %>
<%@ page import="DBhandling.entity.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Product Search Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #007bff;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
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
        <h1>Product Search Result</h1>
        <div>
            <p>You searched for: <%= request.getParameter("category") %></p>
        </div>
        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Category</th>
                <th>Availability</th>
            </tr>
            <%
                String category = request.getParameter("category");
                MySQLInventoryDAO inst = new MySQLInventoryDAO(ConnectionManager.getConnection());
                List<Item> items = inst.getItemByCategory(category);
                
                for (Item item : items) {
            %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getPrice() %></td>
                    <td><%= item.getCategory() %></td>
                    <td><%= item.getStock() %></td>
                </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
