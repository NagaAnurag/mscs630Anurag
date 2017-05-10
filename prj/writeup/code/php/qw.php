<?php 
if (isset($_POST['email'])){
	$abc = $_POST['email'];
	$cookie_name = "user";
$cookie_value = $abc;
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
}
else{
	if(isset($_COOKIE[$cookie_value])) {
    $email = $_COOKIE[$cookie_value];
    if ($email == null){
    	$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
        	$check_email="SELECT * from users where email = '$email'";
        $stmt=$link->query($check_email);
       if ($stmt->num_rows>0)
       {
		   $res = array(); 
       	while ($row=mysqli_fetch_assoc($stmt)) 
         {
         array_push($res, array(
				
			 "email"=>"abcdd";
			 
			 "p_image1"=>$row['p_image1']
			 
		 )
				);
		}
		//Displaying the array in json format 
		echo json_encode($res);
 }
    }
    	else {
    		$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
        	$check_email="SELECT * from users where email = '$email'";
        $stmt=$link->query($check_email);
       if ($stmt->num_rows>0)
       {
		   $res = array(); 
       	while ($row=mysqli_fetch_assoc($stmt)) 
         {
         array_push($res, array(
				
			 "email"=>"abc";
			 
			 "p_image1"=>$row['p_image1']
			 
		 )
				);
		}
		//Displaying the array in json format 
		echo json_encode($res);
 }
    	}

else {

	$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
        	$check_email="SELECT * from users where email = '$email'";
        $stmt=$link->query($check_email);
       if ($stmt->num_rows>0)
       {
		   $res = array(); 
       	while ($row=mysqli_fetch_assoc($stmt)) 
         {
         array_push($res, array(
				
			 "email"=>"ab1",
			 
			 "p_image1"=>$row['p_image1']
			 
		 )
				);
		}
		//Displaying the array in json format 
		echo json_encode($res);
 }
}
}
	?>