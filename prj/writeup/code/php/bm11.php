
<?php

$response = array("error" => FALSE);

        if (isset($_POST['email']){
    $email = $_POST['email'];
    //if($email != ""){       
                      // segment 1=> Comparision with second form (roommate)
                          $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");              
                      $pop = "SELECT * from users";
                               
                                $stmtpop=$link->query($pop);                               
                              
            
                        if ($stmtpop->num_rows>0)                               
                                 {
                                  $res = array(); 
                                  while ($row=mysqli_fetch_assoc($stmtpop))
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
                              /*}
                                else // for  null
                    {
                                
        
                      // segment 1=> Comparision with second form (roommate)
                        
                  $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");
                      $pop1 = "SELECT * from users ";
                                
                                $stmtpop1=$link->query($pop1);                               
                              
            
                        if ($stmtpop1->num_rows>0)                               
                                 {
                                  $res = array(); 
                                  while ($row=mysqli_fetch_assoc($stmtpop1))
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
                      }*/
                              }
                                ?>