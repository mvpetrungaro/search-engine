package app.model;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class Cuisine implements Serializable, Comparable<Cuisine> {
	
	private static final long serialVersionUID = 3291644926453783334L;

	@CsvBindByPosition(position = 0)
	private Integer id;
	
	@CsvBindByPosition(position = 1)
	private String name;

	@Override
	public int compareTo(Cuisine that) {
		return this.getId().compareTo(that.getId());
	}
}
