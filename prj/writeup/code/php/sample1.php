<?php
$email = "we@e.com";

        	$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
        	$check_email="SELECT * from users where email = '$email'";
        $stmt=$link->query($check_email);
       if ($stmt->num_rows>0)
       {
		   $res = array(); 
       	while ($row=mysqli_fetch_assoc($stmt)) 
         {
         array_push($res, array(
				
			 "email"=>$row['email'],
			 
			 "p_image1"=>$row['p_image1']
			 
		 )
				);
		}
		//Displaying the array in json format 
		echo json_encode($res);
 }
	

?>