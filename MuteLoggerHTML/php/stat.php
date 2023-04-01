<?php
require_once "data.php";

$query = "SELECT staff, COUNT(player) AS 'mute_count' FROM mute_log GROUP BY 1;";
$result = mysqli_query($conn, $query);

if (!$result) {
    die("Lekérdezési hiba: " . mysqli_error($conn));
}
$code = json_encode(mysqli_fetch_all($result, MYSQLI_ASSOC));
echo $code;
mysqli_close($conn);
?>