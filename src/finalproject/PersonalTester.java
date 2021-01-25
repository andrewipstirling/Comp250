package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import finalproject.Sorting;

public class PersonalTester {
	
	public static void SortingTest(){
		HashMap<String,Integer> hm1 = new HashMap<String,Integer>();
		hm1.put("six2", 6);
		hm1.put("one",1); 
	    hm1.put( "two",2); 
	    hm1.put("five",5); 
	    hm1.put( "three",3); 
	   
	    hm1.put("four", 4); 
	    hm1.put("six",6);
	    
	    ArrayList<String> list = Sorting.fastSort(hm1);
	    System.out.println(list);
	}
	public static void main(String[] args) throws Exception { 
		//testCyclicVertices();
		//testVisited();
		//testEdges();

		//testXml1();
		SortingTest();
		

	}
	
	public static void testCyclicVertices() {
		try {
			   SearchEngine searchEngine = new SearchEngine("test.xml");
			   searchEngine.crawlAndIndex("www.ea.com");
			   System.out.print("Testing number vertices... ");
			   if (searchEngine.internet.vertexList.size() == 6)
			    System.out.println("Passed \n");
			   else {
			    System.out.println("Error: Number of vertices added during crawling is incorrect.\n");
			   }
			  }
			  catch (Exception e) {
			   e.printStackTrace();
			  }
	}
	private static void testVisited()
	 {
	  
	  int maxScore = 10;
	  int grade = 0;

	  try {
	   SearchEngine searchEngine = new SearchEngine("test.xml");
	   searchEngine.crawlAndIndex("www.cs.mcgill.ca");
	   System.out.print("Testing if vertices are marked while crawling... ");
	   Iterator it = searchEngine.internet.vertexList.entrySet().iterator();
	      while (it.hasNext()) {
	          Map.Entry pair = (Map.Entry)it.next();
	          grade = maxScore;
	          if (!searchEngine.internet.getVisited((String) pair.getKey())) {
	           System.out.println("Error: Vertices are not marked visited during crawling.\n");
	           break;
	          }
	      }
	      
	  System.out.println("Passed \n");    
	   
	  }
	  catch (Exception e) {
	   
	   e.printStackTrace();
	   
	  }

	 }
	public static void testEdges() {
		try {
			   SearchEngine searchEngine = new SearchEngine("test.xml");
			   searchEngine.crawlAndIndex("www.cs.mcgill.ca");
			   ArrayList<String> v = searchEngine.internet.getVertices();
			   int numEdges = 0;
			   System.out.print("Testing if correct number of edges have been added... ");
			   for(String url: v){
			    int outDeg = searchEngine.internet.getOutDegree(url);
			    numEdges+=outDeg;
			   }
			   if(numEdges == 21)
			    System.out.println("Passed \n");
			   else {
			    System.out.println("Error: Number of edges added during crawling is incorrect.\n");
			   }
			   
			  }
			  catch (Exception e) {
			   e.printStackTrace();
			  }
	}
    private static void testXml1() throws Exception{
        System.out.println("----------TESTING test.xml----------");
        SearchEngine searchEngine = new SearchEngine("test.xml");
        searchEngine.crawlAndIndex("www.cs.mcgill.ca");
        searchEngine.assignPageRanks(0.001);
        //the following code only works for xml files given by prof
        /*
        for (String url : searchEngine.internet.getVertices()){
            double expectedRank = searchEngine.parser.getPageRank(url);
            double actualRank = searchEngine.internet.getPageRank(url);
            if (Math.abs(expectedRank-actualRank)>0.001){
                System.out.println("Wrong page rank for " + url);
            }
        }
        */

        ArrayList<String> results = searchEngine.getResults("the");
        int count=1;
        for (String result : results){
            System.out.println(count + ". " + result + " Rank: " + searchEngine.internet.getPageRank(result));
            count++;
        }
        System.out.println();
    }
	
	
}

