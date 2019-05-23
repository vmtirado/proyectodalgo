import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Punto2 {

	static int count = 0; 

	public void DFS(int graph[][], boolean marked[], 
			int n, int vert, int start, int v) { 

		// mark the vertex vert as visited 
		marked[vert] = true; 

		// if the path of length (n-1) is found 
		if (n == 0) { 

			// mark vert as un-visited to  
			// make it usable again 
			marked[vert] = false; 

			// Check if vertex vert end  
			// with vertex start 
			if (graph[vert][start] == 1) { 
				count++; 
				return; 
			} else
				return; 
		} 

		// For searching every possible  
		// path of length (n-1) 
		for (int i = 0; i < v; i++) 
			if (!marked[i] && graph[vert][i] == 1) 

				// DFS for searching path by 
				// decreasing length by 1 
				DFS(graph, marked, n-1, i, start,v); 

		// marking vert as unvisited to make it 
		// usable again 
		marked[vert] = false; 
	} 

	// Count cycles of length N in an  
	// undirected and connected graph. 
	public  int contarCircuitos(int graph[][], int k, int v) { 

		// all vertex are marked un-visited 
		// initially. 
		boolean marked[] = new boolean[v]; 

		// Searching for cycle by using  
		// v-n+1 vertices 
		for (int i = 0; i < v - (k - 1); i++) { 
			DFS(graph, marked, k-1, i, i,v); 

			// ith vertex is marked as visited 
			// and will not be visited again 
			marked[i] = true; 
		} 

		return count / 2;  
	} 

	public static void main(String[] args) { 
		Punto2 instancia = new Punto2();
		try {
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is); 
			String line = br.readLine();

			//valores iniciales del grafo
			final String [] in = line.split(" ");
			int n=Integer.parseInt(in[0]);
			int k= Integer.parseInt(in[1]);
			int grafo[][]= new int[n][n];
			line = br.readLine();
			for(int i=1; i<n && line!=null && line.length()>0 && !"0 0".equals(line); i++) {

				final String [] dataStr = line.split(" ");
				for (int j=1; j<dataStr.length; j++)
				{
					//Valor de la columna 
					int c= Integer.parseInt(dataStr[j]);
					grafo[i-1][c-1]=1;
					grafo[c-1][i-1]=1;
				}
				line = br.readLine();

			}
			
			for (int i = 0; i < grafo.length; i++) {
				for (int j = 0; j < grafo.length; j++) {
					System.out.print(grafo[i][j]+ " ");
				}
				System.out.println();
			}
			System.out.println("Aqui va el grafo");

			int respuesta = instancia.contarCircuitos(grafo,k,n);
			System.out.println(respuesta);

		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}


}
