package org.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.model.branch;
import org.model.client;
import org.model.service;

public class menucontrol {

	@SuppressWarnings("unchecked")
	public List<branch> recuperarDados(List<branch> Branch) throws Exception {
		Date data = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dataformatada = dateFormat.format(data);

		String path = "C:\\POO\\Lista1\\WBapplication\\src\\org\\data\\cadastros.ser";
		String path2 = "C:\\POO\\Lista1\\WBapplication\\src\\org\\data\\cadastros" + dataformatada
				+ ".ser";

		File original = new File(path);
		File backup = new File(path2);

		try {
			Files.copy(original.toPath(), backup.toPath());
		} catch (Exception e) {
			System.out.println("Algo deu errado: " + e);
		}

		FileInputStream canal = new FileInputStream(path);
		ObjectInputStream leitor = new ObjectInputStream(canal);
		Branch = (List<branch>) leitor.readObject();
		leitor.close();
		System.out.println("Cadastros lidos com sucesso!");
		return Branch;
	}

	public void salvarDados(List<branch> Branch) throws Exception {
		String path = "C:\\POO\\Lista1\\WBapplication\\src\\org\\data\\cadastros.ser";
		FileOutputStream canal = new FileOutputStream(path);
		ObjectOutputStream escritor = new ObjectOutputStream(canal);
		escritor.writeObject(Branch);
		escritor.close();
		System.out.println("Cadastros salvos com sucesso!");
	}

	public branch selecionarFilial(List<branch> Branch, branch f, control Control) throws Exception {
		System.out.println("Por favor, digite o número da filial:");
		String numerofil = Control.texto();

		if (Branch.isEmpty()) {
			System.out.println("Não existe filiais cadastradas");
			f = salvarFilial(Branch, f, numerofil, Control);
		} else {
			f = procurarFilial(Branch, f, numerofil, Control);
		}
		return f;
	}

	public branch procurarFilial(List<branch> Branch, branch f, String numerofil, control Control) {
		int encontrou = 0;
		for (branch fil : Branch) {
			if (fil.Number.equals(numerofil)) {
				System.out.println("Filial encontrada\n");
				f = fil;
				encontrou = 1;
				break;
			}
		}
		if (encontrou != 1) {
			System.out.println("Filial não encontrada");
			f = salvarFilial(Branch, f, numerofil, Control);
			System.out.println("");
		}
		return f;
	}

	public branch salvarFilial(List<branch> Branch, branch f, String numerofil, control Control) {
		int resposta = 0;
		System.out.println("Deseja salvar a nova filial?");
		System.out.println("(Escolha Sim(1) ou Não(2)");
		resposta = Control.opcao();
		switch (resposta) {
		case 1:
			branch novafilial = new branch();
			novafilial.Number= numerofil;
			System.out.println("Digite o nome da filial:");
			novafilial.BranchName = Control.texto();
			Branch.add(novafilial);
			f = novafilial;
			break;
		default:
			System.out.println("A filial não foi salva\n");
		}
		return f;
	}

	public void cadastrarCliente(control Control, branch f) {
		client c = new client();
		System.out.println("Por favor insira o nome do cliente:");
		c.name = Control.texto();
		System.out.println("Por favor insira o telefone:");
		c.phone = Control.texto();
		System.out.println("Por favor insira a data de nascimento:");
		c.birthdate = Control.texto();
		System.out.println("Por favor insira o gênero (M ou F):");
		c.gender = Control.texto();
		f.Client.add(c);
	}

	public void editarCliente(branch f, control Control) {
		System.out.println("Digite o nome do cliente para alterar seus dados:");
		String nomecliente = Control.texto();
		for (client pessoa : f.Client) {
			if (pessoa.name.equals(nomecliente)) {
				System.out.println("Por favor insira o nome do cliente:");
				pessoa.name = Control.texto();
				System.out.println("Por favor insira o telefone:");
				pessoa.phone = Control.texto();
				System.out.println("Por favor insira a data de nascimento:");
				pessoa.birthdate = Control.texto();
				System.out.println("Por favor insira o gênero (M ou F):");
				pessoa.gender = Control.texto();
				break;
			}

		}
	}

	public void excluirCliente(branch f, control Control) {
		System.out.println("Digite o nome do cliente para excluir seus dados:");
		String nomecliente = Control.texto();
		for (client pessoa : f.Client) {
			if (pessoa.name.equals(nomecliente)) {
				f.Client.remove(pessoa);
				System.out.println("O cadastro do cliente " + nomecliente + " foi removido!");
				break;
			}
		}
	}

	public void listarClientesAlfabeto(branch f) {
		if (f.Client.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<client> listacli = new LinkedList<>();
		listacli = f.Client;
		Collections.sort(listacli, client.Comparators.NAME);
		listacli.forEach(pessoa -> System.out.println(pessoa));
		System.out.println("");
	}

	public void listarClientesFemAlfabeto(branch f) {
		if (f.Client.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<client> listacli = new LinkedList<>();
		listacli = f.Client;
		Collections.sort(listacli, client.Comparators.GENDERNAME);
		for (client pessoa : listacli) {
			if (pessoa.gender.equals("F")) {
				System.out.println(pessoa);
			}
		}
		System.out.println("");
	}

	public void listarClientesMasAlfabeto(branch f) {
		if (f.Client.isEmpty()) {
			System.out.println("Não há clientes cadastrados");
		} else {
			System.out.println("##### Exibindo a lista de clientes cadastrados: ####");
		}
		List<client> listacli = new LinkedList<>();
		listacli = f.Client;
		Collections.sort(listacli, client.Comparators.GENDERNAME);
		for (client pessoa : listacli) {
			if (pessoa.gender.equals("M")) {
				System.out.println(pessoa);
			}
		}
		System.out.println("");
	}

	public void registrarConsumo(branch f, int escolha, control Control) {
		System.out.println("Digite o nome do cliente para registrar o seu consumo:");
		String nomecliente = Control.texto();
		Date data = new Date();
		double total = 0;
		for (client pessoa : f.Client) {
			if (pessoa.name.equals(nomecliente)) {
				while (escolha != 6) {
					servicemenu.menuserviceshow();
					escolha = Control.opcao();
					if (escolha == 1) {
						service listaconsumo = new service();
						listaconsumo.servicetype = "Manicure";
						listaconsumo.serviceprice = 50.0;
						listaconsumo.serviceday = data;
						pessoa.consum.add(listaconsumo);
					}
					if (escolha == 2) {
						service listaconsumo = new service();
						listaconsumo.servicetype = "Pedicure";
						listaconsumo.serviceprice = 60.0;
						listaconsumo.serviceday = data;
						pessoa.consum.add(listaconsumo);
					}
					if (escolha == 3) {
						service listaconsumo = new service();
						listaconsumo.servicetype = "Design de Sobrancelhas";
						listaconsumo.serviceprice = 40.0;
						listaconsumo.serviceday = data;
						pessoa.consum.add(listaconsumo);
					}
					if (escolha == 4) {
						service listaconsumo = new service();
						listaconsumo.servicetype = "Corte de Cabelo";
						listaconsumo.serviceprice = 80.0;
						listaconsumo.serviceday = data;
						pessoa.consum.add(listaconsumo);
					}
					if (escolha == 5) {
						service listaconsumo = new service();
						listaconsumo.servicetype = "Pintura de Cabelo";
						listaconsumo.serviceprice = 150.0;
						listaconsumo.serviceday = data;
						pessoa.consum.add(listaconsumo);
					}
				}
				for (service consumido : pessoa.consum) {
					if (consumido.serviceday.equals(data)) {
						total = total + consumido.serviceprice;
						System.out.println(consumido);
					}
				}
				break;
			}
		}
		System.out.printf("Total a pagar: R$ %f\n", total);
	}

	public void listarHisConCliente(branch f, control Control) {
		System.out.println("Digite o nome do cliente para exibir o seu histórico:");
		String nomecliente = Control.texto();
		double total = 0;
		for (client pessoa : f.Client) {
			if (pessoa.name.equals(nomecliente)) {
				if (pessoa.consum.isEmpty()) {
					System.out.println("Esse cliente ainda não utilizou nenhum serviço!");
				} else {
					for (service consumido : pessoa.consum) {
						total = total + consumido.serviceprice;
					}
					pessoa.consum.forEach(consumido -> System.out.println(consumido));
				}
			}
		}
		System.out.printf("Total do histórico: R$ %f\n", total);
	}

	public void listarRelatorios(branch f, int escolha, control Control, reportscontrol ReportsControl) {
		while (escolha != 7) {
			reportsmenu.menuserviceshow();
			escolha = Control.opcao();
			if (escolha == 1) {
				ReportsControl.idadeMedia(f);
			}
			if (escolha == 2) {
				ReportsControl.idadeMediaFem(f);
			}
			if (escolha == 3) {
				ReportsControl.idadeMediaMas(f);
			}
			if (escolha == 4) {
				ReportsControl.rankingServicos(f);
			}
			if (escolha == 5) {
				ReportsControl.rankingServicosFem(f);
			}
			if (escolha == 6) {
				ReportsControl.rankingServicosMas(f);
			}
		}
	}
}