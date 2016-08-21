$(document).ready(function() {
	var response="";
	$.ajax({
        url: '/retailer/getCommodity',
        type: 'get',
        contentType: 'application/x-www-form-urlencoded',
        success: function( data, textStatus, jQxhr ){     	
           response = $.parseJSON(data);
           for(i=0; i<response.length; i=i+1){
        	   $("#products").append("<option>" + response[i].english + "</option>");
   	    }
        },
        error: function( jqXhr, textStatus, errorThrown ){
            console.log( errorThrown );
        }
    });
	$('#products').change(function() {
		$("#quantity option").remove();
		$(".priceDiv").removeClass('hide');	
		$(".quantityDiv").removeClass('hide');			
		var item = $("#products option:selected").text();
        for(i=0; i<response.length; i=i+1){
        	if(response[i].english == item){
        		$("#qtyPrice").text(response[i].price);
        		for(j=1;j<=response[i].quantity;j++){
        			$("#quantity").append("<option>" + j + "</option>");
        		}      		       		
        	}
        	
	    }
		
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
	           success: function( data, textStatus, jQxhr ){
	              alert("test");
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });
}); 



 