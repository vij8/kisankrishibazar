$(document).ready(function() {
	

	function startWorker() {
	    if(typeof(Worker) !== "undefined") {
	        if(typeof(w) == "undefined") {
	            w = new Worker("demo_workers.js");
	        }
	        w.onmessage = function(event) {
	            document.getElementById("result").innerHTML = event.data;
	        };
	    } else {
	        document.getElementById("result").innerHTML = "Sorry, your browser does not support Web Workers...";
	    }
	}

	function stopWorker() {
	    w.terminate();
	    w = undefined;
	}
	
	
	
	

	
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
	       var frmrusername = $(this).closest('tr').find('.userDetailsPop .username').val();
	       var orderAvailableID = $(this).closest('tr').find('.userDetailsPop .orderAvailableId').val();
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
					orderId : orderAvailableID,
					retailerusername : retailerusername,
					price : price
				},
	           success: function( data, textStatus, jQxhr ){
	        	   obj.closest('tr').find('.viewDetails').removeClass('disabled');   
	             var response = $.parseJSON(data);
	             if(response == "PASS"){
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
	        	  jsonResponse = $.parseJSON(data);
	        	  if(jsonResponse.length > 0){
	        		  $(".orderTable").removeClass('hide');
	        	  }
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
	        		  userResultHtml+="<td>";
	        		  userResultHtml+="   <button class='btn btn-xs btn-danger negotiate'>Negotiate</button>";
	        		  userResultHtml+=" </td>";
	        		  userResultHtml+="<td> <button class='btn btn-xs btn-danger viewDetails disabled'>View</button> </td>";
	        		  userResultHtml+=" <td>"
		              userResultHtml+=" <label class='label label-info distance' >"+jsonResponse[i].distance.toFixed(2)+" km</label></td>";
	        		  userResultHtml+="<td class='hide userDetailsPop'>"
	        		  userResultHtml+="<input type='hidden' class='address' value='" +jsonResponse[i].address+"'></input>";
	        		  userResultHtml+="<input type='hidden' class='username' value='" +jsonResponse[i].username+"'></input>";
	        		  userResultHtml+="<input type='hidden' class='orderAvailableId' value='" +jsonResponse[i].orderAvailableId+"'></input>";
	        		  userResultHtml+="<input type='hidden' class='quotedPrice' value='" +jsonResponse[i].orderAvailableId+"'></input>";
	        		  userResultHtml+="<input type='hidden' class='phoneno' value='"+jsonResponse[i].phone+"'></input></td></tr>";
	        	  }
	        	  $(".userDetails").html(userResultHtml);
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });
	

	
	$(document).on('click', '.viewDetails', function() {
		var address = $(this).closest('tr').find('.userDetailsPop .address').val();
		var phoneno = $(this).closest('tr').find('.userDetailsPop .phoneno').val();	
		var strVar="";
		strVar += "<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"display: none;\">";
		strVar += "	<div class=\"modal-dialog\">";
		strVar += "		<div class=\"modal-content\">";
		strVar += "			<div class=\"modal-header\">";
		strVar += "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×<\/button>";
		strVar += "				<h4 class=\"modal-title\" id=\"myModalLabel\">Farmer Detail<\/h4>";
		strVar += "			<\/div>";
		strVar += "			<div class=\"modal-body\">";
		strVar += "					Phone : "+phoneno +"<br\/>";
		strVar += "					Address : " +address +"<br\/>";
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
	$(document).on('click', '.negotiate', function() {		
		
		var orderAvailableId = $(this).closest('tr').find('.userDetailsPop .orderAvailableId').val();
		var quotedPrice = $(this).closest('tr').find('.userDetailsPop .quotedPrice').val();
		var  estimatedPrice  = $("#qtyPrice").text(); 
		var farmerUserName = 	$(this).closest('tr').find('.userDetailsPop .username').val();					
		 var details = $.parseJSON(localStorage.getItem('userdetail'));
	       var response = $.parseJSON(details);
	       var retailerusername = response.username;
		var item= $("#products option:selected").text() ;
				
		
		
		var strVar="";
		strVar += "<div class=\"modal fade in\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"false\" style=\"display: block;\">";
		strVar += "                                <div class=\"modal-dialog\">";
		strVar += "                                    <div class=\"modal-content\">";
		strVar += "                                        <div class=\"modal-header\">";
		strVar += "                                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×<\/button>";
		strVar += "                                            <h4 class=\"modal-title\" id=\"myModalLabel\">Negotiate Value<\/h4>";
		strVar += "                                        <\/div>";
		strVar += "                                        <div class=\"modal-body\">";
		strVar += "                                             <label>Enter New Value: <\/label>";		
		strVar += "                       			 <input id = \"negotiationPrice\" type=\"text\" class=\"form-control\">";
		strVar += "                                        <\/div>";
		strVar += "                                        <div class=\"modal-footer\">";		
		strVar += "				<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close<\/button>";
		strVar += "<button type=\"button\" class=\"btn btn-primary\" onclick = \"saveNegotiation( "+ orderAvailableId +"," +quotedPrice+"," +estimatedPrice+",'" +farmerUserName+"','" +item + "')\">Save changes<\/button>";
		strVar += "                                        <\/div>";
		strVar += "                                    <\/div>";
		strVar += "                                <\/div>";
		strVar += "                            <\/div>";

			$("#mymodal").html(strVar);
			$('#myModal').modal('show'); 			
			
				
	});
	
	
	
	
	
	
});


function saveNegotiation(orderAvailableId,quotedPrice,estimatedPrice,farmerUserName,item){
	$.ajax({
		url : '/retailer/saveNegotiationDetails',
		type : 'get',		
		data : {
			orderAvailableId:orderAvailableId,
			quotedPrice : quotedPrice,
			estimatedPrice : estimatedPrice,
			farmerUserName : farmerUserName,
			item : item
		},
		success : function(data, textStatus, jQxhr) {				
			$('#modal').modal('toggle');

		},
		error : function(jqXhr, textStatus, errorThrown) {
			console.log(errorThrown);	
		}
	

});
}




 