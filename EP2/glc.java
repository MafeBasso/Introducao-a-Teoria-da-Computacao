import java.util.ArrayList;
import java.util.List;

public class glc {

	public static void main(String[] args) {
		
		List<InfoGLC> GLCs = new ArrayList<InfoGLC>();
		EntradaGLC.lerEInserirDados(GLCs, args[0]);
		
		List<List<String>> CadeiasGLC = new ArrayList<List<String>>();
		EntradaCadeias.lerEInserirDados(CadeiasGLC, args[1]);
		
		List<List<Integer>> AceitacoesERejeicoesDasGLCs = new ArrayList<List<Integer>>();
		
		//for para percorrer todas as cadeias de todas as gramaticas
		for (int cont1 = 0; cont1 < CadeiasGLC.size(); cont1++) {
			
			List<Integer> AceitacoesERejeicoesDaGLC = new ArrayList<Integer>();
			
			//for para percorrer todas as cadeias de uma gramatica
			for (int cont2 = 0; cont2 < CadeiasGLC.get(cont1).size(); cont2++) {
				
				//for para percorrer as variaveis da cadeia dessa gramatica
				String[] cadeiaSeparada = CadeiasGLC.get(cont1).get(cont2).split(" ");
				
				String[][] matriz = new String[cadeiaSeparada.length][cadeiaSeparada.length];
				
				for (int a=0; a < cadeiaSeparada.length; a++) {
					for (int b=0; b < cadeiaSeparada.length; b++) {
						matriz[a][b] = "-1";
					}
				}
				
				//ver se é cadeia vazia
				if (cadeiaSeparada[0].equals("&")) {
					//ver se o simbolo inicial gera ela, se sim: cadeia aceita, se não: cadeia rejeitada
					if (GLCs.get(cont1).regrasDeSubstituicao.get(0).variaveisDireita.contains("&")) AceitacoesERejeicoesDaGLC.add(1);
					else AceitacoesERejeicoesDaGLC.add(0);
				} else {

					for (int i = 0; i < cadeiaSeparada.length; i++) {
						
						for (int cont3 = 0; cont3 < GLCs.get(cont1).regrasDeSubstituicao.size(); cont3++) {
							//ver se a variavel gera o Wi (W = cadeia, i = posicao)
							if (GLCs.get(cont1).regrasDeSubstituicao.get(cont3).variaveisDireita.contains(cadeiaSeparada[i])) {
								//se sim coloca na tabela(i,i)
								matriz[i][i] = GLCs.get(cont1).regrasDeSubstituicao.get(cont3).variavelEsquerda;
							}
						}
						
					}
					
					for (int l = 1; l < cadeiaSeparada.length; l++) {
						for (int i = 0; i < cadeiaSeparada.length-l+1; i++) {
							int j = i+l-2;
							for (int k = i; k < j-1; k++) {
								for (int cont4 = 0; cont4 < GLCs.get(cont1).regrasDeSubstituicao.size(); cont4++) {
									
									int int1 = 0;
									int int2 = 0;
									List<String> abacate = new ArrayList<String>();
									abacate = GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variaveisDireita;
									for (int cont5 = 0; cont5 < abacate.size(); cont5++) {
										String[] var = abacate.get(cont5).split(" ");
										for (int cont6 = 0; cont6 < var.length; cont6++) {
											if (matriz[i][k].contains(var[cont6])) {
												int1 = 1;
											}
											if (matriz[k+1][j].contains(var[cont6])) {
												int2 = 1;
											}
										}
									}

									if (int1 == 1 && int2 == 1) {
										if (matriz[i][j] != "-1") matriz[i][j] = matriz[i][j].concat(" " + GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda);
										else matriz[i][j] = GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda;
									}
									
								}
							}
							
						}
					}
					
					if (matriz[0][cadeiaSeparada.length-1].contains(GLCs.get(cont1).regrasDeSubstituicao.get(0).variavelEsquerda)) {
						AceitacoesERejeicoesDaGLC.add(1);
					} else AceitacoesERejeicoesDaGLC.add(0);
					
					for (int a=0; a < cadeiaSeparada.length; a++) {
						for (int b=0; b < cadeiaSeparada.length; b++) {
							System.out.print(matriz[a][b] + " ");
						}
						System.out.println();
					}
					
				}
				
			}
			
			AceitacoesERejeicoesDasGLCs.add(AceitacoesERejeicoesDaGLC);
			
		}
		
		System.out.println(AceitacoesERejeicoesDasGLCs);
		
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
		//se o simbolo inicial está em tabela(1,n) ENTÃO aceite,se não rejeite

	}

}
