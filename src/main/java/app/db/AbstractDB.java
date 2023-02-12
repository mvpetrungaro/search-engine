package app.db;

import app.db.repo.AbstractRepository;

public abstract class AbstractDB {
	public abstract void addRepository(Class<?> clazz, AbstractRepository<?> repository);
	public abstract AbstractRepository<?> getRepository(Class<?> clazz);
}
