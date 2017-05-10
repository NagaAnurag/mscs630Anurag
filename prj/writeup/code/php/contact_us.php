

<?php

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['phoneno']) && isset($_POST['subject']) && isset($_POST['message'])) {

	// receiving the post params
    $phoneno = $_POST['phoneno'];
    $subject = $_POST['subject'];
    $message = $_POST['message'];

     $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");

    $query="INSERT INTO contact_us (phoneno, subject, message) 
                    values ('$phoneno', '$subject', '$message')";
                    $stmt=$link->prepare($query);
                    if($stmt->execute()==true)
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