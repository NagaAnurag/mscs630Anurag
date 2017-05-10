<?php

if($_SERVER['REQUEST_METHOD']=='POST') { 

if (isset($_POST['sound']) ) {

       $email = $_POST['sound'];
   
     $link=mysqli_connect("182.50.133.90:3306","users","Android_API","android_api");

    $sql="INSERT INTO soundabc (sound) VALUES ('$email') ";
	$stmt=$link->prepare($sql);
$stmt->execute();
}
	}
?>