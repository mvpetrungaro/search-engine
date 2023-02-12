package app.db.repo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import app.model.Restaurant;
import app.model.Search;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantsRepository extends AbstractRepository<Restaurant> {
	
	private final List<Restaurant> restaurants;
	
	@Override
	public List<Restaurant> findAll() {
		return restaurants;
	}

	public List<Restaurant> findRestaurants(Search search) {

		Predicate<Restaurant> searchCriteria = (r) -> true;

		if (search.getRestaurantName() != null) {
			searchCriteria = searchCriteria.and((r) -> r.getName().toLowerCase().contains(search.getRestaurantName().toLowerCase()));
		}

		if (search.getCustomerRating() != null) {
			searchCriteria = searchCriteria.and((r) -> r.getCustomerRating() >= search.getCustomerRating());
		}

		if (search.getDistance() != null) {
			searchCriteria = searchCriteria.and((r) -> r.getDistance() <= search.getDistance());
		}

		if (search.getPrice() != null) {
			searchCriteria = searchCriteria.and((r) -> r.getPrice() <= search.getPrice());
		}

		if (search.getCuisine() != null) {
			searchCriteria = searchCriteria.and((r) -> r.getCuisine().getName().toLowerCase().contains(search.getCuisine().toLowerCase()));
		}

		List<Restaurant> results = restaurants.stream().filter(searchCriteria).sorted().collect(Collectors.toList());
		
		if (results.size() > 5) {
			return results.subList(0, 5);
		} else {
			return results;
		}
	}
}
