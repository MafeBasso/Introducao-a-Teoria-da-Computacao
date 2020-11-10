import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Saida {
	public static void imprimir(List<List<Integer>> saida, String args) {
		File destino = new File(args);
        BufferedWriter ps = null;
		  try{
			  ps = new BufferedWriter(new FileWriter(destino));
            
            for (int i = 0; i < saida.size(); i++) {
          	  for (int j = 0; j < saida.get(i).size(); j++) {
                    ps.write(saida.get(i).get(j) + " ");
          	  }
          	  ps.newLine();
            }
            
       } catch (FileNotFoundException e) {
	        e.printStackTrace();
	     } catch(IOException e) {
          e.printStackTrace();
       } finally {
      	 if (ps != null) {
      		 try {
	                ps.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
       }
	}
}
