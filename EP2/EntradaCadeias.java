import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* EP2 - Introdução à Teoria da Computação
 * 
 * Trabalho desenvolvido por:
 *
 * - Larissa Fabião da Fonseca - 11208367
 * - Maria Fernanda Basso Santos - 11208197
 *
 * */

public class EntradaCadeias {
	public static void lerEInserirDados (List<List<String>> CadeiasGLC, String args) {
		File arquivoTXT = new File(args);
		BufferedReader br = null;
		String linha = "";
		int n = 0;
		
		try {

			  br = new BufferedReader(new FileReader(arquivoTXT));
			  
			  int i = -1;
			  List<String> adicionar = new ArrayList<String>();
			  
			  while ((linha = br.readLine()) != null) {
				  
				  if (n == 0) {
					  i = Integer.parseInt(linha);
					  adicionar = new ArrayList<String>();
				  } else if (n > 0 && n <= i) {
					  adicionar.add(linha);
					  if (n == i) {
						  CadeiasGLC.add(adicionar);
						  n = -1;
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
