package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Client;
import model.Service;
import model.Vehicle;

public class MenuControl {

	@SuppressWarnings("unchecked")
	public List<Client> recuperarDados(List<Client> clientes) throws Exception {
		Date data = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dataformatada = dateFormat.format(data);

		String caminho = "C:\\POO\\Lista2\\src\\src\\data\\cadastros.ser";
		String caminho2 = "C:\\POO\\Lista2\\src\\src\\data\\cadastros-"
				+ dataformatada + ".ser";

		File original = new File(caminho);
		File backup = new File(caminho2);

		try {
			Files.copy(original.toPath(), backup.toPath());
		} catch (Exception e) {
			System.out.println("Algo deu errado: " + e);
		}

		FileInputStream canal = new FileInputStream(caminho);
		ObjectInputStream leitor = new ObjectInputStream(canal);
		clientes = (List<Client>) leitor.readObject();
		leitor.close();
		System.out.println("Cadastros lidos com sucesso!");
		return clientes;
	}

	public void salvarDados(List<Client> clientes) throws Exception {
		String caminho = "C:\\POO\\Lista2\\src\\src\\data\\cadastros.ser";
		FileOutputStream canal = new FileOutputStream(caminho);
		ObjectOutputStream escritor = new ObjectOutputStream(canal);
		escritor.writeObject(clientes);
		escritor.close();
		System.out.println("Cadastros salvos com sucesso!");
	}

	public void cadastrarCliente(List<Client> clientes, Control controle) {
		Client c = new Client();
		System.out.println("Insira o nome do cliente:");
		c.nome = controle.texto();
		System.out.println("Insira o telefone do cliente:");
		c.telefone = controle.texto();
		System.out.println("Insira a data de nascimento do cliente:");
		c.endereco = controle.texto();
		System.out.println("Insira o cpf do cliente:");
		c.cpf = controle.texto();
		clientes.add(c);
	}

	public void cadastrarVeiculo(List<Client> clientes, Control controle) {
		System.out.println("Para cadastrar o veiculo, selecione o cliente");
		System.out.println("Digite o cpf do cliente:");
		String numcpf = controle.texto();
		for (Client cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				Vehicle v = new Vehicle();
				System.out.println("Por favor insira o número da placa do veículo:");
				v.numeroplaca = controle.texto();
				System.out.println("Por favor insira o modelo do veículo:");
				v.modelo = controle.texto();
				System.out.println("Por favor insira o ano de fabricação do veículo:");
				v.anofabricacao = controle.texto();
				System.out.println("Por favor insira o valor da compra do veículo:");
				v.valorcompra = controle.texto();
				cliente.veiculos.add(v);
			}
		}
	}

	public void agendarRevisao(List<Client> clientes, Control controle) {
		System.out.println("Para realizar o agendamento, selecione o cliente e o veículo");
		System.out.println("Informe o cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Digite a placa do veículo:");
		String numplaca = controle.texto();
		for (Client cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Vehicle veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						Service serv = new Service();
						System.out.println("Por favor insira o tipo de serviço:");
						serv.tipo = controle.texto();
						System.out.println("Por favor insira a data do serviço:");
						serv.data = controle.texto();
						System.out.println("Por favor insira a hora do serviço:");
						serv.hora = controle.texto();
						veiculo.servicos.add(serv);
					}

				}
			}
		}
	}

	public void alterarAgendamento(List<Client> clientes, Control controle) {
		System.out.println("Para alterar o agendamento, selecione o cliente, veículo e a data do agendamento");
		System.out.println("Digite o cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Digite a placa do veículo:");
		String numplaca = controle.texto();
		System.out.println("Digite a data do agendamento:");
		String datagenda = controle.texto();
		for (Client cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Vehicle veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						for (Service servico : veiculo.servicos) {
							if (servico.data.equals(datagenda)) {
								System.out.println("Por favor insira o tipo de serviço:");
								servico.tipo = controle.texto();
								System.out.println("Por favor insira a data do serviço:");
								servico.data = controle.texto();
								System.out.println("Por favor insira a hora do serviço:");
								servico.hora = controle.texto();
							}
						}
					}

				}
			}
		}
	}

	public void cancelarAgendamento(List<Client> clientes, Control controle) {
		System.out.println("Para alterar o agendamento, selecione o cliente, veículo e a data do agendamento");
		System.out.println("Digite o cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Digite a placa do veículo:");
		String numplaca = controle.texto();
		System.out.println("Digite a data do agendamento:");
		String datagenda = controle.texto();
		for (Client cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Vehicle veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						for (Service servico : veiculo.servicos) {
							if (servico.data.equals(datagenda)) {
								System.out.println("Deseja cancelar o serviço agendado: " + servico.tipo + "?");
								System.out.println("Digite 1 para cancelar ou 0 para continuar busca");
								int cancelar = controle.opcao();
								if (cancelar == 1) {
									veiculo.servicos.remove(servico);
									System.out.println("O agendamento foi cancelado com sucesso!");
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	public void relatoriosServicos(List<Client> clientes, Control controle) {
		System.out.println("Digite o nome do cliente para exibir o seu histórico:");
		String numcpf = controle.texto();
		for (Client cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Vehicle veiculo : cliente.veiculos) {
					if (veiculo.servicos.isEmpty()) {
						System.out.println("Esse cliente não possui histórico!");
					} else {
						System.out.println("Serviços registrados do veículo - PLACA: " + veiculo.numeroplaca);
						veiculo.servicos.forEach(servico -> System.out.println(servico));
					}
				}
			}
		}
	}

}
