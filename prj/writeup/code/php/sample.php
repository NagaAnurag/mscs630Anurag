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
         $response["user"]["username"] = $row['username'];
         $response["user"]["email"] = $row['email'];
         $response["user"]["gender"] = $row['gender'];
         $response["user"]["rent"] = $row['rent'];
         $response["user"]["country"] = $row['country'];
         $response["user"]["nationality"] = $row['nationality'];
         $response["user"]["phonenumber"] = $row['phonenumber'];
         $response["user"]["alcoholic"] = $row['alcoholic'];
         $response["user"]["m_stat"] = $row['m_stat'];
         $response["user"]["age"] = $row['age'];
         $response["user"]["image"] = $row['image'];
         $response["user"]["rentbudget"] = $row['rentbudget'];
         $response["user"]["smoker"] = $row['smoker'];
         $response["user"]["food"] = $row['food'];
         $response["user"]["no_of_rooms"] = $$row['no_of_rooms'];
         $response["user"]["city"] = $row['city'];
         $response["user"]["f1_post"] = $row['f1_post'];
         $response["user"]["f2_roommate"] = $row['f2_roommate'];
         $response["user"]["f3_roomo_rent"] = $row['f3_room'];
         $response["user"]["o_rent"] = $row['o_rent']; 
         $response["user"]["occupancy"] = $row['occupancy'];  
		 $response["user"]["tv"] = $row['tv'];
		 $response["user"]["fridge"] = $row['fridge'];
		 $response["user"]["geaser"] = $row['geaser'];
		 $response["user"]["ac"] = $row['ac'];
		 $response["user"]["washing_machine"] = $row['washing_machine'];
		 $response["user"]["internet"] = $row['internet'];
		 $response["user"]["p_image1"] = $row['p_image1'];
		 $response["user"]["p_image2"] = $row['p_image2'];
		 $response["user"]["p_image3"] = $row['p_image3'];
         $response["user"]["occupation"] = $row['occupation'];
         $response["user"]["toh"] = $row['toh'];
         $response["user"]["cooking_skills"] = $row['cooking_skills'];
         $response["user"]["message"] = $row['message'];
         $response["user"]["nofrm"] = $row['nofrm'];
         $response["user"]["owl"] = $row['owl'];
         $response["user"]["address"] = $row['address'];
         echo json_encode($response);
    }
 }
}
 

?>