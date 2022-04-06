package ie.gmit.sw;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Headline {
	private int hashedDate;
	private double hashedExpected;
	private List<Integer> shingledHeadline;
	
	public Headline(String line) {
		//split the line
		String[] split = line.split(",");
		
		//format the date
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-2,"-").toString();
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-5,"-").toString();
		
		//parse date
		hashedDate = LocalDate.parse(split[0]).hashCode();
		
		hashedExpected=split[1].hashCode();
		System.out.println(hashedExpected);
		shingledHeadline=s.createShingle(split[2],3);
	}
	
	public double getHashedExpected() {
		return hashedExpected;
	}
	public void setHashedExpected(double hashedExpected) {
		this.hashedExpected = hashedExpected;
	}

	private static Shingler s=Shingler.getInstance();
	
	public List<Integer> getShingledHeadline() {
		return shingledHeadline;
	}
	public int getHashedDate() {
		return hashedDate;
	}
	public void setHashedDate(int hashedDate) {
		this.hashedDate = hashedDate;
	}
	public void setShingledHeadline(List<Integer> shingledHeadline) {
		this.shingledHeadline = shingledHeadline;
	}
	
	
}
