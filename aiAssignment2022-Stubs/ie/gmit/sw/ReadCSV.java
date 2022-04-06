package ie.gmit.sw;

import java.io.File;

import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.util.csv.CSVFormat;

public class ReadCSV {
	public void read(String file) {
		VersatileDataSource source= new CSVDataSource(new File(file), false, CSVFormat.DECIMAL_COMMA);
		source.rewind();
		String[] line=source.readLine();
		for(String l: line) {
			System.out.println(l);
		}
	}
}
