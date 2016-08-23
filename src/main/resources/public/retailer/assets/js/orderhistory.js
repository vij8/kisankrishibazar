$(document).ready(function() {
	var details = $.parseJSON(localStorage.getItem('userdetail'));
    var response = $.parseJSON(details);
    var retailerusername = response.username;
	$.ajax({
		url : '/retailer/orderHistory',
		type : 'get',		
		data : {
			retailerusername : retailerusername
		},
		success : function(data, textStatus, jQxhr) {
			var response = $.parseJSON(data);
			var strVar="";
			for(i=0; i<response.length; i=i+1){			
				strVar += "<tr>";
				strVar += "<td>"+response[i].date+ "<\/td>";
				strVar += "<td>"+response[i].qty+"<\/td>";
				strVar += "<td>"+response[i].price+"<\/td>";
				strVar += "<td><a href=\"#\" onclick=\"getModal('"+response[i].frmrusername+"');return false;\" class=\"btn btn-info btn-xs\">show details<\/a><\/td>";
				strVar += "<\/tr>";
				$("#orderHistoryHtml").html(strVar);
			}	
			

		},
		error : function(jqXhr, textStatus, errorThrown) {
			alert("Please enter correct credentials.");
			console.log(errorThrown);	
		}
	});

});

function getModal(value){
	
	$.ajax({
		url : '/retailer/farmerdetail',
		type : 'get',		
		data : {
			frmrusername:value
		},
		success : function(data, textStatus, jQxhr) {
			var response = $.parseJSON(data);
			var strVar="";
			strVar += "<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"display: none;\">";
			strVar += "	<div class=\"modal-dialog\">";
			strVar += "		<div class=\"modal-content\">";
			strVar += "			<div class=\"modal-header\">";
			strVar += "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×<\/button>";
			strVar += "				<h4 class=\"modal-title\" id=\"myModalLabel\">Farmer Detail<\/h4>";
			strVar += "			<\/div>";
			strVar += "			<div class=\"modal-body\">";
			strVar += "                    Name : "+response.name+" <br\/>";
			strVar += "					Phone : " + response.phone + "  <br\/>";
			strVar += "					Addres : " + response.address + " <br\/>                        ";
			strVar += "             <\/div>";
			strVar += "			<div class=\"modal-footer\">";
			strVar += "				<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close<\/button>";			
			strVar += "			<\/div>";
			strVar += "		<\/div>";
			strVar += "	<\/div>";
			strVar += "<\/div>";
			strVar += "";
			strVar += "";
			strVar += "";
			strVar += "";



			
			$("#mymodal").html(strVar);
			$('#myModal').modal('show'); 
			

		},
		error : function(jqXhr, textStatus, errorThrown) {
			alert("Please enter correct credentials.");
			console.log(errorThrown);	
		}
	});
	
	
	
	
	

	
}
