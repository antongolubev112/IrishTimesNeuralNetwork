package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTrainingData {
	private ArrayList<String> fileContent = new ArrayList<String>();
	private Headline[] headlines;
	
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
	
	private void convert() {
		headlines = new Headline[fileContent.size()];
		for(int i = 0; i < fileContent.size(); i++) {
			headlines[i] = new Headline(fileContent.get(i));
		}
	}
}
