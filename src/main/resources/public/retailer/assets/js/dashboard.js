$(document).ready(function() {
	$("#search").click(function() {
	       var id =  $("#username").val();
	       var password = $("#password").val();
	       $.ajax({
	           url: '/retailer/login',
	           type: 'post',
	           contentType: 'application/x-www-form-urlencoded',
	           data: {
	        	   username : id,
	        	   password : password
	           },
	           success: function( data, textStatus, jQxhr ){
	              alert("test");
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });
}); 



 