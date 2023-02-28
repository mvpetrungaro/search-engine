package app.db.criteria;

import java.util.function.Function;

import app.model.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CriteriaNumber extends Criteria {
	protected final Function<Restaurant, Integer> c1;
	protected final Integer c2;
}
