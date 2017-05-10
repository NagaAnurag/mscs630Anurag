<?php

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['email']) && isset($_POST['food']) && isset($_POST['rentbudget']) &&
	 isset($_POST['phonenumber']) && isset($_POST['nationality']) && isset($_POST['country']) &&
	  isset($_POST['city']) && isset($_POST['age']) && isset($_POST['m_stat']) &&
	   isset($_POST['gender']) && isset($_POST['smoker']) && isset($_POST['alcoholic']) &&
	    isset($_POST['nofrm']) && isset($_POST['type_of_house']) && isset($_POST['message']) &&
	     isset($_POST['number_of_rooms']) && isset($_POST['occupancy']) && isset($_POST['occupation']) &&
	      isset($_POST['cooking_skills']) && isset($_POST['ura']) && isset($_POST['address']) &&
        isset($_POST['ac']) && isset($_POST['geaser']) && isset($_POST['fridge']) &&
        isset($_POST['tv']) && isset($_POST['wm']) && isset($_POST['int']) && isset($_POST['image1']) && 
			isset($_POST['image2']) && isset($_POST['image3'])) {

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
       $address=$_POST['address'];
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
       $ac=$_POST['ac'];
       $geaser=$_POST['geaser'];
       $fridge=$_POST['fridge'];
       $tv=$_POST['tv'];
       $wm=$_POST['wm'];
       $int=$_POST['int'];
	   $image1=$_POST['image1'];
	   $image2=$_POST['image2'];
	   $image3=$_POST['image3'];

     $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");

    $sql="UPDATE users SET age='$age',phonenumber='$phonenumber',nationality='$nationality',
    country='$country',city='$city',religion='$religion',gender='$gender',m_stat='$m_stat',
    food='$food',rentbudget='$rentbudget',smoker='$smoker',alcoholic='$alcoholic',
    occupancy='$occupancy',owl='$owl',message='$message',nofrm='$nofrm',toh='$toh',
    occupation='$occ',no_of_rooms='$no_of_rooms',f1_post='1',f2_roommate='1',f3_room='0',
    post_active='1',first_post_variable='0',cooking_skills='$cs',tv='$tv',
    fridge='$fridge',ac='$ac',geaser='$geaser',washing_machine='$wm',internet='$int',
    address='$address',p_image1='$image1',p_image2='$image2',p_image3='$image3' WHERE email='$email'";
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