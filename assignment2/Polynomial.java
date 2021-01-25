package assignment2;

import java.math.BigInteger;
import java.util.Iterator;

public class Polynomial implements DeepClone<Polynomial> 
{
 private SLinkedList<Term> polynomial;
 
 public int size() { 
	 return polynomial.size();
 	}
 private Polynomial(SLinkedList<Term> p) {
	 polynomial = p;
 	}
 
 public Polynomial() {
	 polynomial = new SLinkedList<Term>();
 	}
 
 // Returns a deep copy of the object.
 public Polynomial deepClone(){ 
  
	return new Polynomial(polynomial.deepClone());
 }
 /* 
  * TODO: Add new term to the polynomial. Also ensure the polynomial is
  * in decreasing order of exponent.
  */
 
 private SLinkedList<Term> getPolynomial(){
	 return this.polynomial;
 }
 
 public void addTerm(Term t)
 { 
  /**** ADD CODE HERE ****/
  
  // Hint: Notice that the function SLinkedList.get(index) method is O(n), 
  // so if this method were to call the get(index) 
  // method n times then the method would be O(n^2).
  // Instead, use a Java enhanced for loop to iterate through 
  // the terms of an SLinkedList.
  /*
  for (Term currentTerm: polynomial)
  {
   // The for loop iterates over each term in the polynomial!!
   // Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
  }
  */
	 if (this.size() == 0) {
		 polynomial.addFirst(t);
		 return;
	 }
	 
	 int lastIndex = this.size() - 1;
	 /*
	 if (t.getExponent()<polynomial.get(lastIndex).getExponent()) {
		 polynomial.addLast(t);
		 return;
	 }
	 */
	 int position = 0;
	 for (Term polyTerm : polynomial) {
		 if (t.getExponent() == polyTerm.getExponent()) {
			 BigInteger sum;
			 sum = t.getCoefficient().add(polyTerm.getCoefficient());
			 
			 if (sum.equals(new BigInteger("0"))) { // if they add up to zero
				 polynomial.remove(position);
			 }
			 else {
				 polyTerm.setCoefficient(sum); //updates polynomial
			 }
			
			 return;
		 }
		 if (t.getExponent() > polyTerm.getExponent()) {
			 polynomial.add(position, t);
			 return;
		 }
		 if(position == lastIndex && t.getExponent()<polyTerm.getExponent()) {
			 polynomial.addLast(t);
		 }
		 
		 position++;
	 }
 }
 
 public Term getTerm(int index)
 {
  return polynomial.get(index);
 }
 private void addToEnd(Term t) {
	 polynomial.addLast(t); 
 }
 
 
 //TODO: Add two polynomial without modifying either
 public static Polynomial add(Polynomial p1, Polynomial p2)
 {
  /**** ADD CODE HERE ****/
	 Polynomial myPoly = new Polynomial();
	 //Edge Cases
	 if (p1.size() == 0) {
		 myPoly = p2.deepClone();
		 return myPoly;
	 }
	 if (p2.size() ==0) {
		 myPoly = p1.deepClone();
		 return myPoly;
	 }
	 
	 SLinkedList<Term> p1Poly = p1.getPolynomial();
	 Iterator<Term> p1Node = p1Poly.iterator();
	 
	 SLinkedList<Term> p2Poly = p2.getPolynomial();
	 Iterator<Term> p2Node = p2Poly.iterator();
	 
	 
	 Term p1Term = p1Node.next();
	 Term p2Term = p2Node.next();
	 while (p1Term!=null && p2Term!=null) {

		 if (p1Term.getExponent()> p2Term.getExponent()) {
			 myPoly.addTerm(p1Term);
			 if (p1Node.hasNext()) {
				 p1Term = p1Node.next();		 
			 }
			 else {
				 p1Term = null;
			 }
		 }
		 else if (p2Term.getExponent() > p1Term.getExponent()) {
			 myPoly.addTerm(p2Term);
			 if(p2Node.hasNext()) {
				 p2Term = p2Node.next();
			 }
			 else {
				 p2Term = null;
			 }
		 }
		 
		 else { //can add
			 BigInteger sum = p1Term.getCoefficient().add(p2Term.getCoefficient());
			 Term tmp = new Term(p2Term.getExponent(), sum);
			 myPoly.addToEnd(tmp);
			 if (p1Node.hasNext()) {
				 p1Term = p1Node.next();	 
			 }
			 else {
				 p1Term = null;
			 }
			 if(p2Node.hasNext()) {
				 p2Term = p2Node.next();
			 }
			 else {
				 p2Term = null;
			 }
				 
			 	}
	 }
	//remaining terms
	 while(p1Term != null) { 
		 myPoly.addToEnd(p1Term);
		 if(p1Node.hasNext()) {
			 p1Term = p1Node.next();
		 }
		 else {
			 p1Term = null;
		 }
	 }
	 
	 while(p2Term != null) {
		 myPoly.addToEnd(p2Term);
		 if(p2Node.hasNext()) {
			 p2Term = p2Node.next();
		 }
		 else {
			 p2Term = null;
		 }
	 }
	 return myPoly;
	 }
	 
private static BigInteger getCoeffMultiple(BigInteger b1, BigInteger b2) {
	 BigInteger tmpCoeff;
	 tmpCoeff = b1.multiply(b2);
	 return tmpCoeff;
}
 //TODO: multiply this polynomial by a given term.
 public void multiplyTerm(Term t){ 
	 
	 for(Term polyTerm:polynomial) {
		 int tmpExp = polyTerm.getExponent();
		 tmpExp+= t.getExponent();
		 BigInteger tmpCoeff;
		 tmpCoeff = getCoeffMultiple(polyTerm.getCoefficient(), t.getCoefficient());
		 polyTerm.setExponent(tmpExp);
		 polyTerm.setCoefficient(tmpCoeff);
	 }
 }
 private static void addToArray(BigInteger[] arr, int pos, BigInteger coeff) {
	 if (arr[pos]== null) {
		 arr[pos] = coeff;
	 }
	 else {
		 arr[pos]= arr[pos].add(coeff);
	 }
 }
 private static int getExponentSum(Term t1, Term t2) {
	 int sum = t1.getExponent() + t2.getExponent();
	 return sum;
 }
 //TODO: multiply two polynomials
 public static Polynomial multiply(Polynomial p1, Polynomial p2)
 {
  /**** ADD CODE HERE ****/
	 
	 SLinkedList<Term> p1Poly = p1.getPolynomial();
	 Term p1FirstTerm = p1Poly.get(0);
	 
	 
	 SLinkedList<Term> p2Poly = p2.getPolynomial();
	 Term p2FirstTerm = p2Poly.get(0);
	 
	 
	 int maxLength = getExponentSum(p1FirstTerm,p2FirstTerm);
	 BigInteger[] coeffArray = new BigInteger[maxLength+1];
	 
	 for (Term t1: p1Poly) {
		 
		 for(Term t2: p2Poly) {
			 int exp = getExponentSum(t1, t2);
			 BigInteger coeff = getCoeffMultiple(t1.getCoefficient(), t2.getCoefficient());
			 addToArray(coeffArray, exp, coeff);
		 }
	 }
	 Polynomial myPoly = new Polynomial();
	 for (int i = maxLength; i>=0; i--) {
		if (coeffArray[i] != null) {
			Term tmp = new Term(i, coeffArray[i]);
			myPoly.addToEnd(tmp);
		}
	 }

  return myPoly;
 }
 
 // TODO: evaluate this polynomial.
 // Hint:  The time complexity of eval() must be order O(m), 
 // where m is the largest degree of the polynomial. Notice 
 // that the function SLinkedList.get(index) method is O(m), 
 // so if your eval() method were to call the get(index) 
 // method m times then your eval method would be O(m^2).
 // Instead, use a Java enhanced for loop to iterate through 
 // the terms of an SLinkedList.

 public BigInteger eval(BigInteger x)
 {
  /**** ADD CODE HERE ****/
	 if(polynomial.size() ==1) {
		 Term myTerm = polynomial.get(0);
		 
		 x = x.pow(myTerm.getExponent());
		 x= x.multiply(myTerm.getCoefficient());
		 return x;
	 }
	 
	 Term myTerm = polynomial.removeFirst();
	 BigInteger coeff = myTerm.getCoefficient();
	 int pastDeg = myTerm.getExponent();
	 
	 for(Term t: polynomial) {
		 
		 if( t.getExponent() == pastDeg-1) {
			 coeff = coeff.multiply(x);
			 coeff = coeff.add(t.getCoefficient());
			 pastDeg--;
		 }
		 else {
			 int diff = pastDeg - t.getExponent()-1;
			 coeff = coeff.multiply(x);
			 BigInteger tmp = x.pow(diff);
			 coeff = coeff.multiply(tmp);
			 coeff = coeff.add(t.getCoefficient());
			 pastDeg = pastDeg-diff-1;
		 }
		 
		 
	 }
	 if(pastDeg!= 0) {
		 BigInteger tmp = x.pow(pastDeg);
		 coeff = coeff.multiply(tmp);
	 }
	 polynomial.addFirst(myTerm);
	 
  return coeff;
 }
 
 // Checks if this polynomial is a clone of the input polynomial
 public boolean isDeepClone(Polynomial p)
 { 
  if (p == null || polynomial == null || p.polynomial == null || this.size() != p.size())
   return false;

  int index = 0;
  for (Term term0 : polynomial)
  {
   Term term1 = p.getTerm(index);

   // making sure that p is a deep clone of this
   if (term0.getExponent() != term1.getExponent() ||
     term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)  
    return false;

   index++;
  }
  return true;
 }
 
 // This method blindly adds a term to the end of LinkedList polynomial. 
 // Avoid using this method in your implementation as it is only used for testing.
 public void addTermLast(Term t)
 { 
  polynomial.addLast(t);
 }
 
 
 @Override
 public String toString()
 { 
  if (polynomial.size() == 0) return "0";
  return polynomial.toString();
 }
 /*
 private class Stack<E extends DeepClone<E>> extends SLinkedList<E>{
	 private Stack<E> myStack;
	 
	 Stack(){
		 myStack = null;
	 }
	 
	 Stack(SLinkedList<E> list){
		 for(E t: list) {
			myStack.push(t); 
		 }
	 }
	 
	 public void push(E data) {
		 myStack.addLast(data);
	 }
	 public E pop() {
		 return myStack.removeLast(); 
	 }
	 public E peek() {
		 
	 }
	 //Overrride
	 public boolean isEmpty() {
		 
	 }
 }
*/
}

 
