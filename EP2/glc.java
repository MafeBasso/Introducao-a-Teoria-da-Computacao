import java.util.ArrayList;
import java.util.List;

/* EP2 - Introdução a teoria da computação
 * 
 * Trabalho desenvolvido por:
 *
 * - Larissa Fabião da Fonseca - 11208367
 * - Maria Fernanda Basso Santos - 11208197
 *
 * */

public class glc {

	public static void main(String[] args) {
		
		List<InfoGLC> GLCs = new ArrayList<InfoGLC>();
		EntradaGLC.lerEInserirDados(GLCs, args[0]);
		
		List<List<String>> CadeiasGLC = new ArrayList<List<String>>();
		EntradaCadeias.lerEInserirDados(CadeiasGLC, args[1]);
		
		List<List<Integer>> AceitacoesERejeicoesDasGLCs = new ArrayList<List<Integer>>();
		CYK.verificarAceitacoesERejeicoes(GLCs, CadeiasGLC, AceitacoesERejeicoesDasGLCs);
		
		Saida.imprimir(AceitacoesERejeicoesDasGLCs, args[2]);

	}

}
