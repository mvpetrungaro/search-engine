package app.db.criteria;

import java.util.function.Function;
import java.util.function.Predicate;

import app.model.Restaurant;

public class CriteriaNumberGE extends CriteriaNumber {

	public CriteriaNumberGE(Function<Restaurant, Integer> c1, Integer c2) {
		super(c1, c2);
	}

	@Override
	public Predicate<Restaurant> addCriteria(Predicate<Restaurant> criteria) {
		if (c2 != null) {
			return criteria.and((r) -> c1.apply(r) >= c2);
		}
		
		return criteria;
	}
	
	
}
