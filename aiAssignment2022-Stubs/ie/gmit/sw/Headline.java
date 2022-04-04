package ie.gmit.sw;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Headline {
	private String headline;
	private String expected;
	private LocalDate date;
	private List<Integer> shingledHeadline;
	
	public List<Integer> getShingledHeadline() {
		return shingledHeadline;
	}
	public void setShingledHeadline(List<Integer> shingledHeadline) {
		this.shingledHeadline = shingledHeadline;
	}
	public Headline(String line) {
		//split the line
		String[] split = line.split(",");
		
		//format the date
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-2,"-").toString();
		split[0]=new StringBuilder(split[0]).insert(split[0].length()-5,"-").toString();
		
		//parse date
		date = LocalDate.parse(split[0]);
		System.out.println("date:" +date);
		
		//set the variables
		expected=split[1];
		headline=split[2];
		shingledHeadline=null;
	}
	public String getHeadline() {
		return headline;
	}
	public String getExpected() {
		return expected;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public void setExpected(String expected) {
		this.expected = expected;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
