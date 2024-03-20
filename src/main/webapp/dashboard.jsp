<%@ page import="java.util.List" %>
<%@ page import="DBhandling.dao.MySQLItemDAO" %>
<%@ page import="DBhandling.ConnectionManager" %>
<%@ page import="DBhandling.entity.Item" %>
<%@ page import="DBhandling.entity.Order" %>
<%@ page import="DBhandling.entity.OrderItem" %>
<%@ pageimport="DBhandling.service.ServiceFacade" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }
        h1 {
            text-align: center;
            color: #007bff;
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
        .add-to-cart-button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }
        .search-container {
            margin-top: 20px;
            text-align: center;
        }
        .search-input {
            padding: 10px;
            width: 300px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-right: 10px;
        }
        .search-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .dialog-container {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            z-index: 1000;
        }
        .dialog-buttons {
            text-align: center;
            margin-top: 20px;
        }
        .buy-now-button {
            background-color: #007bff; /* Blue */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .buy-now-button:hover {
            background-color: #0056b3; /* Darker blue on hover */
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
    <h1>Items</h1>
    <div class="search-container">
        <input type="text" id="categoryInput" class="search-input" placeholder="Search by category">
        <button class="search-button" onclick="searchByCategory()">Search</button>
    </div>
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Availability</th>
            <th>Action</th> <!-- New column for the button -->
        </tr>
        <%
            MySQLItemDAO inst = new MySQLItemDAO(ConnectionManager.getConnection());
            List<Item> items = inst.getAllItems();

            for (Item item : items) {
        %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getCategory() %></td>
                <td><%= item.getStock() > 0 ? "Available" : "Out of Stock" %></td>
                <td>
                    <button class="add-to-cart-button" onclick="addToCart('<%= item.getName() %>')">Add to Cart</button>
                    <button class="buy-now-button" onclick="buyNow('<%= item.getId() %>', '<%= request.getSession().getAttribute("customerId") %>', '<%= item.getName() %>' )">Buy Now</button>


                </td>
            </tr>
        <%
            }
        %>
    </table>
    <!-- Button or link to navigate to Orders page -->
<div style="text-align: center; margin-top: 20px;">
        <button onclick="navigateToOrders('<%= request.getSession().getAttribute("customerId") %>')" style="background-color: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; cursor: pointer;">
            View Orders
        </button>
    </div>
    <div class="dialog-container" id="confirmationDialog">
        <h2>Confirm Purchase</h2>
        <p>Are you sure you want to buy this item?</p>
        <div class="dialog-buttons">
            <button onclick="buyNowConfirmed()">Yes</button>
            <button onclick="closeDialog()">No</button>
        </div>
    </div>

    <!-- JavaScript function to handle adding to cart and searching by category -->
    <script>
        function addToCart(itemName) {
            alert(itemName + " added to cart!");
            // Here you can add further logic to add the item to the cart
        }

        function searchByCategory() {
            var category = document.getElementById("categoryInput").value.trim();
            // Redirect to product_search.jsp with search query parameter
            window.location.href = "product_search.jsp?category=" + encodeURIComponent(category);
        }


        function buyNow(itemId, custId, itemName) {
            console.log("custId: "+custId);
            var confirmation = confirm("Are you sure you want to buy " + itemName + "?");
            if (confirmation) {
                // Construct the URL with parameters
                var url = "purchase_confirmation.jsp?itemId=" + encodeURIComponent(itemId) + "&custId=" + encodeURIComponent(custId) + "&itemName=" + encodeURIComponent(itemName);
                // Redirect the user to the confirmation page
                window.location.href = url;
            } else {
                // Do nothing if user cancels
            }
        }


        function buyNowConfirmed() {
            // This function is called when user confirms purchase on the confirmation page
            // Here you can invoke Java methods or perform any other necessary actions
            // Example:
            // Perform Java method invocation using AJAX or submit a form to a servlet
            alert("Item purchased successfully!");
        }

        function navigateToOrders(custId){
            console.log("cust: "+custId);
            var url = "Orders.jsp?custId=" + encodeURIComponent(custId);
        
        // Redirect the user to the confirmation page
        window.location.href = url;
        }
    </script>
</body>
</html>
