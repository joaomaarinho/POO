package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Client implements Serializable {
	public String nome;
	public String telefone;
	public String endereco;
	public String cpf;
	public List<Vehicle> veiculos = new ArrayList<>();

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s", nome, telefone, endereco, cpf);
	}
}