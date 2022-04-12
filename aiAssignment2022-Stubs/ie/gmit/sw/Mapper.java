package ie.gmit.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mapper {
	private Set<String> categories=new HashSet<>();
	private Map<String, double[]> categoriesMap= new HashMap<>();
	
	private static Mapper m = null;

	private Mapper() {

	}

	public static Mapper getInstance() {
		if (m == null)
			m = new Mapper();

		return m;
	}
	
	
	public void getUniqueCategories(String category) {
		categories.add(category);
	}

	public void mapCategoryToVector(String category, double [] vector) {
		if(!categoriesMap.containsKey(category)) {
			categoriesMap.put(category, vector);
		}
		
	}
	
	
	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Map<String, double[]> getMap() {
		return categoriesMap;
	}

	public void setMap(Map<String, double[]> map) {
		this.categoriesMap = map;
	}
	
	
}
