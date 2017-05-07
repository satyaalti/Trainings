$(document).ready(function(){
	
	var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfdata = {'X-CSRF-TOKEN': csrfToken};
  
	  var getbalance = function() {
		  
		  var postdata = {userid: $("#fromaccount").val()};
		  
		 // $.extend(postdata, csrfdata);
		  
		  $.ajax({
			    url:  rooturl+ "my/getbalance",
			    type: 'post',
			    data: postdata,
			    headers: csrfdata,
			    dataType: 'json',
			    success: function (data) {
			    	 $("#baldiv").html("Total balance is: "+data.balance);
					 $("#total").val(data.balance);
			    }
			});
	  }
	 
	  getbalance();
		

	 $("#fromaccount").change(function() {
		 getbalance();
		 getbankuserslist();
	 })

	 var getbankuserslist = function() {
		  
		  var postdata = {
				  			fromid: $("#fromaccount").val(), 
				  			banktype: $("input[name=banktype]:checked").val()
				  		 }
		  
		  
		  $.ajax({
			    url:  rooturl+ "my/getbankuserslist",
			    type: 'post',
			    data: postdata,
			    headers: csrfdata,
			    dataType: 'json',
			    success: function (data) {
			    	  var options = "<select  id='toaccount' name='toaccount'>" +
			    	  				"<option value=''>Select An Account</option>" ;	
					  $.each(data, function(key, result){
						 // alert(result.userid+":"+result.firstName);
						  options += "<option value = "+result.userid+">"+result.firstName+"</option>"; 
					  })
					  options +=	"</select>";
					  $('#toaccounttd').html(options);
			    }
			});
		  
		}
	  
	  $("#transferFrm").submit(function(event) {
		 var from = $("#fromaccount").val();
		 var to = $("#toaccount").val();
		 var total = parseInt($("#total").val());
		 var transferamount = $("#transferamount").val();
		 
		if(to == "") {
			event.preventDefault();
			alert("Please select an account to transfer");
		}
		else if(transferamount == "" || transferamount <= 0 ) {
			event.preventDefault();
			alert("Amount should not empty and greater than 0");
		}
		else if(total < transferamount) {
			event.preventDefault();
			alert("You have insufficient balance in your ount");
		}
		
	 });
	 
	 $('input[name=banktype]').change(function(){
		 getbankuserslist();
	 })
	 
	 getbankuserslist();
});