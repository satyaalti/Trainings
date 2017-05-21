$(document).ready(function(){
	
	var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfdata = {'X-CSRF-TOKEN': csrfToken};
  
	var getbalance = function() {
		   
		var userid = $("#fromaccount").val();
		if (typeof userid != "undefined" ) {
			var postdata = {userid: userid};
			  
			// $.extend(postdata, csrfdata);
			  
			$.ajax({
				url:  rooturl+ "user/getbalance",
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
	}
	 
	getbalance();

	$("#fromaccount").change(function() {
		getbalance();
		getbankuserslist();
	})

	var getbankuserslist = function() {
		  
	var fromid = $("#fromaccount").val();
		 
		if (typeof fromid != "undefined" ) {
			  
			var postdata = {
					fromid: fromid, 
			  		banktype: $("input[name=banktype]:checked").val()
			}
			  
			$.ajax({
				    url:  rooturl+ "user/getbankuserslist",
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
	  
	  $('#addUserForm').submit(function(){
		  if($('#userid').val() != '') {
			  if($('#password').val() != '')
				  $('#requiredPwdEncrypt').val(true);
			  else
				  $('#password').val($('#cpassword').val());
		  }
	  });
	  
	  
	  $("#UserregisterForm").submit(function(event) {
		  var to = $("#toaccount").val();
			 var firstName = $("#firstName").val();
			 var lastName = $("#lastName").val();
			 var username = $("#username").val();
			 var password = $("#password").val();
			 var cpassword = $("#cpassword").val();
			 var email = $("#email").val();
			 var bankid = $("#bankid").val();
			 
			if(firstName == "") {
				event.preventDefault();
				alert("Please enter First Name");
			}
			else if(lastName == "" ) {
				event.preventDefault();
				alert("Please enter Last Name");
			}
			else if(username == "" ) {
				event.preventDefault();
				alert("Please enter User Name");
			}
			else if(password == "" ) {
				event.preventDefault();
				alert("Please enter Password");
			}
			else if(cpassword == "" ) {
				event.preventDefault();
				alert("Please enter your Password again");
			}
			else if(password != cpassword ) {
				event.preventDefault();
				alert("Passwords do not match");
			}
			else if(email == "" ) {
				event.preventDefault();
				alert("Please enter Email Address");
			}
			else if(bankid == 0 ) {
				event.preventDefault();
				alert("Please select your Bank");
			}
	  });
	  
	  
	 
	 $('input[name=banktype]').change(function(){
		 getbankuserslist();
	 })
	 
	 getbankuserslist();
	 
	 var validateWithdrawAmount = function() {
		 var total_bal = parseInt(document.getElementById("totalbalance").value);
		 var withdrawamount = parseInt(document.getElementById("withdrawamount").value);
		 if(total_bal < withdrawamount) {
		    alert("Insufficient balance. You can withdraw upto :" + total_bal + "/- only");
		  	return false;
		 }
		 return true;
	 }
	 
	 $('[name="changestatus"]').click(function(){
		 $('#selectedid').val(this.id);
		 $('[name="UserListForm"]').attr('action', rooturl+"admin/changeuserstatus");
		 $('[name="UserListForm"]').submit();
	 });
	 
	 $("#changepwd").click(function(){
		 if($('#trpwd').is(':hidden')) {
			$('#password').val('');
		 }
		 $("#trpwd").toggle(1000);
		 
	 });
});