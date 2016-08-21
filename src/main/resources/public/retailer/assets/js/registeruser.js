$(document).ready(function() {

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(p) {
			$("#lat").val(p.coords.latitude);
			$("#longt").val(p.coords.longitude);

		});
	} else {
		alert('Geo Location feature is not supported in this browser.');
	}
	
	
	$("#register").click(function() {
	     
	       $.ajax({
	           url: '/retailer/register',
	           type: 'post',
	           contentType: 'application/x-www-form-urlencoded',
	           data: $("#retailregister").serialize(),
	           success: function( data, textStatus, jQxhr ){
	              alert("test");
	           },
	           error: function( jqXhr, textStatus, errorThrown ){
	               console.log( errorThrown );
	           }
	       });
	 });

});
