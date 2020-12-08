package org.control;

import org.model.branch;

public class menu {
	public static void menushow(branch f) {
		System.out.println("\n============================================================");
		System.out.println("Você escolheu a filial: " + f.Number + " - " + f.BranchName);
		System.out.println("============================================================");
		System.out.println("Selecione a opção desejada:");
		System.out.println("1.  Selecione a filial");
		System.out.println("2.  Cadastrar cliente");
		System.out.println("3.  Editar cliente");
		System.out.println("4.  Excluir cliente");
		System.out.println("5.  Registrar consumo do cliente");
		System.out.println("6.  Listar histórico de consumo do cliente");
		System.out.println("7.  Listar clientes em ordem alfabética");
		System.out.println("8.  Listar clientes femininos");
		System.out.println("9.  Listar clientes masculinos");
		System.out.println("10. Relatórios de Inteligência");
		System.out.println("11. Listar todas as filiais");
		System.out.println("12. Sair");
		System.out.println("============================================================\n");
	}

}