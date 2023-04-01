<?php
require_once "data.php";
$find = $_POST['find_m'];
$query = "SELECT * FROM mute_log WHERE LOWER(player) LIKE LOWER('%".$find."%') ORDER BY id DESC;";
$result = mysqli_query($conn, $query);

if (!$result) {
    die("Lekérdezési hiba: " . mysqli_error($conn));
}
$code = json_encode(mysqli_fetch_all($result, MYSQLI_ASSOC));
echo $code;

mysqli_close($conn);
?>