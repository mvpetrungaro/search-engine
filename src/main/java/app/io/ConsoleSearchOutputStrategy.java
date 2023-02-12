package app.io;

import java.util.List;

import app.model.Restaurant;

public class ConsoleSearchOutputStrategy implements SearchOutputStrategy {

	@Override
	public void outputSearchResults(List<Restaurant> restaurants) {
		restaurants.forEach(r -> System.out.println(r.getName()));
	}
}
