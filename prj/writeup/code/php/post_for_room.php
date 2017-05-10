<?php

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['email']) && isset($_POST['food']) && isset($_POST['rentbudget']) &&
	 isset($_POST['phonenumber']) && isset($_POST['nationality']) && isset($_POST['country']) &&
	  isset($_POST['city']) && isset($_POST['age']) && isset($_POST['m_stat']) &&
	   isset($_POST['gender']) && isset($_POST['smoker']) && isset($_POST['alcoholic']) &&
	    isset($_POST['nofrm']) && isset($_POST['type_of_house']) && isset($_POST['message']) &&
	     isset($_POST['number_of_rooms']) && isset($_POST['occupancy']) && isset($_POST['occupation']) &&
	      isset($_POST['cooking_skills']) && isset($_POST['ura'])) {

	// receiving the post params

       $email = $_POST['email'];
       $food=$_POST['food']; 
       $rentbudget=$_POST['rentbudget'];
       $phonenumber=$_POST['phonenumber'];
       $nationality=$_POST['nationality'];
       $nationality=strtolower($nationality);
       $country=$_POST['country'];
       $country=strtolower($country);
       $city=$_POST['city'];
       $city=strtolower($city);
       $age=$_POST['age'];
       $m_stat=$_POST['m_stat'];
       $gender=$_POST['gender'];
       $smoker=$_POST['smoker'];
       $alcoholic=$_POST['alcoholic'];
       $message=$_POST['message'];
       $nofrm=$_POST['nofrm'];
       $toh=$_POST['type_of_house'];
       $no_of_rooms=$_POST['number_of_rooms'];
       $occupancy=$_POST['occupancy'];
       $occ=$_POST['occupation'];
       $cs=$_POST['cooking_skills'];
       $owl=$_POST['ura'];
	   

     $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");

    $sql="UPDATE users SET age='$age',phonenumber='$phonenumber',nationality='$nationality',country='$country',city='$city',religion='$religion',
gender='$gender',m_stat='$m_stat',food='$food',rentbudget='$rentbudget',smoker='$smoker',alcoholic='$alcoholic',occupancy='$occupancy',owl='$owl',
message='$message',nofrm='$nofrm',toh='$toh',occupation='$occ',no_of_rooms='$no_of_rooms',f1_post='0',f2_roommate='1',f3_room='1',post_active='1',
first_post_variable='0',cooking_skills='$cs' WHERE email='$email'";
if ($link->query($sql) == TRUE)
 {
                           // echo "True";
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