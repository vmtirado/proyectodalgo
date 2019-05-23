import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Punto1 {

	public static void main(String[] args) throws Exception {
		Punto1 instancia = new Punto1();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();

			while(line!=null && line.length()>0 && !"0".equals(line)) {
				int tam=Integer.parseInt(line);
				line = br.readLine();
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				int respuesta = instancia.buscarSubArreglo(numeros);
				System.out.println(respuesta);
				line = br.readLine();
			}
		}
	}



	public int buscarSubArreglo(int[] arreglo){
		//Longitud del arreglo mas largo 
		int m= 1;
		int c=1;
		for (int i=1; i<arreglo.length; i++)
		{
			int n= arreglo[i]<0? arreglo[i]*-1: arreglo[i];

			if (n<arreglo[i-1])
			{
				if (c>m)
				{
					m=c; 
				}
				c=0;
			}
			c++;	
		}
		return m;
	}
}
