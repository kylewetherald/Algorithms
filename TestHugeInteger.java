///
/// Contents: Test driver for HugeInteger class.
/// Author:   John Aronis
/// Date:     December 2012
///

/// This program runs a battery of tests for the HugeInteger class and
/// compares results to Java's BigInteger class.

import java.math.BigInteger ;
import java.util.Random ;

public class TestHugeInteger {

  public static int START     = 1 ;
  public static int STOP      = 21 ;
  public static int INCREMENT = 10 ;

  public static void main(String[] args) {
    String a, b ;
    BigInteger biga, bigb, bigc, bigd ;
    HugeInteger hugea, hugeb, hugec, huged ;
    boolean correct = true ;
    for (int digits=START ; digits<=STOP ; digits+=INCREMENT) {

      System.out.println("DIGITS = " + digits) ;

      // Create data:
      a = makeHugeInteger(digits) ;
      b = makeHugeInteger(digits) ;
      // a = "0" ;
      // b = "0" ;
      biga = new BigInteger(a,10) ;
      bigb = new BigInteger(b,10) ;
      hugea = new HugeInteger(a) ;
      hugeb = new HugeInteger(b) ;

      // Addition:
      HugeInteger.DIGIT_OPERATIONS = 0 ;
      bigc = biga.add(bigb) ;
      //System.out.println("bigsum:  " + bigc);
      hugec = hugea.add(hugeb) ;
      //System.out.println("hugesum: " + hugec);
      if ( !bigc.toString().equals(hugec.toString()) ) 
      {
      	correct=false ;
      	System.out.println("** ADD FAIL **");	
      }
      System.out.println("  Addition operations/digits: " + HugeInteger.DIGIT_OPERATIONS/(float)digits) ;

      // Multiplication:
      HugeInteger.DIGIT_OPERATIONS = 0 ;
      bigc = biga.multiply(bigb) ;
     // System.out.println("    " + bigc);
      hugec = hugea.multiply(hugeb) ;
      //System.out.println("    " + hugec);
      if ( !bigc.toString().equals(hugec.toString()) ) 
      {
      	correct=false ;
      	System.out.println("** MULT FAIL **");
      }
      System.out.println("  Multiplication operations/digits^2: " + HugeInteger.DIGIT_OPERATIONS/(float)(digits*digits)) ;

      
      // Comparison:
      HugeInteger.DIGIT_OPERATIONS = 0 ;
      if ( biga.compareTo(bigb)!=hugea.compareTo(hugeb) ) 
      {
      correct=false ;
      System.out.println("** COMP FAIL **");
      }
      System.out.println("  Comparison operations/digits: " + HugeInteger.DIGIT_OPERATIONS/(float)digits) ;
	
      // Subtraction:
      if (biga.compareTo(bigb)<0) { 
      	//System.out.println("flipflop!");
      	bigc=biga ; biga=bigb ; bigb=bigc ; hugec=hugea ; hugea=hugeb ; hugeb=hugec ; }
      HugeInteger.DIGIT_OPERATIONS = 0 ;
      bigc = biga.subtract(bigb) ;
      //System.out.println("bigdifference:  " + bigc);
      hugec = hugea.subtract(hugeb) ;
      //System.out.println("hugedifference: " + hugec);
      if ( !bigc.toString().equals(hugec.toString()) ) 
      {
      	correct=false ;
      	System.out.println("** SUBT FAIL **");
      }
      System.out.println("  Subtraction operations/digits: " + HugeInteger.DIGIT_OPERATIONS/(float)digits) ;
      // Fast Multiplication:
      System.out.println(a +" * "+ b);
      HugeInteger.DIGIT_OPERATIONS = 0 ;
      bigc = biga.multiply(bigb) ;
      //System.out.println("    " + bigc);
      hugec = hugea.fastMultiply(hugeb) ;
      //System.out.println("    " + hugec);
      if ( !bigc.toString().equals(hugec.toString()) )
      {
      	 correct=false ;
         System.out.println("** FAST MULT FAIL **\n"+ bigc +"\n"+ hugec);
      }
      System.out.println("  Fast Multiplication operations/digits^2: " + HugeInteger.DIGIT_OPERATIONS/(float)(digits*digits)) ;
      System.out.println("  Fast Multiplication operations/digits^1.6: " + HugeInteger.DIGIT_OPERATIONS/Math.pow(digits,1.6f) +"\n") ;


      // Oops!
      //if (!correct) { System.out.println("ERROR: " + a + " " + b) ; }

    }

  }

  public static String makeHugeInteger(int numberOfDigits) {
    String result = "" ;
    Random R = new Random() ;
    for (int i=0 ; i<numberOfDigits ; i++) { result = result + R.nextInt(10) ; }
    return result ;
  }

}

/// End-of-File
