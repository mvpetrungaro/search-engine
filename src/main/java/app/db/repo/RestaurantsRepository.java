package app.db.repo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import app.db.criteria.Criteria;
import app.db.criteria.CriteriaNumberGE;
import app.db.criteria.CriteriaNumberLE;
import app.db.criteria.CriteriaText;
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
		
		List<Criteria> criterias = Arrays.asList(
				new CriteriaText((r) -> r.getName(), search.getRestaurantName()),
				new CriteriaNumberGE((r) -> r.getCustomerRating(), search.getCustomerRating()),
				new CriteriaNumberLE((r) -> r.getDistance(), search.getDistance()),
				new CriteriaNumberLE((r) -> r.getPrice(), search.getPrice()),
				new CriteriaText((r) -> r.getCuisine().getName(), search.getCuisine())
		);

		for (Criteria criteria : criterias) {
			searchCriteria = criteria.addCriteria(searchCriteria);
		}
		
		List<Restaurant> results = restaurants.stream().filter(searchCriteria).sorted().collect(Collectors.toList());
		
		if (results.size() > 5) {
			return results.subList(0, 5);
		} else {
			return results;
		}
	}
}
