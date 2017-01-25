package sec;

import java.util.Scanner;
import java.io.*;

public class proOne {
  /**
   * main
   *
   * This function is the main function 
   * takes the input from the user, which is a plain text
   * converts into lower case and sends it to encryt function
   */
  public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
    String plainText, plainTextLowerCase;
    System.out.println("Enter Plain Text:");
    plainText=scanner.nextLine();
    int length;
    length = plainText.length();
    plainTextLowerCase = plainText.toLowerCase();
    encryt(plainTextLowerCase, length);
  }

  /**
   * encryt
   *
   * This function converts into cipher text 
   * a or A -> 0 z or Z -> 25 and 'space' -> 26
   */
  
private static void encryt(String plainTextLowerCase, int length) {
  int[] arra;
  arra = new int[10000];
  int count = 0;
  for (int i=0; i< length; i++){
    char ch = plainTextLowerCase.charAt(i);
    int ascii = (int) ch;
    if(ascii == 32){
      arra[i] = 26;
      count++;
    }
    else{
    int key = (ascii-97);
    arra[i] = key;
    count++;
    }
  }
  System.out.println("Cipher Text:");
  for (int i=0; i < count; i++){
    System.out.print(arra[i] + " ");
  }
}
}
