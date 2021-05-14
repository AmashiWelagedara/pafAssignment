package com;

import java.sql.*; 
public class Order 
{ //Connection to the DB

private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //DB access denied 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orders", "root", "2220"); 
 } 
    catch (Exception e) 
    {e.printStackTrace();} 
 
    return con; 
 } 


public String insertOrder (String cartid, String name, String address, String email, String phone, String total) 
{ 
  String output = ""; 

try
{ 
	 
   Connection con = connect(); 
   
   if (con == null) 
   {return "Error while connecting to the database for inserting."; } 

   // create a prepared statement
   String query = " INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)"; 

   PreparedStatement preparedStmt = con.prepareStatement(query); 

   // binding values
   preparedStmt.setInt(1, 0); 
   preparedStmt.setString(2, cartid); 
   preparedStmt.setString(3, name); 
   preparedStmt.setString(4, address);
   preparedStmt.setString(5, email);
   preparedStmt.setString(6, phone);
   preparedStmt.setString(7, total);
   
   // execute the statement
   preparedStmt.execute(); 
   con.close(); 
    String newOrders = readOrders();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrders + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

public String readOrders() 
{ 
   String output = ""; 
   
   try
  { 
     Connection con = connect(); 
 
     if (con == null) 
     {return "Error while connecting to the database for reading."; } 
 
     // Prepare the HTML table to be displayed
     output = "<table border='1'><tr><th>Cart ID</th><th>Order Name</th>" +
              "<th>Address</th>" + 
              "<th>Email</th>" +
              "<th>Phone</th>" +
              "<th>Total</th>" +
              "<th>Update</th><th>Remove</th></tr>"; 
 
   String query = "select * from orders"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
 
   // iterate through the rows in the result set
  while (rs.next()) 
 { 
      String orderid = Integer.toString(rs.getInt("orderid")); 
      String cartid = rs.getString("cartid"); 
      String name = rs.getString("name"); 
      String address = rs.getString("address");
      String email = rs.getString("email");
      String phone = rs.getString("phone");
      String total = rs.getString("total");
       
   // Add a row into the HTML table
		 output += "<tr><td><input id='hidIdUpdate' name='hidIdUpdate' type='hidden' value='" + orderid + "'>"
				 + cartid + "</td>";
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + address + "</td>"; 
		 output += "<td>" + email + "</td>";
		 output += "<td>" + phone + "</td>";
		 output += "<td>" + total + "</td>";
		 		
   // buttons
		 output += "<td><input name='btnUpdate' " 
		 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-oid='" + orderid + "'></td>"
		 + "<td><form method='post' action='orders.jsp'>"
		 + "<input name='btnRemove' " 
		 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-oid='" + orderid + "'>"
		 + "<input name='hidIdDelete' type='hidden' " 
		 + " value='" + orderid + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the HTML table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the orders."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

public String updateOrder(String ID, String cartid, String name, String address, String email, String phone, String total)
 { 
   
	String output = ""; 
 
	try
   { 
      Connection con = connect(); 
 
      if (con == null) 
      {return "Error while connecting to the database for updating."; } 
 
     // create a prepared statement
     String query = "UPDATE orders SET cartid=?,name=?,address=?,email=?,phone=?,total=? WHERE orderid=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 
     // binding values
    preparedStmt.setString(1, cartid); 
    preparedStmt.setString(2, name); 
    preparedStmt.setString(3, address);
    preparedStmt.setString(4, email);
    preparedStmt.setString(5, phone);
    preparedStmt.setString(6, total);
    preparedStmt.setInt(7, Integer.parseInt(ID)); 
 
    // execute the statement
    preparedStmt.execute(); 
    con.close();
	String newOrders = readOrders();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrders + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the order.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


public String deleteOrder(String orderid) 
 { 
 String output = ""; 
 
 try
 { 
    Connection con = connect(); 
    if (con == null) 
    {return "Error while connecting to the database for deleting."; } 
 
    // create a prepared statement
    String query = "delete from orders where orderid=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
 
    // binding values
    preparedStmt.setInt(1, Integer.parseInt(orderid)); 
 
 // execute the statement
 	 preparedStmt.execute(); 
 	 con.close(); 
 	 String newOrders = readOrders();
 	 output =  "{\"status\":\"success\", \"data\": \"" + 
 			 newOrders + "\"}"; 
 	 } 

 	catch (Exception e) 
 	 { 
 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the order.\"}";  
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
 		}








} 