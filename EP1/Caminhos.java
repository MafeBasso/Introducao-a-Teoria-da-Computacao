import java.util.ArrayList;
import java.util.List;

public class Caminhos {
	public static void todosPossiveis (List<InfoAFN> automatos, List<List<Integer>> saida) {
		for (int i = 0; i < automatos.size(); i++) {
			List<Integer> saidaAutomato = new ArrayList<Integer>();
			  
			for (int j = 0; j < automatos.get(i).numCadeias; j++) {
				//cada cadeia tem varios caminhos possiveis
				List<List<Integer>> caminhos = new ArrayList<List<Integer>>();
				List<Integer> estadoinicial = new ArrayList<Integer>();
				estadoinicial.add(automatos.get(i).estadoInicial);
				caminhos.add(estadoinicial);
			  
				for (int k = 0; k < automatos.get(i).cadeias.get(j).size(); k++) {
					List<List<Integer>> caminhosQueDevemosExcluir = new ArrayList<List<Integer>>();
					List<List<Integer>> novosCaminhos = new ArrayList<List<Integer>>();
					
					for (int a = 0; a < caminhos.size(); a++) {
			
						for (int b = 0; b < automatos.get(i).numTransicoes; b++) {
						  
							//estado inicial da transicao = ultimo estado do caminho
							if (caminhos.get(a).get(caminhos.get(a).size()-1).equals(automatos.get(i).transicoes.get(b).estadoInicio)) {
				  
								for (int c = 0; c < automatos.get(i).transicoes.get(b).caminhosPossiveis.size(); c++) {
					  
									//simbolo da cadeia esta contido na transicao
									if (automatos.get(i).transicoes.get(b).caminhosPossiveis.get(c).simbolos.contains(automatos.get(i).cadeias.get(j).get(k))) {
										List<Integer> novoCaminho = new ArrayList<Integer>();
										novoCaminho.addAll(caminhos.get(a));
										novoCaminho.add(automatos.get(i).cadeias.get(j).get(k));
										novoCaminho.add(automatos.get(i).transicoes.get(b).caminhosPossiveis.get(c).estadoFim);
				  
										novosCaminhos.add(novoCaminho);
									}
			  
									//vamos lidar com cadeia vazia
									if (automatos.get(i).transicoes.get(b).caminhosPossiveis.get(c).simbolos.contains(0)) {
										if (caminhos.get(a).get(caminhos.get(a).size()-1).equals(automatos.get(i).transicoes.get(b).caminhosPossiveis.get(c).estadoFim)) {
											continue;
										} else {
											List<Integer> novoCaminho = new ArrayList<Integer>();
											novoCaminho.addAll(caminhos.get(a));
											novoCaminho.add(0);
											novoCaminho.add(automatos.get(i).transicoes.get(b).caminhosPossiveis.get(c).estadoFim);
			
											novosCaminhos.add(novoCaminho);
										}
									}
							  
								}
						  
							}
					  
						}
						caminhosQueDevemosExcluir.add(caminhos.get(a));
					}
					//acabei todas as transicoes de todos os caminhos que existiam antes desse simbolo da cadeia
					//hora de add os novos caminhos e excluir os que nao conseguiram sair do lugar
					caminhos.removeAll(caminhosQueDevemosExcluir);
					caminhos.addAll(novosCaminhos);
				  
				}
				//acabamos de fazer todos os caminhos possiveis com essa cadeia, vamos ver se ela foi aceita ou rejeitada
				int m = 0;
				while (m < caminhos.size()) {
					if (automatos.get(i).estadosAceitacao.contains(caminhos.get(m).get(caminhos.get(m).size()-1)) && caminhos.get(m).containsAll(automatos.get(i).cadeias.get(j))) {
						saidaAutomato.add(1);
						break;
					}
					m++;
				}
				if (m == caminhos.size()) saidaAutomato.add(0);
				
			}
			saida.add(saidaAutomato);
		}
	}

}
