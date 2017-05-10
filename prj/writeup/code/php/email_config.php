<?php

session_start(); 
$_SESSION['email'] = $_POST['email'];
if($_SESSION['email'] != null){
	$response["error"] = FALSE;
    $response["error_msg"] = " Data Sent ";
	//echo json_encode($response);
	header('Location:sample1.php');
		
	}
	else{
		$response["error"] = TRUE;
    $response["error_msg"] = " Data Sent ";
		//	echo json_encode($response);
	}
?>