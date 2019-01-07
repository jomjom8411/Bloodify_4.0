<?php
//host
$host = "localhost";
//user name
$username = "root";
//database password
$pwd = "";
//database name.
$db = "Bloodify";

$con=mysqli_connect($host,$username,$pwd,$db) or die("Unable to Connect");

if(mysqli_connect_error($con))
{
	echo "Failed to connect";
}

$id = $_GET['Id'];
$query=mysqli_query($con,"SELECT * FROM post  where Id=$id  ");

if($query)
{
while($row=mysqli_fetch_array($query))
	{
	$flag[]=$row;
	}
print(json_encode($flag));
}
mysqli_close($con);
?>


