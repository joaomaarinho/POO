package org.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class client implements Serializable {
	public String name;
	public String phone;
	public String birthdate;
	public String gender;
	public List<service> consum = new ArrayList<service>();

	@Override
	public String toString() {
		
		return String.format("%s\t%s\t%s\t%s", name, phone, birthdate, gender);
	}

	public static class Comparators {
		public static final Comparator<client> NAME = (client c1, client c2) -> c1.name.compareTo(c2.name);
		public static final Comparator<client> GENDER = (client c1, client c2) -> c1.gender.compareTo(c2.gender);
		public static final Comparator<client> GENDERNAME = (client c1, client c2) -> GENDER.thenComparing(NAME)
				.compare(c1, c2);

	}

}
