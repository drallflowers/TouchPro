<?php
// highscores data file
$highscoresFile = "." . DIRECTORY_SEPARATOR . "highscores.dat";

// echo out the saved highscores
$jsonString = "[]";
if(file_exists($highscoresFile)){
	$jsonString = file_get_contents($highscoresFile);
}
echo $jsonString;
?>