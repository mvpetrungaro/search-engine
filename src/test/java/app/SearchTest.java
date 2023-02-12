package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.db.InMemoryDB;
import app.db.loader.CuisinesLoader;
import app.db.loader.RestaurantsLoader;
import app.db.repo.RestaurantsRepository;
import app.model.Restaurant;
import app.model.Search;

class SearchTest {

	static RestaurantsRepository restaurantsRepo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		InMemoryDB db = new InMemoryDB();

		try {
			db.acceptLoader(new CuisinesLoader());
			db.acceptLoader(new RestaurantsLoader());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		restaurantsRepo = (RestaurantsRepository) db.getRepository(Restaurant.class);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testSearchByRestaurantName() {

		// Given
		Search search = new Search.Builder()
				.byRestaurantName("yummy")
				.build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		List<String> names = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
		
		assertEquals(Arrays.asList(
				"Sizzle Yummy",
				"Yummylia",
				"Palace Yummy",
				"Festive Yummy",
				"Diced Yummy"), names
		);
	}

	@Test
	void testSearchByCustomerRating4() {

		// Given
		Search search = new Search.Builder().byCustomerRating(4).build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		List<String> names = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
		
		assertEquals(Arrays.asList(
				"Deliciousgenix",
				"Deliciouszilla",
				"Fodder Table",
				"Grove Table",
				"Bang Delicious"), names
		);
	}
	
	@Test
	void testSearchByCustomerRating5AndDistance1() {

		// Given
		Search search = new Search.Builder()
				.byCustomerRating(5)
				.byDistance(1)
				.build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		assertTrue(restaurants.isEmpty());
	}
	
	@Test
	void testSearchByPrice15() {

		// Given
		Search search = new Search.Builder()
				.byPrice(15)
				.build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		List<String> names = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
		
		assertEquals(Arrays.asList(
				"Deliciousgenix",
				"Deliciouszilla",
				"Dished Grill",
				"Sizzle Yummy",
				"Kitchenster"), names
		);
	}
	
	@Test
	void testSearchByCuisine() {

		// Given
		Search search = new Search.Builder()
				.byCuisine("american")
				.build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		List<String> names = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
		
		assertEquals(Arrays.asList(
				"Wish Chow",
				"Kitchenster",
				"Fresh Table",
				"Tableio",
				"Bazaar Chow"), names
		);
	}
	
	@Test
	void testSearchByEverything() {

		// Given
		Search search = new Search.Builder()
				.byRestaurantName("pala")
				.byCustomerRating(4)
				.byDistance(5)
				.byPrice(30)
				.byCuisine("sian")
				.build();

		// When
		List<Restaurant> restaurants = restaurantsRepo.findRestaurants(search);

		// Then
		List<String> names = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
		
		assertEquals(Arrays.asList(
				"Fury Palace"), names
		);
	}
}
