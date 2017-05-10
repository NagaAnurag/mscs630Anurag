<?php
$response = array("error" => FALSE);
if (isset($_POST['email'])){

      $email=$_POST['email'];
        	$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
        	$check_email="SELECT * from users where email='$email' ";
        $stmt=$link->query($check_email);
       if ($stmt->num_rows>0)
       {
       	while ($row=mysqli_fetch_assoc($stmt)) 
         {
         $response["error"] = FALSE;
         $response["user"]["first_post_variable"] = $row['first_post_variable'];
          echo json_encode($response);
       }
     }
   }
   ?>