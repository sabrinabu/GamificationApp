<?php  
 $db_name = "firstdb";  
 $mysql_user = "root";  
 $mysql_pass = "123456";  
 $server_name = "localhost";  
 
 $con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);  
 
 if(!$con){
	 //echo "could not connect";
 }
 else{
	 //echo "database connected";
 }
 ?>  