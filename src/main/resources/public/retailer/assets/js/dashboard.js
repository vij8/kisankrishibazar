$(document).ready(function() {
	var response="";
	var jsonResponse="";
	$.ajax({
        url: '/retailer/getCommodity',
        type: 'get',
        contentType: 'application/x-www-form-urlencoded',
        success: function( data, textStatus, jQxhr ){     	
           response = $.parseJSON(data);
           for(i=0; i<response.length; i=i+1){       	   
        	   $("#products").append("<option>" + response[i].item + "</option>");
   	    }
           $("#products").prepend("<option disabled selected> Select your option </option>");
           $('#products option:eq(0)').prop('selected', true);
        },
        error: function( jqXhr, textStatus, errorThrown ){
            console.log( errorThrown );
        }
    });
	$('#products').change(function() {
		$("#quantity option").remove();
		$(".priceDiv").removeClass('hide');	
		$(".quantityDiv").removeClass('hide');
		$("#search").removeClass('hide');
		var item = $("#products option:selected").text();
        for(i=0; i<response.length; i=i+1){
        	if(response[i].item == item){
        		$("#qtyPrice").text(response[i].price);
        		for(j=1;j<=response[i].quantity;j++){
        			$("#quantity").append("<option>" + j + "</option>");
        		}      		       		
        	}
        	
	    }
		
	});
	
	$(document).on('click', '.markInterested', function() {
		var obj = $(this);
	       var item =  $('#products :selected').text();
	       var details = $.parseJSON(localStorage.getItem('userdetail'));
	       var response = $.parseJSON(details);
	       var retailerusername = response.username;
	       var frmrusername = $(this).closest('tr').find('.name').text();
	       var qty = $(this).closest('tr').find('.quantity').text();
	       var price = $(this).closest('tr').find('.frmrPrice').text();
	       $.ajax({
	           url: '/retailer/saveOrder',
	           type: 'get',
	           contentType: 'application/x-www-form-urlencoded',
	           data : {
					item : item,
					qty : qty,
					frmrusername : frmrusername,
					retailerusername : retailerusername,
					price : price
				},
	           success: function( data, textStatus, jQxhr ){
	             var response = $.parseJSON(data);
	             if(response == "PASS"){
	            	 alert(obj.text());
	            	 obj.addClass("disabled");
	             }
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });
	
	$("#search").click(function() {
	       var item =  $('#products :selected').text();
	       var qty = $('#quantity :selected').text();
	       var details = $.parseJSON(localStorage.getItem('userdetail'));
	       var response = $.parseJSON(details);
	       var lat = response.lat;
	       var longt = response.longt;
	       $.ajax({
	           url: '/retailer/orderAvailable',
	           type: 'get',
	           contentType: 'application/x-www-form-urlencoded',
	           data : {
					item : item,
					qty : qty,
					lat : lat,
					longt : longt
				},
	           success: function( data, textStatus, jQxhr ){
	        	  $(".orderTable").removeClass('hide');
	        	  jsonResponse = $.parseJSON(data);
	        	  var userResultHtml = "";
	        	  for(i=0; i<jsonResponse.length; i++){
	        		  userResultHtml += "<tr> <td class='name'>"+jsonResponse[i].name+"</td> "
	        		  userResultHtml+= "<td class='quantity'>"+jsonResponse[i].quantity+"</td>"; 
	        		  userResultHtml+=" <td>"
	        		  userResultHtml+=" <label class='label label-info frmrPrice' >"+jsonResponse[i].price+"</label>";
	        		  userResultHtml+="</td>";
	        		  userResultHtml+="<td>";
	        		  userResultHtml+="   <button class='btn btn-xs btn-danger markInterested'>Mark Interested</button>";
	        		  userResultHtml+=" </td>";
	        		  userResultHtml+=" <td>01/25/2015</td>";
	        		  userResultHtml+="<td> <button class='btn btn-xs btn-danger viewDetails'>View</button> </td></tr> ";
	        		  userResultHtml+="<input type='hidden' class='address' value='" +jsonResponse[i].address+"'></input>";
	        		  userResultHtml+="<input type='hidden' class='phoneno' value='"+jsonResponse[i].phone+"'></input>";
	        	  }
	        	  $(".userDetails").html(userResultHtml);
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });
	

	
	$(document).on('click', '.viewDetails', function() {
		var address = $(this).closest('tbody').find('.address').val();
		var phoneno = $(this).closest('tbody').find('.phoneno').val();	
		var strVar="";
		strVar += "<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"display: none;\">";
		strVar += "	<div class=\"modal-dialog\">";
		strVar += "		<div class=\"modal-content\">";
		strVar += "			<div class=\"modal-header\">";
		strVar += "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×<\/button>";
		strVar += "				<h4 class=\"modal-title\" id=\"myModalLabel\">Farmer Detail<\/h4>";
		strVar += "			<\/div>";
		strVar += "			<div class=\"modal-body\">";
		strVar += "					Phone :"+phoneno +"<br\/>";
		strVar += "					Address :" +address +"<br\/>";
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
		
	}); 
	
});





 