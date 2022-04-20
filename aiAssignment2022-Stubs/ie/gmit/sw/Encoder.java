package ie.gmit.sw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Encoder {
	private static Encoder map = null;
	
	private ReadCSV read;
	private Encoder() {

	}

	public static Encoder getInstance() {
		if (map == null)
			map = new Encoder();

		return map;
	}
	
	private Map<String, Integer> encodedCategories= new HashMap<>();
	private Map<Integer, String> mappedCategories= new HashMap<>();
	
	private String[] categories= {"news.politics","news.health.coronavirus","business.personal-finance","business.technology","lifestyle.food","culture.music.album-reviews","news.environment", 
			"lifestyle.health-family","lifestyle.homes.gardens","news.social.beliefs","business.construction","lifestyle.homes","business.financial-services","sport.others","business.retail-and-services",
			"news.world.us","business.energy-and-resources","news.law","news.consumer","lifestyle.homes.take-five","lifestyle.health-family.fitness","lifestyle.homes.new-to-market","culture.design",
			"news.offbeat","news.health","news.law.courts.criminal-court","news.politics.oireachtas","business.work","business.health-pharma","news.world.africa","news.law.courts.district-court",
			"sport.racing","news.law.courts.high-court","business.companies","lifestyle.homes.fine-art-antiques","lifestyle.health-family.parenting","news.ireland","culture.tuarascail",
			"lifestyle.travel.europe","culture.stage","lifestyle.food.restaurant-reviews","sport.soccer","business.transport-and-tourism","lifestyle.abroad.working-abroad","news.law.courts",
			"business.economy","lifestyle.travel","business.innovation","lifestyle.food.recipes","sport.gaelic","news.world.middle-east","business.markets","business.media-and-marketing",
			"business.commercial-property","news.world","news.law.courts.circuit-court","culture.music","lifestyle.abroad.generation-emigration","lifestyle.motors","news.law.courts.coroners-court",
			"lifestyle.people","news.law.courts.supreme-court","lifestyle.food.drink","sport.golf","news.world.europe","news","news.education","business","news.world.asia-pacific",
			"lifestyle.homes.interiors","culture.tv-radio-web","lifestyle.fashion","business.manufacturing","lifestyle.abroad","opinion","lifestyle","culture.books","news.social",
			"news.science","opinion.letters","lifestyle.travel.ireland","culture","lifestyle.fashion.beauty","culture.heritage","culture.film","business.agribusiness-and-food",
			"opinion.editorial","sport","sport.rugby","news.world.uk"
	};
	
	public void populateEncoder() {
		for (int i = 0; i < categories.length; i++) {
			encodedCategories.put(categories[i], i);
		}
		
		for (int i = 0; i < categories.length; i++) {
			mappedCategories.put(i,categories[i]);
		}
	}
	
	public double[] encode(String input) {
		if (input == null | input.isEmpty()) {
			return null;
		}

		int position=encodedCategories.get(input);
		
		double[] encoding = new double[90];
		
		encoding[position]+=1;
		//System.out.println(Arrays.toString(encoding));
		return encoding;
		
	}

	public Map<String, Integer> getEncodedCategories() {
		return encodedCategories;
	}

	public void setEncodedCategories(Map<String, Integer> encodedCategories) {
		this.encodedCategories = encodedCategories;
	}

	public Map<Integer, String> getMappedCategories() {
		return mappedCategories;
	}

	public void setMappedCategories(Map<Integer, String> mappedCategories) {
		this.mappedCategories = mappedCategories;
	}
	
	
	



	
}
