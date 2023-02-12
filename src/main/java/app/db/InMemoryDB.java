package app.db;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.db.loader.InMemoryDBLoader;
import app.db.repo.AbstractRepository;
import lombok.Getter;

public class InMemoryDB extends AbstractDB {
	
	@Getter
	private Map<Class<?>, AbstractRepository<?>> repositories = new HashMap<>();
	
	@Override
	public void addRepository(Class<?> clazz, AbstractRepository<?> repository) {
		repositories.put(clazz, repository);
	}
	
	@Override
	public AbstractRepository<?> getRepository(Class<?> clazz) {
		return repositories.get(clazz);
	}
	
	public void acceptLoader(InMemoryDBLoader loader) throws IllegalStateException, IOException {
		loader.load(this);
	}
}
