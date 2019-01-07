<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $Email = $_REQUEST['Email'];
    $password = $_REQUEST['password'];

    require_once 'connect.php';

    $sql = "SELECT * FROM user WHERE Email='" . $Email . "' AND password='" . $password . "' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['Login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( $password == $row['password'] ) {
            
            $index['nom'] = $row['nom'];
            $index['Email'] = $row['Email'];
			$index['id'] = $row['Id'];
           

            array_push($result['Login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }

}

?>