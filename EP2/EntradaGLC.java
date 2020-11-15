import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/* EP2 - Introdução à Teoria da Computação
 * 
 * Trabalho desenvolvido por:
 *
 * - Larissa Fabião da Fonseca - 11208367
 * - Maria Fernanda Basso Santos - 11208197
 *
 * */

public class EntradaGLC {
	public static void lerEInserirDados (List<InfoGLC> GLCs, String args) {
		File arquivoTXT = new File(args);
		BufferedReader br = null;
		String linha = "";
		String txtDivisor = " ";
		int n = 0;
		
		try {

			  br = new BufferedReader(new FileReader(arquivoTXT));
			  
			  while ((linha = br.readLine()) != null) {
				  String[] lendo = linha.split(txtDivisor);
				  
				  if (n == 1) {
					  InfoGLC glc = new InfoGLC();
					  glc.numVariaveis = Integer.parseInt(lendo[0]);
					  glc.numSimbolosTerminais = Integer.parseInt(lendo[1]);
					  glc.numRegrasDeSubstituicao = Integer.parseInt(lendo[2]);
					  GLCs.add(glc);
				  } else if (n == 2) {
					  GLCs.get(GLCs.size()-1).inicial = lendo[0];
					  for (int i = 0; i < lendo.length; i++) {
						  Substituicao subst = new Substituicao();
						  subst.variavelEsquerda = lendo[i];
						  GLCs.get(GLCs.size()-1).regrasDeSubstituicao.add(subst);
					  }
				  } else if (n == 3) {
					  for (int i = 0; i < lendo.length; i++) {
						  GLCs.get(GLCs.size()-1).terminais.add(lendo[i]);
					  }
				  } else if (n > 3) {
					  for (int i = 0; i < GLCs.get(GLCs.size()-1).regrasDeSubstituicao.size(); i++) {
						  if (GLCs.get(GLCs.size()-1).regrasDeSubstituicao.get(i).variavelEsquerda.equals(lendo[0])) {
							  if (lendo.length > 3) {
								  GLCs.get(GLCs.size()-1).regrasDeSubstituicao.get(i).variaveisDireita.add(lendo[2].concat(" " + lendo[3]));
							  } else {
								  GLCs.get(GLCs.size()-1).regrasDeSubstituicao.get(i).variaveisDireita.add(lendo[2]);
							  }
						  }
					  }
					  if (n == GLCs.get(GLCs.size()-1).numRegrasDeSubstituicao + 3) n = 0;
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
