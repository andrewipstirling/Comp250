package assignment3;



import assignment3.DogShelter.DogNode;
import java.util.ArrayList;

public class MyTester {
	public static void main(String[] args) {
		//shelterTest1();
		//adoptTest1();
		//adoptTest2();
		//iteratorTest1();
		//findDogToAdoptTest1();
		//vetScheduleTest1();
		//fullTest();
		codePost();
	}
	
	public static void shelterTest1() {
		
		Dog R = new Dog("Rex", 8, 100, 5, 50.0);
		DogShelter myShelter = new DogShelter(R);
		
		//System.out.println(myShelter.root);
		Dog S = new Dog("Stella", 5, 110, 2, 250.0);
		myShelter.shelter(S);
		System.out.println(myShelter.root);
		
		
		Dog P = new Dog("Poldo", 4, 140, 1, 35.0);
		myShelter.shelter(P);
		System.out.println(myShelter.root);
		
	}
	public static void adoptTest1() {
		Dog R = new Dog("Rex", 8, 100, 5, 50.0);
		DogShelter myShelter = new DogShelter(R);
		
		//System.out.println(myShelter.root);
		Dog S = new Dog("Stella", 5, 110, 2, 250.0);
		myShelter.shelter(S);
		
		
		Dog P = new Dog("Poldo", 4, 140, 1, 35.0);
		myShelter.shelter(P);
		System.out.println(myShelter.root);
		
		myShelter.adopt(P);
		System.out.println(myShelter.root);
	}
	public static void adoptTest2() {
		Dog R = new Dog("Rex", 8, 45, 5, 50.0);
		Dog S = new Dog("Stella", 5, 50, 2, 250.0);
		Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
		Dog P = new Dog("Poldo", 10, 35, 1, 35.0);
		Dog B = new Dog("Bella", 1, 40, 15, 120.0);
		Dog C = new Dog("Cloud", 4, 10, 23, 80.0);
		Dog A = new Dog("Archie", 6, 120, 18, 40.0);
		Dog D = new Dog("Daisy", 7, 15, 12, 35.0);
		DogShelter myShelter = new DogShelter(R);
		myShelter.shelter(S);
		myShelter.shelter(L);
		myShelter.shelter(P);
		myShelter.shelter(B);
		myShelter.shelter(C);
		myShelter.shelter(A);
		myShelter.shelter(D);
		System.out.println(myShelter.root);
		myShelter.adopt(A);
		System.out.println(myShelter.root);
	}
	public static void findDogToAdoptTest1() {
		Dog R = new Dog("Rex", 8, 45, 5, 50.0);
		Dog S = new Dog("Stella", 5, 50, 2, 250.0);
		Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
		Dog P = new Dog("Poldo", 10, 35, 1, 35.0);
		Dog B = new Dog("Bella", 1, 40, 15, 120.0);
		Dog C = new Dog("Cloud", 4, 10, 23, 80.0);
		Dog A = new Dog("Archie", 6, 120, 18, 40.0);
		Dog D = new Dog("Daisy", 7, 15, 12, 35.0);
		DogShelter myShelter = new DogShelter(R);
		myShelter.shelter(S);
		myShelter.shelter(L);
		myShelter.shelter(P);
		myShelter.shelter(B);
		myShelter.shelter(C);
		myShelter.shelter(A);
		myShelter.shelter(D);
		Dog adopted = myShelter.findDogToAdopt(2, 4);
		System.out.println(adopted);
	}
	
	public static void iteratorTest1() {
		Dog R = new Dog("Rex", 8, 45, 5, 50.0);
		Dog S = new Dog("Stella", 5, 50, 2, 250.0);
		Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
		Dog P = new Dog("Poldo", 10, 35, 1, 35.0);
		Dog B = new Dog("Bella", 1, 40, 15, 120.0);
		Dog C = new Dog("Cloud", 4, 10, 23, 80.0);
		Dog A = new Dog("Archie", 6, 120, 18, 40.0);
		Dog D = new Dog("Daisy", 7, 15, 12, 35.0);
		DogShelter myShelter = new DogShelter(R);
		myShelter.shelter(S);
		myShelter.shelter(L);
		myShelter.shelter(P);
		myShelter.shelter(B);
		myShelter.shelter(C);
		myShelter.shelter(A);
		myShelter.shelter(D);
		System.out.println(myShelter.root);
		
		for(Dog d: myShelter) {
			System.out.println(d.toString());
		}		
	}
	
	public static void vetScheduleTest1() {
		Dog R = new Dog("Rex", 8, 45, 8, 50.0);
		Dog S = new Dog("Stella", 5, 50, 2, 250.0);
		Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
		Dog P = new Dog("Poldo", 10, 35, 1, 35.0);
		
		DogShelter myShelter = new DogShelter(R);
		//myShelter.shelter(S);
		//myShelter.shelter(L);
		//myShelter.shelter(P);
		ArrayList<ArrayList<Dog>> list = myShelter.getVetSchedule();
		System.out.println(list);
		
	}
	public static void fullTest() {
		Dog R = new Dog("Rex", 8, 100, 5, 50.0);
		Dog S = new Dog("Stella", 5, 50, 2, 250.0);
		Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
		Dog P = new Dog("Poldo", 10, 60, 1, 35.0);
		Dog B = new Dog("Bella", 1, 55, 15, 120.0);
		Dog C = new Dog("Cloud", 4, 10, 23, 80.0);
		Dog A = new Dog("Archie", 6, 120, 18, 40.0);
		Dog D = new Dog("Daisy", 7, 15, 12, 35.0);
		DogShelter s = new DogShelter(R);
		s.shelter(S);
		
		s.shelter(L);
		s.adopt(R);
		s.shelter(R);
		s.shelter(P);
		s.adopt(R);
		s.shelter(R);
		s.shelter(B);
		s.shelter(C);
		s.shelter(A);
		s.shelter(D);
		System.out.println(s.findOldest());
		System.out.println(s.findYoungest()); // displays Bella(1 , 55)
		System.out.println(s.findDogToAdopt(3, 5)); // displays Lessie(3 , 70)
		System.out.println(s.budgetVetExpenses(15)); // displays 515.0
		System.out.println(s.getVetSchedule());
		s.adopt();
		System.out.println(s.root);
	}
	public static void codePost() {
	      Dog L = new Dog("Lessie", 3, 70, 9, 25.0);
	      Dog R = new Dog("Rex", 8, 100, 5, 50.0);
			Dog S = new Dog("Stella", 5, 50, 2, 250.0);
			Dog P = new Dog("Poldo", 10, 60, 1, 35.0);
			Dog B = new Dog("Bella", 1, 55, 15, 120.0);
			Dog C = new Dog("Cloud", 4, 10, 23, 80.0);
			Dog A = new Dog("Archie", 6, 120, 18, 40.0);
			Dog D = new Dog("Daisy", 7, 15, 12, 35.0);

	      DogShelter test = new DogShelter( A );

	      DogShelter.DogNode ll = test.new DogNode( L );
	      DogShelter.DogNode rr = test.new DogNode( R );
	      DogShelter.DogNode ss = test.new DogNode( S );
	      DogShelter.DogNode pp = test.new DogNode( P );
	      DogShelter.DogNode bb = test.new DogNode( B );
	      DogShelter.DogNode cc = test.new DogNode( C );
	      DogShelter.DogNode aa = test.new DogNode( A );
	      DogShelter.DogNode dd = test.new DogNode( D );

	      test.root.younger = ll;
	      test.root.younger.parent = test.root;
	      test.root.older = rr;
	      test.root.older.parent = test.root;

	      test.root.younger.younger = bb;
	      test.root.younger.younger.parent = ll;
	      test.root.younger.older = ss;
	      test.root.younger.older.parent = ll;

	      test.root.younger.older.younger = cc;
	      test.root.younger.older.younger.parent = ss;

	      test.root.older.younger = dd;
	      test.root.older.younger.parent = rr;
	      test.root.older.older = pp;
	      test.root.older.older.parent = rr;

	      System.out.print( "Testing adopt() #3... " );

	      test.adopt( L );
	      test.adopt( B );
	      test.adopt( P );

	      boolean dogs = test.root.d == A && test.root.younger.d == S && test.root.older.d == R && 
	                      test.root.younger.younger.d == C && test.root.older.younger.d == D;

	      boolean nulls = test.root.parent == null && test.root.older.older == null && test.root.older.younger.younger == null
	                      && test.root.older.younger.older == null  && test.root.younger.older == null 
	                      && test.root.younger.younger.younger == null && test.root.younger.younger.older == null;

	      boolean parents = test.root.younger.parent.d == A && 
	                        test.root.older.parent.d == A &&
	                        test.root.older.younger.parent.d == R &&
	                        test.root.younger.younger.parent.d == S;

	   	if( !( dogs && nulls && parents ) )
	      {
	      	if( !dogs ) throw new AssertionError( "Dogs are not assigned correctly" );
	      	else if( !nulls ) throw new AssertionError( "Null values are not assigned correctly" );
	      	else if( !parents ) throw new AssertionError( "Parent pointers are not assigned correctly" );
	      }

	     	System.out.println("Passed.");
	}
	
		
		
		
		
	
}
	

