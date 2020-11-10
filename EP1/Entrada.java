import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Entrada {
	public static void lerEInserirDados (List<InfoAFN> automatos, String args) {
		File arquivoTXT = new File(args);
		BufferedReader br = null;
		String linha = "";
		String txtDivisor = " ";
		int n = 1;
		
		try {

			  br = new BufferedReader(new FileReader(arquivoTXT));
			  linha = br.readLine();
			  String[] lendo = linha.split(txtDivisor);
			  Integer numAutomatos = Integer.parseInt(lendo[0]);
			  
			  while ((linha = br.readLine()) != null) {
				  lendo = linha.split(txtDivisor);
				  
				  if (n == 1) {
					  
					  InfoAFN afn = new InfoAFN();
					  afn.numEstados = Integer.parseInt(lendo[0]);
					  afn.numSimbolos = Integer.parseInt(lendo[1]);
					  afn.numTransicoes = Integer.parseInt(lendo[2]);
					  afn.estadoInicial = Integer.parseInt(lendo[3]);
					  afn.numEstadosAceitacao = Integer.parseInt(lendo[4]);
					  automatos.add(afn);
					  
				  } else if (n == 2) {
					  for (int i = 0; i < lendo.length; i++) {
						  automatos.get(automatos.size()-1).estadosAceitacao.add(Integer.parseInt(lendo[i]));
					  }
				  } else if (n > 2 && n < 3 + automatos.get(automatos.size()-1).numTransicoes) {
					  
					  int x = 0;
					  while (x < automatos.get(automatos.size()-1).transicoes.size()) {
						  if (automatos.get(automatos.size()-1).transicoes.get(x).estadoInicio == Integer.parseInt(lendo[0])) {
							  int i = 0;
							  while (i < automatos.get(automatos.size()-1).transicoes.get(x).caminhosPossiveis.size()) {
								  if (automatos.get(automatos.size()-1).transicoes.get(x).caminhosPossiveis.get(i).estadoFim == Integer.parseInt(lendo[2])) {
									  automatos.get(automatos.size()-1).transicoes.get(x).caminhosPossiveis.get(i).simbolos.add(Integer.parseInt(lendo[1]));	
								  }
								  i++;
							  }
							  if (i == automatos.get(automatos.size()-1).transicoes.get(x).caminhosPossiveis.size()) {
								  TransicaoAssociada tranAssoc = new TransicaoAssociada();
								  tranAssoc.simbolos.add(Integer.parseInt(lendo[1]));
								  tranAssoc.estadoFim = Integer.parseInt(lendo[2]);
								  automatos.get(automatos.size()-1).transicoes.get(x).caminhosPossiveis.add(tranAssoc);
							  }
						  }
						  x++;
					  }
					  if (x == automatos.get(automatos.size()-1).transicoes.size()) {
						  Transicao transicao = new Transicao();
						  transicao.estadoInicio = Integer.parseInt(lendo[0]);
						  
						  TransicaoAssociada tranAssoc = new TransicaoAssociada();
						  tranAssoc.simbolos.add(Integer.parseInt(lendo[1]));
						  tranAssoc.estadoFim = Integer.parseInt(lendo[2]);
						  
						  transicao.caminhosPossiveis.add(tranAssoc);
						  
						  automatos.get(automatos.size()-1).transicoes.add(transicao);
						  
					  }
					  
				  } else if (n == 3 + automatos.get(automatos.size()-1).numTransicoes) {
					  automatos.get(automatos.size()-1).numCadeias = Integer.parseInt(lendo[0]);
				  } else if (n > 3 + automatos.get(automatos.size()-1).numTransicoes && n <= 3 + automatos.get(automatos.size()-1).numTransicoes + automatos.get(automatos.size()-1).numCadeias) {
					  List<Integer> cadeia = new ArrayList<Integer>();
					  for (int i = 0; i < lendo.length; i++) {
						  cadeia.add(Integer.parseInt(lendo[i]));
					  }
					  automatos.get(automatos.size()-1).cadeias.add(cadeia);
					  if (n == 3 + automatos.get(automatos.size()-1).numTransicoes + automatos.get(automatos.size()-1).numCadeias) {
						  n = 0;
					  }
				  }
				  
				  n++;
			  }
			  
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
