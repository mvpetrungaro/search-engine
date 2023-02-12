package app.db.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import app.db.InMemoryDB;
import app.db.repo.CuisinesRepository;
import app.model.Cuisine;

public class CuisinesLoader extends InMemoryDBLoader {

	@Override
	public void load(InMemoryDB db) throws IllegalStateException, IOException {
		
		try (
				InputStream inputStream = getClass().getResourceAsStream("/cuisines.csv");
				InputStreamReader reader = new InputStreamReader(inputStream);
		) {
			List<Cuisine> cuisines = new CsvToBeanBuilder<Cuisine>(reader)
	                .withType(Cuisine.class)
	                .withSkipLines(1)
	                .build()
	                .parse();
			
			CuisinesRepository cuisinesRepo = new CuisinesRepository(cuisines);
			
			db.addRepository(Cuisine.class, cuisinesRepo);
		}
	}
	
}
