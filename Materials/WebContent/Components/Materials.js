/**
 * 
 */

$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateMaterialForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidMaterialIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "MaterialsAPI", 
 type : type, 
 data : $("#formMaterial").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onMaterialSaveComplete(response.responseText, status); 
 } 
 }); 
});


function onMaterialSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divMaterialsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 

$("#hidMaterialIDSave").val(""); 
$("#formMaterial")[0].reset(); 
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidMaterialIDSave").val($(this).data("Materialid")); 
		 $("#MaterialCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#MaterialName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#MaterialPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#MaterialDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
		});
//delete

$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "MaterialsAPI", 
		 type : "DELETE", 
		 data : "MaterialID=" + $(this).data("Materialid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onMaterialDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
function onMaterialDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divMaterialsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
// CLIENT-MODEL================================================================
function validateMaterialForm() 
{ 
// CODE
if ($("#MaterialCode").val().trim() == "") 
 { 
 return "Insert Material Code."; 
 } 
// NAME
if ($("#MaterialName").val().trim() == "") 
 { 
 return "Insert Material Name."; 
 } 
// PRICE-------------------------------
if ($("#MaterialPrice").val().trim() == "") 
 { 
 return "Insert Material Price."; 
 } 
// is numerical value
var tmpPrice = $("#MaterialPrice").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Material Price."; 
 } 
// convert to decimal price
 $("#MaterialPrice").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#MaterialDesc").val().trim() == "") 
 { 
 return "Insert Material Description."; 
 } 
return true; 
}
