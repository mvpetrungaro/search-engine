package app.db.criteria;

import java.util.function.Function;
import java.util.function.Predicate;

import app.model.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriteriaText extends Criteria {
	
	private final Function<Restaurant, String> c1;
	private final String c2;

	@Override
	public Predicate<Restaurant> addCriteria(Predicate<Restaurant> criteria) {
		if (c2 != null && !c2.isEmpty()) {
			return criteria.and((r) -> c1.apply(r).toLowerCase().contains(c2.toLowerCase()));
		}
		
		return criteria;
	}
	
	
}
