package ie.gmit.sw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;

public class ReadCSV {
	private ArrayList<MLDataPair> data; 
	private Scanner sc = null;
	private FeatureVectoriser fv;
	private int vecSize;
	private int shingleSize;
	
	
	
	public ReadCSV(int vecSize, int shingleSize) {
		this.data = new ArrayList<>();
		this.fv = new FeatureVectoriser();
		this.vecSize = vecSize;
		this.shingleSize = shingleSize;
	}

	public MLDataSet readFile(String filePath) {
		String line = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			sc = new Scanner(fis, "UTF-8");
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				// create a headline object from the line
				//fileContent.add(new Headline(line));
				createDataPairs(line);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createdDataset();
	}
	
	private void createDataPairs(String line) {
		Headline headline= new Headline(line,shingleSize);
		
		MLData input= new BasicMLData(fv.hashVectorizer(headline,vecSize));
		MLData expected= new BasicMLData(fv.expectedVectorizer(headline, 90));
		MLDataPair dataPair= new BasicMLDataPair(input,expected);
		data.add(dataPair);
		
	}
	
	private MLDataSet createdDataset() {
		MLDataSet dataSet= new BasicMLDataSet(data);
		return dataSet;
	}
}
