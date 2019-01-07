<?php

$conn = mysqli_connect("localhost", "root", "", "bloodify");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
   
   
   
     
    $nom = $_POST["nom"];
    $prenom = $_POST["prenom"];
    $Email = $_POST["Email"];
	$tel = $_POST["tel"];
	$region = $_POST["region"];
	$grpsanguin = $_POST["grpsanguin"];
	$age = $_POST["age"];
	$datedonation = $_POST["datedonation"];
    $password = $_POST["password"];
	$id = $_POST['Id'];

    $sql = "UPDATE user SET nom='$nom', prenom='$prenom', Email='$Email', tel=$tel, region='$region', grpsanguin='$grpsanguin', age=$age, datedonation='$datedonation', password='$password' WHERE Id=$id ";
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


