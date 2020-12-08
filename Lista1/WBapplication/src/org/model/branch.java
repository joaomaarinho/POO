package org.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")	
public class branch implements Serializable {
	public String Number;
	public String BranchName;
	public List<client> Client = new ArrayList<client>();

	@Override
	public String toString() {
		
		return String.format("%s - %s", Number, BranchName);
	}
}
