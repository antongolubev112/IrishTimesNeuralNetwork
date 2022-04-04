package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadTrainingData {
	private ArrayList<String> fileContent = new ArrayList<>();
	private Headline[] headlines;
	private static Shingler s=Shingler.getInstance();
	private List<ArrayList<Integer>> vectors; 
	private int vecSize=2000;
	
	//read file into an array
	public void readFile(String filePath) {
		String line="";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while((line = br.readLine())!=null) {
				fileContent.add(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convert();
	}
	
	public Headline[] getHeadlines() {
		return headlines;
	}

	public void setHeadlines(Headline[] headlines) {
		this.headlines = headlines;
	}

	private void convert() {
		headlines = new Headline[fileContent.size()];
		for(int i = 0; i < fileContent.size(); i++) {
			headlines[i] = new Headline(fileContent.get(i));
		}
		shingleHeadline();
		
	}
	
	private void shingleHeadline() {
		for(int i = 0; i < headlines.length; i++) {
			headlines[i].setShingledHeadline(s.createShingle(headlines[i].getHeadline(),3));
		}
		hashingVectorizer();
	}
	
	// adapted from https://en.wikipedia.org/wiki/Feature_hashing
	
	private void hashingVectorizer() {
		ArrayList<Integer> vec ;
		vectors= new ArrayList<>(headlines.length);

		//loop counter
		int i=0;
		for(Headline h: headlines) {
			//create a new arraylist, initialise all values with 0
			vec= new ArrayList<Integer>(Collections.nCopies(vecSize, 0));
			
			//hash the date
			int dateHash=h.getDate().hashCode();
			for(Integer shingle: h.getShingledHeadline()) {
				//set the index to be the shingle to be the remainder of the hashcode / size of the vector
				//increment the value at that index by one
				System.out.println("shingle: "+shingle);
				System.out.println("vector value: "+vec.get(Math.abs(shingle) % vecSize));
				vec.set(Math.abs(shingle)% vecSize, vec.get(Math.abs(shingle) % vecSize)+1);
				System.out.println("vector value: "+vec.get(Math.abs(shingle) % vecSize));
			}
			vec.set(Math.abs(dateHash)%vecSize, vec.get(dateHash % vecSize)+1);
			vectors.add(i, vec);
			i++;
		}
		
	}
	
	//1: Create a fixed size vector
}
