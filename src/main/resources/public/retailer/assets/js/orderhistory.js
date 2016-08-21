$(document).ready(function() {

	$.ajax({
		url : '/retailer/orderHistory',
		type : 'get',		
		data : {
			retailerusername:"shamim"
		},
		success : function(data, textStatus, jQxhr) {
			var response = $.parseJSON(data);
			var strVar="";
			strVar += "<tr>";
			strVar += "<td>"+response.date+ "<\/td>";
			strVar += "<td>"+response.qty+"<\/td>";
			strVar += "<td>"+response.price+"<\/td>";
			strVar += "<td><a href=\"\/retaier\/farmerdetail:username\" class=\"btn btn-info btn-xs\">show details<\/a><\/td>";
			strVar += "<\/tr>";
			$("#orderHistoryHtml").html(strVar);
			

		},
		error : function(jqXhr, textStatus, errorThrown) {
			alert("Please enter correct credentials.");
			console.log(errorThrown);
		}
	});

});
