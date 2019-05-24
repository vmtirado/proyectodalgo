
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Stack;

	import java.util.ArrayList;
	//Autora: Vilma Tirado Gómez
	public class ProblemaC {

		public static ArrayList<Integer> encontrarValorMax(int[][] Eventos, int equipos, Stack<ArrayList<Integer>> fila, ArrayList<Integer> actual, ArrayList<Integer> max){
			
			int sumaMax = calcularValor(Eventos, max);
			int sumaActual=calcularValor(Eventos, actual);
			if(sumaActual>=sumaMax){
				if(esValido(Eventos, actual, equipos)){
					max=actual;
				}
			}
			if(!actual.isEmpty()){
				int ultimo= actual.get(actual.size()-1);
				for (int i = ultimo+1; i < Eventos.length; i++) {
					ArrayList<Integer> copia= (ArrayList<Integer>) actual.clone();
					copia.add(i);
					fila.add(copia);
				}
			}
			else{
				for (int i = 0; i < Eventos.length; i++) {
					ArrayList<Integer> copia= (ArrayList<Integer>) actual.clone();
					copia.add(i);
					fila.add(copia);
				}
			}
			if(!fila.isEmpty()){
				max=encontrarValorMax(Eventos, equipos, fila, fila.pop(),max);
			}
			
			return max;
		}
		
		public static int calcularValor(int[][] Eventos, ArrayList<Integer> actual ){
			int suma =0;
			for (int i = 0; i < actual.size(); i++) {
				int k = actual.get(i);
				suma += Eventos[k][3];
			}
			return suma;
		}
		
		public static boolean esValido(int[][] Eventos,ArrayList<Integer> actual,int equipos){
			boolean val = true;
			int dia = calcularDiaMaximo(Eventos);
			for (int i = 0; i <= dia; i++) {
				int numeroEquipos=0;
				for (int j = 0; j < actual.size(); j++) {
					int[] evento1=Eventos[actual.get(j)];
					if((evento1[1]<=i&&evento1[2]>=i)||(evento1[1]==evento1[2]&&evento1[2]==i)){
						numeroEquipos++;
						if(numeroEquipos>equipos){
							return false;
						}
					}
				}
			}
			return val;
		}
		
		public static int calcularDiaMaximo(int[][] Eventos){
			int dia = calcularPrimerDiaMax(Eventos);
			int diaMax = dia;
			for (int i = 0; i < Eventos.length; i++) {
				int temp =diaMax;
				diaMax = Math.max(Eventos[i][2], dia);
				if(diaMax!=temp){
					dia=temp;
				}
			}
			return dia;
		}
		
		public static int calcularPrimerDiaMax(int[][]Eventos){
			int diaMax = 0;
			for (int i = 0; i < Eventos.length; i++) {
				diaMax = Math.max(Eventos[i][1], diaMax);
			}
			return diaMax;
		}
		
		public static void main(String[] args) throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String linea;
			int equipos;
			int numEventos;
			String data[][];
			while (true)
			{																	
				linea = br.readLine();
				if (linea.equals("0 0"))
					return;
				
				equipos = Integer.parseInt(linea.split(" ")[1]);;
				numEventos = Integer.parseInt(linea.split(" ")[0]);; 
				int[][] dataNum=new int[numEventos][4];
				data = new String[numEventos][4];

				for (int i = 0; i < numEventos; i++) {
					data[i] = br.readLine().split(" ");
					for (int j = 0; j < data[i].length; j++) {
						dataNum[i][j]=Integer.parseInt(data[i][j]);
					}
				}
				
					ArrayList<Integer> max=encontrarValorMax(dataNum, equipos, new Stack<ArrayList<Integer>>(), new ArrayList<Integer>(),new ArrayList<Integer>());
					System.out.println(calcularValor(dataNum, max));
				
				
			
			}
			
		}
	}


}
