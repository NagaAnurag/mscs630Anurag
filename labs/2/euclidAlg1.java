/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Anurag
 */
public class euclidAlg1 {
   /**
   * euclidAlg
   *
   * This function finds the GCD of two input numbers
   */  
  public static long euclidAlg(long p, long q) {
    if (q == 0) return p;
    else return euclidAlg(q, p % q);
    }
   /**
   * main
   *
   * This function is the main function 
   * takes the input from the user, of which we need to find the GCD
   */
public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in); 
    System.out.println("Enter the values of p and q:");
    while (scanner.hasNext()){
      long p = scanner.nextLong();
      long q = scanner.nextLong();
      long d  = euclidAlg(p, q);
      System.out.println("gcd(" + p + ", " + q + ") = " + d);
    }    
  }
}

