<?php
    $con = mysqli_connect("localhost", "root", "", "bloodify");
	echo("hello");
     if ($_SERVER['REQUEST_METHOD'] =='POST'){
		 echo("hello");
		 
    $nom = $_POST["nom"];
    $prenom = $_POST["prenom"];
    $Email = $_POST["Email"];
	$tel = $_POST["tel"];
	$region = $_POST["region"];
	$grpsanguin = $_POST["grpsanguin"];
	echo("hello");
	$age = $_POST["age"];
	$datedonation = $_POST["datedonation"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO user (nom, prenom, Email, tel, region, grpsanguin, age, datedonation , password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssississ", $nom, $prenom, $Email, $tel, $region, $grpsanguin, $age, $datedonation , $password);
    mysqli_stmt_execute($statement);
    
      $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
	 }
?>
