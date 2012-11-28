<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php
$fname = $_POST['fname'];
$mname = $_POST['mname'];
$lname = $_POST['lname'];
$gender = $_POST['gender'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$city = $_POST['city'];
$state = $_POST['state'];
$addrline1 = $_POST['addrline1'];
$addrline2 = $_POST['addrline2'];
$addrline3 = $_POST['addrline3'];
$pincode = $_POST['pincode'];
?>
<!--<script>
var JSONObject = {
    "firstName" : "Abdul",
    "middleName" : "Mujahid",
    "lastName" : "Shaikh",
    "gender" : "MALE" ,
    "email" : "shaikh.mujahid@gmail.com",
    "phone" : "919702347733",
    "address" : {
        "CITY" : "Mumbai",
        "STATE" : "Maharashtra",
        "ADDRESSLINE1" : "addressLine1",
        "ADDRESSLINE2" : "addressLine2",
        "ADDRESSLINE3" : "addressLine3",
        "PINCODE" : "400067"
    }        
};
document.getElementById("fname").innerHTML=JSONObject.firstName
document.getElementById("mname").innerHTML=JSONObject.middleName
document.getElementById("lname").innerHTML=JSONObject.lastName 
document.getElementById("gender").innerHTML=JSONObject.gender
document.getElementById("email").innerHTML=JSONObject.email
document.getElementById("phone").innerHTML=JSONObject.phone

document.getElementById("city").innerHTML=JSONObject.address.CITY
document.getElementById("state").innerHTML=JSONObject.address.STATE
document.getElementById("addrline1").innerHTML=JSONObject.address.ADDRESSLINE1
document.getElementById("addrline2").innerHTML=JSONObject.address.ADDRESSLINE2
document.getElementById("addrline3").innerHTML=JSONObject.address.ADDRESSLINE3
document.getElementById("pincode").innerHTML=JSONObject.address.PINCODE
</script>
-->
<p>Susscesfully sumitted</p>
</body>
</html>
