<?php
$email = "we@e.com";
$link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
	$check_email="SELECT * from users where email='$email' ";
    $stmt=$link->query($check_email);
    if ($stmt->num_rows>0)
    {
     while ($row=mysqli_fetch_assoc($stmt)) 
       {
            $GLOBALS['L_rent']= $row['rent'];
            $GLOBALS['L_country']=$row['country'];
            $GLOBALS['L_nation']=$row['nationality'];
            $GLOBALS['L_phonenumber']=$row['phonenumber'];
            $GLOBALS['L_alcoholic']=$row['alcoholic'];
            $GLOBALS['L_martial']=$row['m_stat'];
            $GLOBALS['L_age']=$row['age'];
            $GLOBALS['L_bud']=$row['rentbudget'];
            $GLOBALS['L_smoker']=$row['smoker'];
            $GLOBALS['L_food']=$row['food'];
            $GLOBALS['L_nor']=$row['no_of_rooms'];
            $GLOBALS['L_city']=$row['city'];
            $GLOBALS['L_post']=$row['f1_post'];
            $GLOBALS['L_roomate']=$row['f2_roommate'];
            $GLOBALS['L_room']=$row['f3_room'];
            $GLOBALS['L_o_rent']=$row['o_rent'];
            $GLOBALS['L_occ']=$row['occupancy'];   
		}
    }	
		    $llrent=$GLOBALS['L_rent'];
		    $country1=$GLOBALS['L_country'];
		    $nation1=$GLOBALS['L_nation'];
		    $alcoholic1=$GLOBALS['L_alcoholic'];
		    $martial=$GLOBALS['L_martial'];
		    $smoker1=$GLOBALS['L_smoker'];
		    $food1=$GLOBALS['L_food'];
		    $age1=$GLOBALS['L_age'];
		    $bud1=$GLOBALS['L_bud'];
		    $nor1=$GLOBALS['L_nor'];
		    $city1=$GLOBALS['L_city'];
		    $f1_post=$GLOBALS['L_post'];
		    $o_rent=$GLOBALS['L_o_rent'];
		    $rent=$GLOBALS['L_rent'];
			$f2_roommate=$GLOBALS['L_roomate'];
		    $f3_room=$GLOBALS['L_room'];
			$occ=$GLOBALS['L_occ'];

			      // segment 1=> Comparision with second form (roommate)
                        //All
                        $q1="SELECT * from users where country='$country1' and f1_post='1' and f2_roommate='0' and city='$city1' and email != '$email'ORDER BY id DESC";
  $stmt1=$link->query($q1);
  if ($stmt1->num_rows>0)                               
                                 {
                                  $res = array(); 
                                  while ($row=mysqli_fetch_assoc($stmt1))
                                  {
                                  		array_push($res, array(
				
									 "username" => $row['username'],
							         "email" => $row['email'],
							         "gender" => $row['gender'],
							         "rent" => $row['rent'],
							         "country" => $row['country'],
							         "nationality" => $row['nationality'],
							         "phonenumber" => $row['phonenumber'],
							         "alcoholic" => $row['alcoholic'],
							         "m_stat" => $row['m_stat'],
							         "age" => $row['age'],
							         "image" => $row['image'],
							         "rentbudget" => $row['rentbudget'],
							         "smoker" => $row['smoker'],
							         "food" => $row['food'],
							         "no_of_rooms" => $row['no_of_rooms'],
							         "city" => $row['city'],
							         "f1_post" => $row['f1_post'],
							         "f2_roommate" => $row['f2_roommate'],
							         "f3_roomo_rent" => $row['f3_room'],
							         "o_rent" => $row['o_rent'], 
							         "occupancy" => $row['occupancy'],  
									 "tv" => $row['tv'],
									 "fridge" => $row['fridge'],
									 "geaser" => $row['geaser'],
									 "ac" => $row['ac'],
									 "washing_machine" => $row['washing_machine'],
									 "internet" => $row['internet'],
									 "p_image1" => $row['p_image1'],
									 "p_image2" => $row['p_image2'],
									 "p_image3" => $row['p_image3'],
							         "occupation" => $row['occupation'],
							         "toh" => $row['toh'],
							         "cooking_skills" => $row['cooking_skills'],
							         "message" => $row['message'],
							         "nofrm" => $row['nofrm'],
							         "owl" => $row['owl'],
							         "address" => $row['address']
									 
								 						  )
											  );
                                  }
                                  echo json_encode($res);
                              	}
else{
$res = array(); 
       	
         array_push($res, array(
				
			 "email"=>"lsaksmkal",
			 
			 "p_image1"=>$row['p_image1']
			 
		 )
				);
		
		//Displaying the array in json format 
		echo json_encode($res);
}
?>