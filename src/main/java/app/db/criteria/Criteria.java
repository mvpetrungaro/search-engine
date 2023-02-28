package app.db.criteria;

import java.util.function.Predicate;

import app.model.Restaurant;

public abstract class Criteria {
	public abstract Predicate<Restaurant> addCriteria(Predicate<Restaurant> criteria);
}
