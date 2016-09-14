package babyNames;

import java.io.*;

public class main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Markov maleNames = new Markov("namesBoys.txt");
		Markov femaleNames = new Markov("namesGirls.txt");
		System.out.print("Welcome to the Markov Baby Name Generator!\n");
		String gender = "";
		while(true){
			System.out.print("What gender of name are you looking for? (male or female)");
			gender = br.readLine();
			if (!(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female")))
				System.out.println("That is not a compatible gender, sorry!");
			else break;
		}
		
		
		System.out.print("How long would you like the name to be?\n"
				+ "Minimum: ");
		int min = Integer.parseInt(br.readLine());
		System.out.print("Maximum: ");
		int max = Integer.parseInt(br.readLine());
		System.out.print("How many names would you like to generate? ");
		int amount = Integer.parseInt(br.readLine());
		
		System.out.print("Here is your list of generated names:\n");
		if(gender.toLowerCase().equals("male")){
			for(int i = 0; i < amount; i++){
				maleNames.generateName(min, max);
			}
		}
		else{
			for(int i = 0; i < amount; i++){
				femaleNames.generateName(min, max);
			}
		}

	}

}
