<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $id = $_POST['id'];

    require_once 'connect.php';

    $sql = "SELECT * FROM user WHERE Id='$id' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             $h['name']        = $row['nom'] ;
             $h['prename']        = $row['prenom'] ;
             $h['email']       = $row['Email'] ;
		    $h['tel']       = $row['tel'] ;
             $h['age']       = $row['age'] ;
			  $h['photo']       = $row['photo'] ;
			  $h['points']       = $row['points'] ;

             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($conn);
 }
 
 ?>