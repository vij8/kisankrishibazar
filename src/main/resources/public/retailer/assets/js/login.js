$(document).ready(function() {
	$("#login").click(function() {
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
	        	   window.location.href = '/retailer/dashboard';
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	        	   alert("Please enter correct credentials.");
	               console.log( errorThrown );
	           }
	       });
	 });
}); 



 