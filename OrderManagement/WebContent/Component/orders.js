$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateOrderForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "OrderAPI", 
		 type : type, 
		 data : $("#formOrder").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onOrderSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidIdSave").val($(this).data("oid"));
	$("#cartid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
	$("#total").val($(this).closest("tr").find('td:eq(5)').text());
});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "OrderAPI", 
	 type : "DELETE", 
	 data : "orderid=" + $(this).data("oid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onOrderDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
	// CLIENT-MODEL================================================================
	function validateOrderForm()
		{
		// Cart ID
		if ($("#cartid").val().trim() == "")
		{
		return "Insert Cart ID.";
		}
		// Order Name
		if ($("#name").val().trim() == "")
		{
		return "Insert Order Name.";
		}
		// is string value
		var tmpoName = $("#name").val().trim();
		if ($.isNumeric(tmpoName))
		{
		return "Order Name cannot be just a value.";
		}
		// Address
		if ($("#address").val().trim() == "")
		{
		return "Insert Address.";
		}
		// is string value
		var tmpadd = $("#address").val().trim();
		if ($.isNumeric(tmpadd))
		{
		return "Address cannot be just a value.";
		}
		// Email-------------------------------
		if ($("#email").val().trim() == "")
		{
		return "Insert Email.";
		}
		
		// Phone-------------------------------
		if ($("#phone").val().trim() == "")
		{
		return "Insert Phone.";
		}
		// is string value
		var tmpph = $("#phone").val().trim();
		if (!$.isNumeric(tmpph))
		{
		return "Phone Number cannot have letters.";
		}
		// Total-------------------------------
		if ($("#total").val().trim() == "")
		{
		return "Insert Total.";
		}
		// is string value
		var tmptot = $("#total").val().trim();
		if (!$.isNumeric(tmptot))
		{
		return "Total cannot have letters.";
		}
		return true;
	}
function onOrderSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divOrdersGrid").html(resultSet.data); 
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
	 $("#hidIdSave").val(""); 
	 $("#formOrder")[0].reset(); 
}

function onOrderDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divOrdersGrid").html(resultSet.data); 
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