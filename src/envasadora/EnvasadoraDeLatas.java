package envasadora;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EnvasadoraDeLatas
{
	char[] latas;
	int maxSecuencia;
	int segMaxSecuencia;
	int distancia;
	
	public EnvasadoraDeLatas (File archivo) throws FileNotFoundException
	{
		Scanner sc = new Scanner(archivo);
		
		String cadena = sc.nextLine().replace(" ", "").replace("F", "");
		latas = cadena.toCharArray();
		
		sc.close();
	}
	
	public void procesar ()
	{
		int i = 0;
		int contador = 0;
		int posFSec = 0;
		int posFSegSec = 0;
		
		while (i < latas.length)
		{
			while (i < latas.length && latas[i] == 'G')
			{
				contador++;
				i++;
			}
			
			if (contador > maxSecuencia)
			{
				//
				segMaxSecuencia = maxSecuencia;
				posFSegSec = posFSec;
				//
				
				maxSecuencia = contador;
				posFSec = i;
			}
			else if (contador > segMaxSecuencia)
			{
				segMaxSecuencia = contador;
				posFSegSec = i;
			}
			
			contador = 0;
			i++;
		}
		
		distancia = posFSec > posFSegSec ? posFSec - posFSegSec - maxSecuencia : posFSegSec - posFSec - segMaxSecuencia;
	}
	
	public void mostrarResultados (File arch) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(arch));
		
		out.println(latas.length);
		out.println(maxSecuencia);
		out.println(segMaxSecuencia);
		out.println(distancia);
		out.close();
	}
}
