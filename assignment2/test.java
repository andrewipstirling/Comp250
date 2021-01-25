package assignment2;

import java.math.BigInteger;

public class test {

	public static void myMultiplyTest() {
		   Polynomial pStudent1 = new Polynomial();
		   Polynomial pStudent2 = new Polynomial();
		   
		   pStudent1.addTerm(new Term(4, new BigInteger("2")));     
		   pStudent1.addTerm(new Term(2, new BigInteger("2")));  
		   pStudent1.addTerm(new Term(1, new BigInteger("1"))); 
		   pStudent1.addTerm(new Term(2, new BigInteger("-1")));
		   pStudent1.addTerm(new Term(0, new BigInteger("1")));
		   System.out.println("Polynomial 1: " + pStudent1);
		   
		   
		   
		   pStudent2.addTerm(new Term(4, new BigInteger("5")));
		   pStudent2.addTerm(new Term(3, new BigInteger("4")));
		   pStudent2.addTerm(new Term(2, new BigInteger("3")));
		   pStudent2.addTerm(new Term(1, new BigInteger("2")));
		   pStudent2.addTerm(new Term(0, new BigInteger("1")));
		   
		   System.out.println("Polynomial 2: " + pStudent2);
		   
		   Polynomial mult = Polynomial.multiply(pStudent1,pStudent2);
		   System.out.println(mult);
				   
	   }
	public static void testAdd1() {
		   Polynomial pStudent1 = new Polynomial();
		   Polynomial pStudent2 = new Polynomial();
		   
		   pStudent1.addTerm(new Term(4, new BigInteger("2")));     
		   pStudent1.addTerm(new Term(2, new BigInteger("2")));  
		   pStudent1.addTerm(new Term(1, new BigInteger("1"))); 
		   pStudent1.addTerm(new Term(2, new BigInteger("-1")));
		   pStudent1.addTerm(new Term(0, new BigInteger("1")));
		   System.out.println("Polynomial 1: " + pStudent1);
		   
		   
		   
		   pStudent2.addTerm(new Term(4, new BigInteger("5")));
		   pStudent2.addTerm(new Term(3, new BigInteger("4")));
		   pStudent2.addTerm(new Term(2, new BigInteger("3")));
		   pStudent2.addTerm(new Term(1, new BigInteger("2")));
		   pStudent2.addTerm(new Term(0, new BigInteger("1")));
		   
		   System.out.println("Polynomial 2: " + pStudent2);
		   
		   Polynomial sum = Polynomial.add(pStudent1,pStudent2);
		   System.out.println("Polynomial sum = " + sum);
		   
	   }
}
