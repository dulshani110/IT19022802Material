package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Material {
	
	//create the connection method
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/supplier", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	public String insertMaterial(String code, String name, String price, String desc) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = " insert into supplier(`MaterialID`,`MaterialCode`,`MaterialName`,`MaterialPrice`,`MaterialDesc`)"
			+ " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, code); 
			 preparedStmt.setString(3, name); 
			 preparedStmt.setDouble(4, Double.parseDouble(price)); 
			 preparedStmt.setString(5, desc); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newMaterials = readMaterials(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newMaterials + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Material.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
	
	public String readMaterials()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Material Code</th>" 
	 + "<th>Material Name</th><th>Material Price</th>"
	 + "<th>Material Description</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from supplier"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String MaterialID = Integer.toString(rs.getInt("MaterialID")); 
	 String MaterialCode = rs.getString("MaterialCode"); 
	 String MaterialName = rs.getString("MaterialName"); 
	 String MaterialPrice = Double.toString(rs.getDouble("MaterialPrice")); 
	 String MaterialDesc = rs.getString("MaterialDesc");
	 
	// Add into the html table
	 output += "<tr><td>" + MaterialCode + "</td>"; 
	 output += "<td>" + MaterialName + "</td>"; 
	 output += "<td>" + MaterialPrice + "</td>"; 
	 output += "<td>" + MaterialDesc + "</td>"; 
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-Materialid='" + MaterialID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-Materialid='" + MaterialID + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the Materials."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

	
	
	
	//create the delete method
	public String deleteMaterial(String MaterialID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from supplier where MaterialID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(MaterialID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newMaterials = readMaterials(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newMaterials + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Material.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	//update the Materials data
	public String updateMaterial(String ID, String code, String name, 
			 String price, String desc) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE supplier SET MaterialCode=?,MaterialName=?,MaterialPrice=?,MaterialDesc=? WHERE MaterialID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setDouble(3, Double.parseDouble(price)); 
			 preparedStmt.setString(4, desc); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newMaterials = readMaterials(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newMaterials + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the Material.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
}
