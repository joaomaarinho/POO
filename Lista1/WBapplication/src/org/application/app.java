package org.application;

import java.util.ArrayList;
import java.util.List;

import org.control.control;
import org.control.menu;
import org.control.menucontrol;
import org.control.reportscontrol;
import org.model.branch;

public class app {

	public static void main(String[] args) {
		control Control = new control();
		menucontrol MenuControl = new menucontrol();
		reportscontrol ReportsControl = new reportscontrol();
		List<branch> Branch = new ArrayList<>();

		try {
			Branch = MenuControl.recuperarDados(Branch);
		} catch (Exception e) {
			System.out.println("Algo deu errado na leitura!");
			e.printStackTrace();
		}
		branch f = new branch();

		int escolha = 0;
		while (escolha != 12) {
			
			try {
				MenuControl.salvarDados(Branch);
			} catch (Exception e) {
				System.out.println("Algo deu errado no salvamento!");
				e.printStackTrace();
			}
			menu.menushow(f);
			escolha = Control.opcao();

			if (escolha == 1) {
				try {
					f = MenuControl.selecionarFilial(Branch, f, Control);
				} catch (Exception e) {
					System.out.println("Algo deu errado na seleção da filial");
					e.printStackTrace();
				}
			}

			if (escolha == 2) {
				MenuControl.cadastrarCliente(Control, f);
			}

			if (escolha == 3) {
				MenuControl.editarCliente(f, Control);
			}

			if (escolha == 4) {
				MenuControl.excluirCliente(f, Control);
			}

			if (escolha == 5) {
				MenuControl.registrarConsumo(f, escolha, Control);
			}

			if (escolha == 6) {
				MenuControl.listarHisConCliente(f, Control);
			}

			if (escolha == 7) {
				MenuControl.listarClientesAlfabeto(f);
			}

			if (escolha == 8) {
				MenuControl.listarClientesFemAlfabeto(f);
			}

			if (escolha == 9) {
				MenuControl.listarClientesMasAlfabeto(f);
			}

			if (escolha == 10) {
				MenuControl.listarRelatorios(f, escolha, Control, ReportsControl);
			}

			if (escolha == 11) {
				Branch.forEach(branch -> System.out.println(branch));
			}
		}
		System.out.println("Aplicação Finalizada!");

	}
}