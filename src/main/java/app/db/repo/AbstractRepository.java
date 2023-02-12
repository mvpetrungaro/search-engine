package app.db.repo;

import java.util.List;

public abstract class AbstractRepository<T> {
	public abstract List<T> findAll();
}
