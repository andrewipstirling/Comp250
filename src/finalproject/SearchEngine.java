package finalproject;

import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		// TODO : Add code here
		if(!(internet.getVisited(url))) {
			addToWordIndex(url);
			internet.addVertex(url);
			internet.setVisited(url, true);
			//System.out.println(url);
			
			ArrayList<String> neighbourUrls = parser.getLinks(url);
			if(neighbourUrls == null) {
				return;
			}
			for(String newUrl:neighbourUrls) {
				
				crawlAndIndex(newUrl);	
				
				internet.addEdge(url, newUrl);
			}
		}
	}

	private void addToWordIndex(String url) {
		ArrayList<String> content = parser.getContent(url);
		if(content ==null) {
			return;
		}
		for(String word:content) {
			if(wordIndex.containsKey(word.toLowerCase())) {
				ArrayList<String> urlList = wordIndex.get(word.toLowerCase());
				if(!(urlList.contains(url))){
					urlList.add(url);
				}
			}
			else {
				ArrayList<String> urlList = new ArrayList<String>();
				urlList.add(url);
				wordIndex.put(word.toLowerCase(), urlList);
			}
		}
	}
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		// TODO : Add code here
		ArrayList<String> vertices = internet.getVertices();
		ArrayList<Double> oldRanks = new ArrayList<Double>(vertices.size());
		
		for(String v:vertices) {
			internet.setPageRank(v, 1.0);
			oldRanks.add(1.0);
		}

		ArrayList<Boolean> bools = new ArrayList<Boolean>(vertices.size());
		for(int j =0; j<vertices.size();j++) {
			bools.add(false);
		}
		
		while(true) {
			
			ArrayList<Double> newRanks = computeRanks(vertices);
			for(int i=0;i<vertices.size();i++) {
				internet.setPageRank(vertices.get(i), newRanks.get(i));
				
				double diff = Math.abs(newRanks.get(i) - oldRanks.get(i));
				if(diff<epsilon) {
					bools.set(i,true);
				}
			}
			if(bools.contains(false)) {
				oldRanks = newRanks;	
			}
			else {
				break;
			}
		}
	}
	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		//TODO: stop being an idiot
		ArrayList<Double> newRanks = new ArrayList<Double>(vertices.size());
		for(int j=0; j<vertices.size();j++) {
			double newRank = 0;
			ArrayList<String> edgesInto = internet.getEdgesInto(vertices.get(j));
			
			for(String vertexIn:edgesInto) {
				double pageRank = internet.getPageRank(vertexIn);
				double outDeg = internet.getOutDegree(vertexIn);
				newRank+= (pageRank/outDeg);
			}
			newRank = 0.5 + 0.5*newRank;
			newRanks.add(newRank);
			}
		
		return newRanks;
	}


	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {

		if(wordIndex.containsKey(query.toLowerCase())) {
			ArrayList<String> urlList = wordIndex.get(query.toLowerCase());
			HashMap<String, Double> vertexRankMap = new HashMap<String, Double>();

			for(String url:urlList) {
				vertexRankMap.put(url, internet.getPageRank(url));
				
			}

			urlList = Sorting.fastSort(vertexRankMap);
			
			System.out.println("After sort");
			return urlList;
		}
		
		else {
			ArrayList<String> urlList = new ArrayList<String>();
			return urlList;
		}
		

	}
}
