package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Service implements Serializable {
	public String tipo;
	public String data;
	public String hora;

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s", tipo, data, hora);
	}

}
