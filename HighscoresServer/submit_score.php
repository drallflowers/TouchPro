<?php

// highscores are just the top 10 scores
$top = 10; 

// highscores data file
$highscoresFile = "." . DIRECTORY_SEPARATOR . "highscores.dat";

// get the username GET param
$username = "";
if(isset($_GET["username"])){
	$username = $_GET["username"];
}

// get the score GET param
$score = 0;
if(isset($_GET["score"])){
	$score = intval($_GET["score"]);
}

// if username and score are present then see if its a highscore
if($username !== "" && $score > 0){
	// load highscores data file, if file does not exist use an empty json array
	$jsonString = "[]";
	if(file_exists($highscoresFile)){
		$jsonString = file_get_contents($highscoresFile);
	}
	$json = json_decode($jsonString,true);

	// create array in memory to store the parse json values
	$highscores = array();
	
	// iterate over the json results and copy into array
	$numResults = count($json);
	for ($i=0; $i<$numResults; $i++){
		$values = array("username" => $json[$i]["username"], "score" => intval($json[$i]["score"]));
		array_push($highscores, $values);
	}
	
	// if num highscores is less than the num top highscores then add this score
	if($numResults < $top){
		$values = array("username" => $username, "score" => $score);
		array_push($highscores, $values);
	} else {
		// finds the lowest score
		$indexOfLowestScore = 2147483647;
		$lowestScore = 2147483647;
		for($i=0; $i<count($highscores); $i++){
			if($highscores[$i]["score"] < $lowestScore){
				$indexOfLowestScore = $i;
				$lowestScore = $highscores[$i]["score"];
			}
		}
		
		unset($highscores[$indexOfLowestScore]);
		$values = array_values(array("username" => $username, "score" => $score));
		array_push($highscores, $values);
	}
	
	// save the new highscores file
	file_put_contents($highscoresFile, json_encode(array_values($highscores)));
	
	$result = array("success" => "true");
	echo json_encode($result);
} else {
	$result = array("success" => "false");
	echo json_encode($result);
}
?>