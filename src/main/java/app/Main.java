package app;

import java.io.IOException;
import java.util.List;

import app.db.InMemoryDB;
import app.db.loader.CuisinesLoader;
import app.db.loader.RestaurantsLoader;
import app.db.repo.RestaurantsRepository;
import app.io.CommandLineSearchInputStrategy;
import app.io.ConsoleSearchOutputStrategy;
import app.model.Restaurant;
import app.model.Search;

public class Main {

	public static void main(String[] args) {
		
		InMemoryDB db = new InMemoryDB();

		try {
			db.acceptLoader(new CuisinesLoader());
			db.acceptLoader(new RestaurantsLoader());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			Search searchParams = new CommandLineSearchInputStrategy(args).getSearchParamsFromInput();

			RestaurantsRepository restaurantsRepo = (RestaurantsRepository) db.getRepository(Restaurant.class);
			List<Restaurant> results = restaurantsRepo.findRestaurants(searchParams);

			new ConsoleSearchOutputStrategy().outputSearchResults(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
