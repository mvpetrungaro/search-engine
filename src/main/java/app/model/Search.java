package app.model;

import lombok.Data;

@Data
public class Search {
	private String restaurantName;
	private Integer customerRating;
	private Integer distance;
	private Integer price;
	private String cuisine;
	
	public static class Builder {
		private String restaurantName;
		private Integer customerRating;
		private Integer distance;
		private Integer price;
		private String cuisine;
		
		public Builder byRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
			return this;
		}
		
		public Builder byCustomerRating(Integer customerRating) {
			this.customerRating = customerRating;
			return this;
		}
		
		public Builder byDistance(Integer distance) {
			this.distance = distance;
			return this;
		}
		
		public Builder byPrice(Integer price) {
			this.price = price;
			return this;
		}
		
		public Builder byCuisine(String cuisine) {
			this.cuisine = cuisine;
			return this;
		}
		
		public Search build() {
			Search search = new Search();
			
			search.setRestaurantName(restaurantName);
			search.setCustomerRating(customerRating);
			search.setDistance(distance);
			search.setPrice(price);
			search.setCuisine(cuisine);
			
			return search;
		}
	}
}
