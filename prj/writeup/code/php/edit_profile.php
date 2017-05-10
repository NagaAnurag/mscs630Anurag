<?php

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['email']) && isset($_POST['phonenumber']) && isset($_POST['nationality']) &&
	 isset($_POST['country']) && isset($_POST['gender']) && isset($_POST['occupation']) &&
	  isset($_POST['city']) && isset($_POST['age']) && isset($_POST['m_stat'])){

		
		$email = $_POST['email'];
		$phonenumber=$_POST['phonenumber'];
        $nationality=$_POST['nationality'];
        $occ=$_POST['occupation'];
        $city=$_POST['city'];
        $age=$_POST['age'];
        $m_stat=$_POST['m_stat'];
        $gender=$_POST['gender'];
        $gender=$_POST['country'];
        
        $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
    
 $sql="UPDATE users SET age='$age',phonenumber='$phonenumber',occupation='$occ',nationality='$nationality',city='$city',
 gender='$gender',m_stat='$m_stat',country='$country' WHERE email='$email'";
 if ($link->query($sql) == TRUE) {
     
     $response["error"] = FALSE;
     $response["error_msg"] = " Data Sent ";
     echo json_encode($response);
    
 } 
 else {
     
     $response["error"] = TRUE;
     $response["error_msg"] = " Data Not Sent ";
     echo json_encode($response);
 }


}
?>