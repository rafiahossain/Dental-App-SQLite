<?php

if (isset($_POST["email"]) && isset($_POST["code"])){
    $to = $__POST["email"];
    $subject="Verify Code";
    $message = "Your verification code: " .$_POST["code"];
    mail($to, $subject, $message);
    echo "Sent Successfully";
}

?>