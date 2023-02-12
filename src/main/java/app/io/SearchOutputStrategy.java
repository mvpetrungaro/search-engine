package app.io;

import java.util.List;

import app.model.Restaurant;

public interface SearchOutputStrategy {
	void outputSearchResults(List<Restaurant> restaurants);
}
