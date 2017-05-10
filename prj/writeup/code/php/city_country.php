<?php

 
if (isset($_POST['email']) && isset($_POST['city']) && isset($_POST['country'])){

	$email = $_POST['email'];
	$country=$_POST['country'];
    $country=strtolower($country);
    $city=$_POST['city'];
    $city=strtolower($city);

    $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");

    $sql="UPDATE users SET country='$country',city='$city' WHERE email='$email'";
if ($link->query($sql) == TRUE)
 {
       
         $response["error"] = FALSE;
         $response["error_msg"] = " Data Sent ";
                           echo json_encode($response);
                       }
                   
                        
                        else{
                            $response["error"] = TRUE;
                            $response["error_msg"] = " Data Not Sent ";
                           echo json_encode($response);
                        }
                    

}

?>