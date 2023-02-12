package app.db.repo;

import java.util.List;

import app.model.Cuisine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CuisinesRepository extends AbstractRepository<Cuisine> {

	private final List<Cuisine> cuisines;

	@Override
	public List<Cuisine> findAll() {
		return cuisines;
	}
}
