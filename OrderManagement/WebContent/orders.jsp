<%@page import= "com.Order" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/orders.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Order Management </h1>
<form id="formOrder" name="formOrder" method="post" action="orders.jsp">
 Cart ID: 
<input id="cartid" name="cartid" type="text" 
 class="form-control form-control-sm">
<br> Order Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Address: 
<input id="address" name="address" type="text" 
 class="form-control form-control-sm">
<br> Email: 
<input id="email" name="email" type="text" 
 class="form-control form-control-sm">
<br> Phone: 
<input id="phone" name="phone" type="text" 
 class="form-control form-control-sm">
<br> Total: 
<input id="total" name="total" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidIdSave" name="hidIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divOrdersGrid">

<%
 Order orderObj = new Order(); 
 out.print(orderObj.readOrders()); 
%>
</div>

</div></div></div>

</body>
</html>