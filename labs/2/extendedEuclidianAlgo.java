/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec;

import java.util.Scanner;

/**
 *
 * @author Anurag
 */
public class extendedEuclidianAlgo {
  /**
   * euclidAlgExt
   * This function performs extended euclidian algorithm 
   */  
  public static void euclidAlgExt(long a, long b){
    long x = 0, y = 1, prex = 1, prey = 0, temp;
    while (b != 0){
      long q = a / b;
      long r = a % b;
      a = b;
      b = r;
      temp = x;
      x = prex - q * x;
      prex = temp;
      temp = y;
      y = prey - q * y;
      prey = temp;            
    }
    long d = euclidAlg( a, b);
    System.out.println(d + " " + prex +" "+ prey);
  }
  /**
   * euclidAlg
   * This function finds the GCD of two input numbers 
   */      
  public static long euclidAlg(long p, long q) {
    if (q == 0) return p;
    else return euclidAlg(q, p % q);
  }
  /**
   * main
   *
   * This function is the main function, 
   * takes the input from the user, of which we need to perform extended 
   * euclidian algorithm
   */ 
  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a, b of d= ax + by \n");
    while (scanner.hasNext()){
      long a = scanner.nextLong();
      long b = scanner.nextLong();
      if(a >= b){
        euclidAlgExt(a,b);
      }
      else{
      long buff;
      buff = a;
      a = b;
      b = buff;
      euclidAlgExt(a,b);
      }        
    }
  }
}
