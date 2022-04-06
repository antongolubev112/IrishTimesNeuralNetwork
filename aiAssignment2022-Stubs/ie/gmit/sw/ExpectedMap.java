package ie.gmit.sw;

public class ExpectedMap {
	private String headlineCategory;
	private double [] vector;
	
	public ExpectedMap(String headlineCategory, double[] vector) {
		this.headlineCategory = headlineCategory;
		this.vector = vector;
	}

	public String getHeadlineCategory() {
		return headlineCategory;
	}

	public void setHeadlineCategory(String headlineCategory) {
		this.headlineCategory = headlineCategory;
	}

	public double[] getVector() {
		return vector;
	}

	public void setVector(double[] vector) {
		this.vector = vector;
	}
	
}
