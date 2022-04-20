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
	private static Encoder map=Encoder.getInstance();
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

	// read data from file
	public MLDataSet readFile(String filePath) {
		map.populateEncoder();
		String line = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			sc = new Scanner(fis, "UTF-8");
			while (sc.hasNextLine()) {
				line = sc.nextLine();

				// create a headline object from each line
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
		// create headline object from each line in the file
		Headline headline = new Headline(line, shingleSize);

		// vectorise headline and create an MLData object
		MLData input = new BasicMLData(fv.hashVectorizer(headline, vecSize));

		// vectorise the expected category and create an MLData object
		MLData expected = new BasicMLData(map.encode(headline.getExpected()));
		MLDataPair dataPair = new BasicMLDataPair(input, expected);
		data.add(dataPair);

	}

	// create dataset
	private MLDataSet createdDataset() {
		MLDataSet dataSet = new BasicMLDataSet(data);
		return dataSet;
	}
}
