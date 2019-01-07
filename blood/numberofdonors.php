<?php


$Id = $_GET['id_post'];


include_once ("connect.php");

$query = "SELECT * FROM donate  WHERE id_post =$Id ";

$result = mysqli_query($conn,$query);




$number_of_rows =  mysqli_num_rows($result);


echo($number_of_rows);

if ($number_of_rows >0 ) {
	
		
		
	}
	
	
	






?>