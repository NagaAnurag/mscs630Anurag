<?php

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['email'])) {
	$email = $_POST['email'];
	 $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
    
 $sql="UPDATE email SET email='$email' WHERE id='1'";
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