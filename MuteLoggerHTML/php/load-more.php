<?php
require_once "data.php";
$offset = $_POST['offset'];
$limit = $_POST['limit'];
$query = "SELECT * FROM mute_log ORDER BY id DESC LIMIT $limit OFFSET $offset;";
$result = mysqli_query($conn, $query);

if (!$result) {
    die("Lekérdezési hiba: " . mysqli_error($conn));
}

$code = json_encode(mysqli_fetch_all($result, MYSQLI_ASSOC));
echo $code;
mysqli_close($conn);
?>