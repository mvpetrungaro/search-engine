package app.model;

import java.io.Serializable;
import java.util.Comparator;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class Restaurant implements Serializable, Comparable<Restaurant> {

	private static final long serialVersionUID = -8705001553215064113L;

	@CsvBindByPosition(position = 0)
	private String name;

	@CsvBindByPosition(position = 1)
	private Integer customerRating;

	@CsvBindByPosition(position = 2)
	private Integer distance;

	@CsvBindByPosition(position = 3)
	private Integer price;

	@CsvBindByPosition(position = 4)
	private Integer cuisineId;

	private transient Cuisine cuisine;

	@Override
	public int compareTo(Restaurant that) {
		return Comparator.comparing(Restaurant::getDistance)
				.thenComparing(Comparator.comparing(Restaurant::getCustomerRating).reversed())
				.thenComparing(Restaurant::getPrice).compare(this, that);
	}
}
