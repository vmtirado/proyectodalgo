import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Punto2 {

	public static final int V = 5; 
	static int count = 0; 

	public void DFS(int graph[][], boolean marked[], 
			int n, int vert, int start) { 

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
		for (int i = 0; i < V; i++) 
			if (!marked[i] && graph[vert][i] == 1) 

				// DFS for searching path by 
				// decreasing length by 1 
				DFS(graph, marked, n-1, i, start); 

		// marking vert as unvisited to make it 
		// usable again 
		marked[vert] = false; 
	} 

	// Count cycles of length N in an  
	// undirected and connected graph. 
	public  int contarCircuitos(int graph[][], int n) { 

		// all vertex are marked un-visited 
		// initially. 
		boolean marked[] = new boolean[V]; 

		// Searching for cycle by using  
		// v-n+1 vertices 
		for (int i = 0; i < V - (n - 1); i++) { 
			DFS(graph, marked, n-1, i, i); 

			// ith vertex is marked as visited 
			// and will not be visited again 
			marked[i] = true; 
		} 

		return count / 2;  
	} 

	public static void main(String[] args) { 
		Punto2 instancia = new Punto2();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();

			while(line!=null && line.length()>0 && !"0 0".equals(line)) {
				//valores iniciales del grafo
				final String [] in = line.split(" ");
				int k,n= Integer.parseInt(in[0]); Integer.parseInt(in[1]);
				line = br.readLine();
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				int respuesta = instancia.contarCircuitos(numeros);
				System.out.println(respuesta);
				line = br.readLine();
				
				int graph[][] = {{0, 1, 0, 1, 0}, 
						{1, 0, 1, 0, 1}, 
						{0, 1, 0, 1, 0}, 
						{1, 0, 1, 0, 1}, 
						{0, 1, 0, 1, 0}}; 
				
				System.out.println(instancia.contarCircuitos(graph, k)); 
			}
		}
	}

}
