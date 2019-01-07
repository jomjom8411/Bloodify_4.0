<?php
    $con = mysqli_connect("localhost", "root", "", "bloodify");
	
     if ($_SERVER['REQUEST_METHOD'] =='POST'){
		 		 
    $id_user = $_POST["id_user"];
   	$id_post = $_POST["id_post"];
	
	
	
    $statement = mysqli_prepare($con, "INSERT INTO donate ( id_user, id_post) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ii", $id_user, $id_post);
    mysqli_stmt_execute($statement);
    
      $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
	 }
?>
