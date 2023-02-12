package app.db.loader;

import java.io.IOException;

import app.db.InMemoryDB;

public abstract class InMemoryDBLoader {
	public abstract void load(InMemoryDB db) throws IllegalStateException, IOException;
}
