function checkAvailability() {
	  var date = $("#bookingDate").val();
	  var branch = $("#branch").val();
	  var url =
	    location.protocol +
	    "//" +
	    location.hostname +
	    (location.port ? ":" + location.port : "");
	  $.ajax({
	    type: "GET",
	    url:url +"/ayur/appointment/check-availability?bookingDate=" +date +"&branchId=" +branch,
	    success: function(resp) {
	      if (resp != null && resp != "" && resp != "null") {
	        if (resp == "Available") {
	          $("#availability").html("<b style='color:green'>" + resp + "</b>");
	          $("#btnSubmit").attr("disabled", false);
	        } else {
	          $("#availability").html(
	            "<b style='color:red'> Sorry appointment not available for the selected date </b>"
	          );
	          $("#btnSubmit").attr("disabled", true);
	        }
	      }
	    },
	    error: function(response) {
	      console.log(response.responseText);
	      alert("Sorry appointment not available for this branch on " + date);
	    }
	  });
	}