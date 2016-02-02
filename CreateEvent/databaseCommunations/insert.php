
 <?php  
 require "init.php";    
 $name = $_POST["name"];  
 $category = $_POST["category"];  
 $location = $_POST["location"];  
 $description = $_POST["description"];  
 $datetime = $_POST["datetime"];  
 
 $sql_query = "insert into event (name, category, location, description, datetime) values('$name','$category','$location','$description','$datetime');"; 
 
 if(mysqli_query($con, $sql_query))
 {
 //echo "Data Insertion Success...";
 }     
 else
 {
 //echo "Data insertion error..";
 }
 
 ?>  