package application;

import java.util.ArrayList;
import java.util.List;

import control.Control;
import control.MenuControl;
import control.Menu;
import model.Client;

public class app {

	public static void main(String[] args) {
		Control controle = new Control();
		MenuControl controlemenu = new MenuControl();
		List<Client> clientes = new ArrayList<>();

		try {
			clientes = controlemenu.recuperarDados(clientes);
		} catch (Exception e) {
			System.out.println("Falha na leitura!");
			e.printStackTrace();
		}

		int escolha = 0;
		while (escolha != 7) {
			try {
				controlemenu.salvarDados(clientes);
			} catch (Exception e) {
				System.out.println("Falha no salvamento!");
				e.printStackTrace();
			}
			Menu.showMenu();
			escolha = controle.opcao();

			if (escolha == 1) {
				controlemenu.cadastrarCliente(clientes, controle);
			}

			if (escolha == 2) {
				controlemenu.cadastrarVeiculo(clientes, controle);
			}

			if (escolha == 3) {
				controlemenu.agendarRevisao(clientes, controle);
			}

			if (escolha == 4) {
				controlemenu.alterarAgendamento(clientes, controle);
			}

			if (escolha == 5) {
				controlemenu.cancelarAgendamento(clientes, controle);
			}

			if (escolha == 6) {
				controlemenu.relatoriosServicos(clientes, controle);
			}
		}
		System.out.println("Aplicação Finalizada!");

	}

}

