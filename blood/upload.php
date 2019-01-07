<?php
$conn = mysqli_connect("localhost", "root", "", "bloodify");
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	


    $id = $_POST['Id'];
    $photo = $_POST['photo'];

    $path = "profile_image/$id.jpeg";
    $finalPath = "http://192.168.1.6/Blood/".$path;

  

    $sql = "UPDATE user SET photo='$finalPath'  WHERE Id=$id ";

    if (mysqli_query($conn, $sql)) {
        
        if ( file_put_contents( $path, base64_decode($photo) ) ) {
            
            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);
            mysqli_close($conn);

        }

    }
	}

	
	else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}


?>