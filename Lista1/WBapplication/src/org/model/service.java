package org.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class service implements Serializable {
	public String servicetype;
	public double serviceprice;
	public Date serviceday;

	@Override
	public String toString() {

		return String.format("%s\t\t\t%f\t\t\t%tD", servicetype, serviceprice, serviceday);
	}
}