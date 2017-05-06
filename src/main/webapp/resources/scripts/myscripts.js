$(document).ready(function(){
	
	var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfdata = {'X-CSRF-TOKEN': csrfToken};
  
	  var getbalance = function() {
		  
		  var postdata = {userid: $("#fromaccount").val()};
		  
		 // $.extend(postdata, csrfdata);
		  
		  $.ajax({
			    url:  rooturl+ "admin/getbalance",
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
			    url:  rooturl+ "admin/getbankuserslist",
			    type: 'post',
			    data: postdata,
			    headers: csrfdata,
			    dataType: 'json',
			    success: function (data) {
			    	  var options = "<select  id='toaccount' name='toaccount'>" ;
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
		 var to = parseInt($("#toaccount").val());
		 var total = parseInt($("#total").val());
		 var transferamount = $("#transferamount").val();
		 
		if( from==to) {
			event.preventDefault();
			alert("use another name for to user");
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