<?php

include_once ("connect.php");
$query = "SELECT * FROM post  ORDER BY donors_number ASC";

$result = mysqli_query($conn,$query);

$number_of_rows =  mysqli_num_rows($result);

$response = array ();


if ($number_of_rows >0 ) {
	while($row = mysqli_fetch_assoc($result)){
		
	$response[] = $row;
		
		
	}
	
	
	
}

header('Content-Type: application/json');
echo json_encode(array("jomjom"=>$response));
mysqli_close($conn);





?>