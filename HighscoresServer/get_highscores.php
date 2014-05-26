<?php
// highscores data file
$highscoresFile = "." . DIRECTORY_SEPARATOR . "highscores.dat";

// echo out the saved highscores
echo $jsonString = file_get_contents($highscoresFile);
?>