<?php

$conn = mysqli_connect("localhost", "root", "", "bloodify");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
   
   
   
     
    
	
	$Id = $_POST['Id'];
	$points = $_POST['points'];
    
	 
	
    $sql = "UPDATE user SET  points =$points WHERE Id=$Id  ";
    if(mysqli_query($conn, $sql)) {

          $result["success"] = "1";
          $result["message"] = "success";

          echo json_encode($result);
          mysqli_close($conn);
      }
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}

?>


