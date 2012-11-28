<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Qpeka | Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/slider.css">
<style>
#register-form label.error {
    color: #FB3A3A;
    display: inline-block;
    margin: 5px;
    padding: 0;
    text-align: left;
}
</style>
<script src="js/json2.js"></script>
<script>
function registration(){
	firstName = document.getElementById("fname").value;
		/*if(firstName.length < 5){
				document.getElementById("fnameerror").innerHTML = '<div class="error">mandatory field</div>';
				return false;
		}*/
	middleName = document.getElementById("mname").value;
		/*if(middleName.length < 5){
				document.getElementById("mnameerror").innerHTML = 'mandatory field';
				return false;
		}*/
	lastName = document.getElementById("lname").value; 
		/*if(lastName.length < 5){
			document.getElementById("lnameerror").innerHTML = 'mandatory field';
			return false;
		}*/
	gender = document.getElementById("gender").value;
	email = document.getElementById("email").value;
		/*if(email.length < 5){
				document.getElementById("emailerror").innerHTML = 'mandatory field';
				return false;
		}*/
	phone = document.getElementById("phone").value;	
		/*if(phone.length < 5){
			document.getElementById("phoneerror").innerHTML = 'mandatory field';
			return false;
		}*/
	city = document.getElementById("city").value;
	state = document.getElementById("state").value;
	addressline1 = document.getElementById("addrline1").value;
	addressline2 = document.getElementById("addrline2").value;
	addressline3 = document.getElementById("addrline3").value;
	pincode = document.getElementById("pincode").value;
		/*if(pincode.length < 5){
			document.getElementById("pincodeerror").innerHTML = 'mandatory field';
			return false;
		}*/

var JSONObject = {
    "firstName" : firstName,
    "middleName" : middleName,
    "lastName" : lastName,
    "gender" : gender,
    "email" : email,
    "phone" : phone,
    "address" : {
        "CITY" : city,
        "STATE" : state,
        "ADDRESSLINE1" : addressline1,
        "ADDRESSLINE2" : addressline2,
        "ADDRESSLINE3" : addressline3,
        "PINCODE" : pincode
    }        
};
//JSON.stringify(JSONOject);
jsonstr = JSON.stringify(JSONObject);
console.log(JSONObject);
console.log(jsonstr);

jsonstr = encodeURI(jsonstr);
console.log(jsonstr);
	var url = 'http://192.168.2.6:8080'+'?params='+jsonstr+'&rand='+Math.random();
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status == 200) {
			alert('succes submit');
		}
	}
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}
</script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<script type="text/javascript">
(function($,W,D)
{
    var JQUERY4U = {};

    JQUERY4U.UTIL =
    {
        setupFormValidation: function()
        {
            //form validation rules
            $("#register-form").validate({
                rules: {
                    fname: "required",
                    lname: "required",
					gender: "required",
                    email: {
                        required: true,
                        email: true
                    },
                    phone: {
                        required: true,
                        minlength: 10
                    },
					pincode: {
                        required: true,
                        minlength: 6
                    },
                },
                messages: {
                    fname: "Please enter your first name",
                    lname: "Please enter your last name",
					gender: "Please select gender",
                    number: {
                        required: "Please enter a valid number",
                    },
                    email: "Please enter a valid email address",
					digits: {
                        required: "Please enter a valid pincode",
					},
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        }
    }

    //when the dom has loaded setup form validation rules
    $(D).ready(function($) {
        JQUERY4U.UTIL.setupFormValidation();
    });

})(jQuery, window, document);
</script>

</head>
<body>
<div class="super-container">
  <div class="fheader">
    <?php include 'common/header.php'; ?>
    <?php include 'common/navigation.php'; ?>
  </div>
  <div class="container">
    <div class="m-promos">
      <div class="discover">
        <h2>Discover Qpeka!!!</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
      </div>
      <div class="get-started"> 
        <!--<table class="tablem">
          <tr>
            <td><input type="text" placeholder="Email" /></td>
            <td><input type="password" placeholder="Password" /></td>
            <td></td>
            <td><input type="button" value="Login" /></td>
          </tr>
          <tr>
            <td><a href="">&nbsp;Forget Password?</a></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table>--> 
      </div>
    </div>
    <div id="main" role="main">
      <div class="section">
        <div class="register">
          <h2>Register</h2>
          <form method="POST" onSubmit="registration(); return false;"  id="register-form" novalidate="novalidate">
            <table width="100%">
              <tr><td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">First Name:</td>
                <td width="70%"><input type="text" name="fname" id="fname" class="rfield" /></td>
              </tr>
              <tr><td width="1%"></td>
                <td width="21%">Middle Name:</td>
                <td width="70%"><input type="text" name="mname" id="mname" class="rfield" /></td>
              </tr>
              <tr><td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">Last Name:</td>
                <td width="70%"><input type="text" name="lname" id="lname" class="rfield" /></td>
              </tr>
              <tr><td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">Gender:</td>
                <td width="70%">
                <!--<input type="text" name="gender" id="gender" />
                <input type="radio" id="male" name="sex" value="male"> Male <input type="radio" id="female" name="sex" value="female"> Female-->
				 <select name="gender" id="gender" style="width:200px;">
                    <option></option>
                    <option>Male</option>
                    <option>Female</option>
                  </select>
                  </td>
              </tr>
              <tr><td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">Email:</td>
                <td width="70%"><input type="text" name="email" id="email" class="rfield" /></td>
              </tr>
              <tr>
              <td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">Phone:</td>
                <td width="70%"><input type="number" maxlength="10" name="phone" id="phone" class="rfield" /></td>
              </tr>
              <!--<tr>
                <td colspan="3"><h4>Address</h4></td>
              </tr>-->
              <tr><td width="1%"></td>
                <td width="21%">City:</td>
                <td width="70%"><input type="text" name="city" id="city" class="rfield" /></td>
              </tr>
              <tr><td width="1%"></td>
                <td width="21%">State:</td>
                <td width="70%"><input type="text" name="state" id="state" class="rfield" /></td>
              </tr>
              <tr><td width="1%"></td>
                <td width="21%">Address Line1:</td>
                <td width="70%"><input type="text" name="addrline1" id="addrline1" class="rfield" /></td>
              </tr>
              <tr>
              <td width="1%"></td>
                <td width="21%">Address Line2:</td>
                <td width="70%"><input type="text" name="addrline2" id="addrline2" class="rfield" /></td>
              </tr>
              <tr>
              <td width="1%"></td>
                <td width="21%">Address Line3:</td>
                <td width="70%"><input type="text" name="addrline3" id="addrline3" class="rfield" /></td>
              </tr>
              <tr>
              <td width="1%"><span class="mandatory">*</span></td>
                <td width="21%">Pincode:</td>
                <td width="70%"><input type="text" name="pincode" id="pincode" maxlength="6" class="rfield" /></td>
              </tr>
              <tr><td width="1%"></td>
              	<td width="21%">&nbsp;</td>
                <td width="70%"><input type="submit" value="Register" onClick="registration();" /></td>
              </tr>
            </table>
          </form>
        </div>
      </div>
      <div class="aside">
        <?php include 'module/featuredBooks.php'; ?>
        <?php include 'module/recommendedBooks.php'; ?>
      </div>
    </div>
    <?php include 'common/footer.php'; ?>
  </div>
</div>
</body>
</html>