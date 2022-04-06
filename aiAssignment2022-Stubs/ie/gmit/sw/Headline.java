package ie.gmit.sw;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Headline {
	private int hashedDate;
	private List<Integer> shingledHeadline;
	private String expected;
	
	public Headline(String line, int shingleSize) {
		//split the line
		String[] split = line.split(",");
		
		//format the date
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-2,"-").toString();
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-5,"-").toString();
		
		//parse date
		hashedDate = LocalDate.parse(split[0]).hashCode();
		
		expected=split[1];
		shingledHeadline=s.createShingle(split[2],shingleSize);
	}
	
	
	public String getExpected() {
		return expected;
	}
	public void setExpected(String expected) {
		this.expected = expected;
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
