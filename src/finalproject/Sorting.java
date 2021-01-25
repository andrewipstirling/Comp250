package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort


public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	// ADD YOUR CODE HERE
    	ArrayList<K> sortedUrls = new ArrayList<K>();
    	if(results.size()==0) {
    		return sortedUrls;
    	}
    	sortedUrls.addAll(results.keySet());
    	quickSort(results, sortedUrls, 0, sortedUrls.size()-1);
    	
    	
    	return sortedUrls;
    }
    
    private static <K,V extends Comparable> void quickSort(HashMap<K,V> results, ArrayList<K> myList, int a, int b){
    	
    	if(a>=b) {
    		return;
    	}
    	int med = (a+b)/2;
    	
    	V pivotValue = results.get(myList.get(med));
    	int left = a;
    	int right= b;
    	while(true) {

    		while(results.get(myList.get(left)).compareTo(pivotValue)> 0) {
    			left++;
    		}
    		while(results.get(myList.get(right)).compareTo(pivotValue)< 0) {
    			right--;
    		}
    		if(left>=right) {
    			break;
    		}
    		swap(myList,left,right);
    		left++;
    		right--;
    		
    	}
    	quickSort(results,myList, a, right);
    	quickSort(results,myList,right+1,b);
    	
    	
    }
    
    private static <K> void swap(ArrayList<K> sortedUrls, int i, int j) {
    	K ele = sortedUrls.get(i);
    	sortedUrls.set(i, sortedUrls.get(j));
    	sortedUrls.set(j, ele);
    }
}