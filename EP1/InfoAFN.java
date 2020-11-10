import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InfoAFN {
	int numEstados;
	int numSimbolos;
	int numTransicoes;
	int estadoInicial;
	int numEstadosAceitacao;
	int numCadeias;
	Set<Integer> estadosAceitacao = new HashSet<Integer>();
	List<Transicao> transicoes = new ArrayList<Transicao>();
	List<List<Integer>> cadeias = new ArrayList<List<Integer>>();
}