<?php


include_once ("connect.php");

$Id = $_GET['id_user'];
$id_post = $_GET['id_post'];


$query = "SELECT * FROM donate  WHERE id_user =$Id AND id_post =$id_post ";

$result = mysqli_query($conn,$query);




$number_of_rows =  mysqli_num_rows($result);


echo($number_of_rows);

if ($number_of_rows == 0 ) {
	
		
		
	}
	
	if ($number_of_rows == 1 ) {
	
		
		
	}
	
	







?>