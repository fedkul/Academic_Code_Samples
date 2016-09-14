package babyNames;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


public class Markov {
	// Hashmap
	public static Hashtable<String, ArrayList<String>> markovChain = new Hashtable<String, ArrayList<String>>();
	static Random rnd = new Random();
	
	
	/*
	 * Main constructor
	 */
	public Markov(String file) throws IOException {
		
		// Create the first two entries (k:_start, k:_end)
		markovChain.put("_start", new ArrayList<String>());
		markovChain.put("_end", new ArrayList<String>());
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        addWord(line);
		    }
		}
		
	}
	
	/*
	 * Add words
	 */
	public void addWord(String word) {
		word = "__"+word+"__";
		// put each word into an array
		ArrayList<String> letters = new ArrayList<String>();
		String[] tmp = word.split("");
		for (int i = 0; i < tmp.length; i++){
			letters.add(tmp[i]);
		}
		String prevPair = "__";
		// Loop through each letter pair, check if it's already added
		// if its added, then get the suffix vector and add the word
		// if it hasn't been added then add the letter pair to the list
		// if its the first or last pair then select the _start / _end key
		ArrayList<String> startletters = markovChain.get("_start");
		startletters.add(prevPair);
		
		for (int i=2; i<letters.size(); i++) {
			String next = letters.get(i);
			//System.out.println(twoPair);
						
			// Add the start and end letters to their own
			if (i == 2) {
				
				ArrayList<String> suffix = markovChain.get(prevPair);
				if (suffix == null) {
					suffix = new ArrayList<String>();
					suffix.add(next);
					markovChain.put(prevPair, suffix);
				}
				else{
					suffix.add(next);
					markovChain.put(prevPair, suffix);
				}
				
			} else if (i == letters.size()) {
				ArrayList<String> endletters = markovChain.get("_end");
				endletters.add(prevPair);				
			} else {	
				ArrayList<String> suffix = markovChain.get(prevPair);
				if (suffix == null) {
					suffix = new ArrayList<String>();
					suffix.add(next);
					markovChain.put(prevPair, suffix);
				} else {
					suffix.add(next);
					markovChain.put(prevPair, suffix);
				}
			}
			prevPair = prevPair.substring(1) + next;
		}
	}
	
	
	/*
	 * Generate a markov name
	 */
	public void generateName(int min, int max) {
		
		// Vector to hold the phrase
		String name = "";
		
		// String for the next letter
		String next = "";
				
		// Select the first word
		ArrayList<String> startLetters = markovChain.get("__");
		int startWordsLen = startLetters.size();
		next = "_"+startLetters.get(rnd.nextInt(startWordsLen));
		
		
		char endCond = next.charAt(next.length()-1);
		// Keep looping through the letters until we've reached the end
		while (endCond != '_') {
			name = name + next.charAt(1);
			ArrayList<String> letterSelection = markovChain.get(next);
			int wordSelectionLen = letterSelection.size();
			next = next.charAt(1)+letterSelection.get(rnd.nextInt(wordSelectionLen));
			endCond = next.charAt(next.length()-1);
		}
		if(name.length() <= max && name.length() >= min){
			System.out.println("New name: " + name);	
		}
		else{
			generateName(min, max);
		}	
	}
}