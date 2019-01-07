<?php
    $con = mysqli_connect("localhost", "root", "", "bloodify");
	
     if ($_SERVER['REQUEST_METHOD'] =='POST'){
		 		 
    $id = $_POST["id_user"];
   	$region = $_POST["region"];
	$grpsanguin = $_POST["grpsanguin"];
	$slots = $_POST["slots"];
	
    $statement = mysqli_prepare($con, "INSERT INTO post ( id_user, region, grpsanguin, slots) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "isss", $id, $region, $grpsanguin, $slots);
    mysqli_stmt_execute($statement);
    
      $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
	 }
?>
