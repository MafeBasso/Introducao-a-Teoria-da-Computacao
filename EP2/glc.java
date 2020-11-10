import java.util.ArrayList;
import java.util.List;

public class glc {

	public static void main(String[] args) {
		
		List<InfoGLC> GLCs = new ArrayList<InfoGLC>();
		EntradaGLC.lerEInserirDados(GLCs, args[0]);
		
		List<List<String>> CadeiasGLC = new ArrayList<List<String>>();
		EntradaCadeias.lerEInserirDados(CadeiasGLC, args[1]);
		
		// TODO Auto-generated method stub
		
		//ver se é cadeia vazia e ver se o simbolo inicial gera ela, se sim: cadeia aceita, se não: cadeia rejeitada
		//for i = 1...n
			//ver se a variavel gera o Wi (W = cadeia)
			//se sim coloca na tabela(i,i)
		//for l = 2...n
			//for i = 1...n-l+1
				//j=i+l-1
				//for k = i...j-1
				//for cada A => BC
					//if tabela(i,k) contem B  E  tabela(k+1,j) contem C ENTÃO coloca A em (i,j)

	}

}
