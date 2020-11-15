import java.util.ArrayList;
import java.util.List;

public class CYK {

	public static void verificarAceitacoesERejeicoes (List<InfoGLC> GLCs, List<List<String>> CadeiasGLC, List<List<Integer>> AceitacoesERejeicoesDasGLCs) {
		
		//for para percorrer todas as cadeias de todas as gramaticas
		for (int cont1 = 0; cont1 < CadeiasGLC.size(); cont1++) {
			
			List<Integer> AceitacoesERejeicoesDaGLC = new ArrayList<Integer>();
			
			//for para percorrer todas as cadeias de uma gramatica
			for (int cont2 = 0; cont2 < CadeiasGLC.get(cont1).size(); cont2++) {
				
				//for para percorrer as variaveis da cadeia dessa gramatica
				String[] cadeiaSeparada = CadeiasGLC.get(cont1).get(cont2).split(" ");
				
				String[][] matriz = new String[cadeiaSeparada.length+1][cadeiaSeparada.length+1];
				
				for (int a=0; a <= cadeiaSeparada.length; a++) {
					for (int b=0; b <= cadeiaSeparada.length; b++) {
						matriz[a][b] = "-1";
					}
				}
				
				//ver se é cadeia vazia
				if (cadeiaSeparada[0].equals("&")) {
					//ver se o simbolo inicial gera ela, se sim: cadeia aceita, se não: cadeia rejeitada
					if (GLCs.get(cont1).regrasDeSubstituicao.get(0).variaveisDireita.contains("&")) AceitacoesERejeicoesDaGLC.add(1);
					else AceitacoesERejeicoesDaGLC.add(0);
				} else {

					for (int i = 1; i <= cadeiaSeparada.length; i++) {
						
						for (int cont3 = 0; cont3 < GLCs.get(cont1).regrasDeSubstituicao.size(); cont3++) {
							//ver se a variavel gera o Wi (W = cadeia, i = posicao)
							if (GLCs.get(cont1).regrasDeSubstituicao.get(cont3).variaveisDireita.contains(cadeiaSeparada[i-1])) {
								//se sim coloca na tabela(i,i)
								if (matriz[i][i] != "-1") matriz[i][i] = matriz[i][i].concat(" " + GLCs.get(cont1).regrasDeSubstituicao.get(cont3).variavelEsquerda);
								else matriz[i][i] = GLCs.get(cont1).regrasDeSubstituicao.get(cont3).variavelEsquerda;
							}
						}
						
					}
					
					//for l = 2...n
					for (int l = 2; l <= cadeiaSeparada.length; l++) {
						
						//for i = 1...n-l+1
						for (int i = 1; i <= cadeiaSeparada.length-l+1; i++) {
							
							//j=i+l-1
							int j = i+l-1;

							//for k = i...j-1
							for (int k = i; k <= j-1; k++) {
								
								for (int cont4 = 0; cont4 < GLCs.get(cont1).regrasDeSubstituicao.size(); cont4++) {
									
									int vericacaoVar1 = 0;
									int vericacaoVar2 = 0;
									List<String> varsDireita = new ArrayList<String>();
									varsDireita = GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variaveisDireita;

									//for para cada A => BC
									for (int cont5 = 0; cont5 < varsDireita.size(); cont5++) {
										
										String[] var = varsDireita.get(cont5).split(" ");
										for (int cont6 = 0; cont6 < var.length; cont6++) {
											if (matriz[i][k].contains(var[cont6])) {
												vericacaoVar1 = 1;
												//se tabela(i,k) contem B  E  tabela(k+1,j) contem C ENTÃO colocamos A na tabela(i,j)
												if (vericacaoVar1 == 1 && vericacaoVar2 == 1) {
													if (matriz[i][j] != "-1") matriz[i][j] = matriz[i][j].concat(" " + GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda);
													else matriz[i][j] = GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda;
												}
											}
											if (matriz[k+1][j].contains(var[cont6])) {
												vericacaoVar2 = 1;
												//se tabela(i,k) contem B  E  tabela(k+1,j) contem C ENTÃO colocamos A na tabela(i,j)
												if (vericacaoVar1 == 1 && vericacaoVar2 == 1) {
													if (matriz[i][j] != "-1") matriz[i][j] = matriz[i][j].concat(" " + GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda);
													else matriz[i][j] = GLCs.get(cont1).regrasDeSubstituicao.get(cont4).variavelEsquerda;
												}
											}
										}
										
									}
									
								}
								
							}
							
						}
					}

					//se o simbolo inicial está em tabela(1,n) ENTÃO aceite, se não rejeite
					if (matriz[1][cadeiaSeparada.length].contains(GLCs.get(cont1).inicial)) {
						AceitacoesERejeicoesDaGLC.add(1);
					} else AceitacoesERejeicoesDaGLC.add(0);
					
				}
				
			}
			
			AceitacoesERejeicoesDasGLCs.add(AceitacoesERejeicoesDaGLC);
			
		}
		
	}

}
