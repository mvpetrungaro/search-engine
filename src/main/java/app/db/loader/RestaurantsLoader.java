package app.db.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import app.db.InMemoryDB;
import app.db.repo.CuisinesRepository;
import app.db.repo.RestaurantsRepository;
import app.model.Cuisine;
import app.model.Restaurant;

public class RestaurantsLoader extends InMemoryDBLoader {

	@Override
	public void load(InMemoryDB db) throws IllegalStateException, IOException {
		
		try (
				InputStream inputStream = getClass().getResourceAsStream("/restaurants.csv");
				InputStreamReader reader = new InputStreamReader(inputStream);
		) {
			List<Restaurant> restaurants = new CsvToBeanBuilder<Restaurant>(reader)
	                .withType(Restaurant.class)
	                .withSkipLines(1)
	                .build()
	                .parse();
			
			CuisinesRepository cuisinesRepo = (CuisinesRepository) db.getRepository(Cuisine.class);
			List<Cuisine> cuisines = cuisinesRepo.findAll();
			
			for (Restaurant restaurant : restaurants) {
				
				cuisines.stream()
						.filter(c -> c.getId().equals(restaurant.getCuisineId())).findAny()
						.ifPresent(c -> restaurant.setCuisine(c));
			}
			
			RestaurantsRepository restaurantsRepo = new RestaurantsRepository(restaurants);
			
			db.addRepository(Restaurant.class, restaurantsRepo);
		}
	}
	
}
