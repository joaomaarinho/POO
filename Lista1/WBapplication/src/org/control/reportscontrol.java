package org.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.model.branch;
import org.model.client;
import org.model.service;

public class reportscontrol {

	public void idadeMedia(branch f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		int[] idades = new int[f.Client.size()];
		for (int i = 0; i < f.Client.size(); i++) {
			String nasc = f.Client.get(i).birthdate;
			String[] partes = nasc.split("/");
			int anonasc = Integer.parseInt(partes[2]);
			idades[i] = anoatual - anonasc;
		}
		int total = 0;
		for (int i = 0; i < idades.length; i++) {
			total += idades[i];
		}
		int idademedia = total / idades.length;
		System.out.println("A idade média dos clientes é: " + idademedia);
	}

	public void idadeMediaFem(branch f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> idades = new ArrayList<>();
		for (int i = 0; i < f.Client.size(); i++) {
			if (f.Client.get(i).gender.equals("F")) {
				String nasc = f.Client.get(i).birthdate;
				String[] partes = nasc.split("/");
				int anonasc = Integer.parseInt(partes[2]);
				idades.add(anoatual - anonasc);
			}
		}
		int total = 0;
		for (int i = 0; i < idades.size(); i++) {
			total += idades.get(i);
		}
		int idademedia = total / idades.size();
		System.out.println("A idade média das clientes femininas é: " + idademedia);
	}

	public void idadeMediaMas(branch f) {
		int anoatual = Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> idades = new ArrayList<>();
		for (int i = 0; i < f.Client.size(); i++) {
			if (f.Client.get(i).gender.equals("M")) {
				String nasc = f.Client.get(i).birthdate;
				String[] partes = nasc.split("/");
				int anonasc = Integer.parseInt(partes[2]);
				idades.add(anoatual - anonasc);
			}
		}
		int total = 0;
		for (int i = 0; i < idades.size(); i++) {
			total += idades.get(i);
		}
		int idademedia = total / idades.size();
		System.out.println("A idade média dos clientes masculinos é: " + idademedia);
	}

	public void rankingServicos(branch f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (client cliente : f.Client) {
			for (service consumo : cliente.consum) {
				if (consumo.servicetype.equals("Manicure")) {
					manicure += 1;
				} else if (consumo.servicetype.equals("Pedicure")) {
					pedicure += 1;
				} else if (consumo.servicetype.equals("Design de Sobrancelhas")) {
					sobrancelhas += 1;
				} else if (consumo.servicetype.equals("Corte de Cabelo")) {
					corte += 1;
				} else {
					pintura += 1;
				}
			}

		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking de serviços #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));

	}

	public void rankingServicosFem(branch f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (client cliente : f.Client) {
			if (cliente.gender.equals("F")) {
				for (service consumo : cliente.consum) {
					if (consumo.servicetype.equals("Manicure")) {
						manicure += 1;
					} else if (consumo.servicetype.equals("Pedicure")) {
						pedicure += 1;
					} else if (consumo.servicetype.equals("Design de Sobrancelhas")) {
						sobrancelhas += 1;
					} else if (consumo.servicetype.equals("Corte de Cabelo")) {
						corte += 1;
					} else {
						pintura += 1;
					}
				}
			}
		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking dos serviços mais utilizados pelo público feminino #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));
	}

	public void rankingServicosMas(branch f) {
		int manicure = 0;
		int pedicure = 0;
		int sobrancelhas = 0;
		int corte = 0;
		int pintura = 0;
		Map<String, Integer> rank = new TreeMap<String, Integer>();
		for (client cliente : f.Client) {
			if (cliente.gender.equals("M")) {
				for (service consumo : cliente.consum) {
					if (consumo.servicetype.equals("Manicure")) {
						manicure += 1;
					} else if (consumo.servicetype.equals("Pedicure")) {
						pedicure += 1;
					} else if (consumo.servicetype.equals("Design de Sobrancelhas")) {
						sobrancelhas += 1;
					} else if (consumo.servicetype.equals("Corte de Cabelo")) {
						corte += 1;
					} else {
						pintura += 1;
					}
				}
			}
		}
		rank.put("Manicure", manicure);
		rank.put("Pedicure", pedicure);
		rank.put("Design de Sobrancelhas", sobrancelhas);
		rank.put("Corte de Cabelo", corte);
		rank.put("Pintura de Cabelo", pintura);
		System.out.println("##### Exibindo o ranking dos serviços mais utilizados pelo público masculino #####");
		Stream<Map.Entry<String, Integer>> listarank = rank.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		listarank.forEach(rankeado -> System.out.println(rankeado));

	}

}