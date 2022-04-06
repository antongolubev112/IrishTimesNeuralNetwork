package ie.gmit.sw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;

public class ReadTrainingData {
	private ArrayList<Headline> fileContent = new ArrayList<>();
	//private List<ArrayList<Integer>> headlineVectors; 
	private int vecSize=200;
	private FeatureVectoriser fv;
	private double[][] headlineVector;
	private Scanner sc=null;
	private static TrainingDataFactory tdf=TrainingDataFactory.getInstance();
	
	//read file into an array
	public void readFile(String filePath) {
		String line="";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			sc = new Scanner(fis, "UTF-8");
			while(sc.hasNextLine()) {
				line=sc.nextLine();
				//create a headline object from the line 
				fileContent.add(new Headline(line));

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createNeuralNet();
	}
	
	private void createNeuralNet() {
		tdf.createNN(fileContent,vecSize);
	}

//	private void convert() {
//		fv=new FeatureVectoriser();
//		
//		//initialise vector list
//		headlineVectors= new ArrayList<>(fileContent.size());
//		
//		int i=0;
//		for(Headline h: fileContent) {
//			MLData nnInput= new BasicMLData(fv.hashVectorizer(h, vecSize));
//			MLData nnOutput= new BasicMLData(h.getHashedExpected());
//			MLDataPair dataPair=new BasicMLDataPair(nnInput,nnOutput);
//			headlineVectors.add(i,dataPair);
//			i++;
//		}
//		//shingleHeadline();
//		createNN(createDataSet());
//	}
	
	private void shingleHeadline() {
		//hashVectors();
		
		//createNN(createDataSet());
	}
	
	// adapted from https://en.wikipedia.org/wiki/Feature_hashing
	
//	private void hashVectors() {
//		
//		
//		//initialise vector list
//		headlineVector= new double[headlines.length][vecSize];
//
//		//loop counter
//		int i=0;
//		for(Headline h: headlines) {
//			headlineVector[i]=fv.hashVectorizer(h, vecSize);
//			//perform feature vectorisation on the headlines
//			//createDataPair for each headline
//		}
//
//		//increment counter
//		i++;
//	}
	
	

		
	
	
	//1: Create a fixed size vector
}
