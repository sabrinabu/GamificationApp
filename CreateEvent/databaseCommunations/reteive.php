 <?php  
 require "init.php";  
 $user_name = $_POST["sabrina"];
 $user_pass = $_POST["kaynat"]; 
 
 $sql_query = "select * from event";
 $result = mysqli_query($con, $sql_query);  
 
 while($row=mysqli_fetch_assoc($result)){
	$output[]=$row;
 }
 
 print(json_encode($output));
 
 /*if(mysqli_num_rows($result)>0)  
 {    
	echo "</br>";
	$totalRows=0;
	while($row = mysqli_fetch_array($result))
		{
			echo $row['name'] . "\t";
			echo $row['category'] . "\t";
			echo $row['location'] . "\t";
			echo $row['description'] . "\t";
			echo $row['datetime'] . "\t";
			echo "</br>";
		}
 }  
 else  
 {   
	echo "No data.......Try Again..";  
 }*/  
 ?>  