//Maria Fernanda Basso Santos - 11208197 e Larissa Fabiao da Fonseca - 11208367
import java.util.ArrayList;
import java.util.List;

public class afn {
	
	public static void main(String[] args) {
		
		List<InfoAFN> automatos = new ArrayList<InfoAFN>();
		Entrada.lerEInserirDados(automatos, args[0]);
		
		List<List<Integer>> saida = new ArrayList<List<Integer>>();
		Caminhos.todosPossiveis(automatos, saida);
		
		Saida.imprimir(saida, args[1]);
		
	}

}
