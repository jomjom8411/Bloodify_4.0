<?php

$conn = mysqli_connect("localhost", "root", "", "bloodify");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
   
   
   
     
    
	
	$Id = $_POST['Id'];
	 $id_user = $_POST['id_user'];
	$id_post = $_POST['id_post'];
	$etat = $_POST['etat'];
    
	 
	
    $sql = "UPDATE donate SET  id_user=$id_user, id_post=$id_post, etat=$etat WHERE Id=$Id  ";
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


