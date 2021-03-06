$(document).ready(function() {
	$(".errorMessage").addClass('hide');
	$("#login").click(function() {
		var id = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			url : '/retailer/login',
			type : 'post',
			contentType : 'application/x-www-form-urlencoded',
			data : {
				username : id,
				password : password
			},
			success : function(data, textStatus, jQxhr) {
				if (typeof (Storage) !== "undefined") {
					$(".errorMessage").addClass('hide');
					localStorage.setItem('userdetail', JSON.stringify(data));
					window.location.href = '/retailer/dashboard';
				} else {
					console.log("No local Storage Support");
				}

			},
			error : function(jqXhr, textStatus, errorThrown) {
				$(".errorMessage").removeClass('hide');
				console.log(errorThrown);
			}
		});
	});
});
