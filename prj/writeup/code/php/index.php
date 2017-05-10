<?php
    include('/aes-cbc.php');
    echo "string 0";
    $aes = new SH_AES_Cipher();
    
    $key = rand_char('16'); 
    function rand_char($length) {
    $random = '';
    for ($i = 0; $i < $length; $i++) {
    $random .= chr(mt_rand(33, 126));
    }
  return $random;
}

    //$key = '0011223344556677'; //128/192/256 bits
    $plaintext = 'The quick brown fox jumped over the lazy cat';
    $iv ='1111111111111111'; //Random (unpredictable!) IV 128 bits.

    $ciphertext = $aes->encrypt($plaintext,$key,$iv);
    $original =$aes->decrypt($ciphertext,$key,$iv);
    echo "string 1";
    echo $ciphertext;
    echo "string 2";
    echo $original;

 ?>