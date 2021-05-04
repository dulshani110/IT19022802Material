<%@page import="com.Material"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Materials Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Materials.js"></script>
</head>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Material Management</h1>
<form id="formMaterial" name="formMaterial">
 Material code: 
     <input id="MaterialCode" name="MaterialCode" type="text" class="form-control form-control-sm">
 <br> 
 Material name: 
     <input id="MaterialName" name="MaterialName" type="text" class="form-control form-control-sm">
 <br> 
 Material price: 
     <input id="MaterialPrice" name="MaterialPrice" type="text" class="form-control form-control-sm">
 <br> 
 Material description: 
 <input id="MaterialDesc" name="MaterialDesc" type="text" class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 <input type="hidden" id="hidMaterialIDSave" name="hidMaterialIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divMaterialsGrid">
 <%
 Material MaterialObj = new Material(); 
 out.print(MaterialObj.readMaterials()); 
 %>
</div>
</div> </div> </div> 
</body>

</html>