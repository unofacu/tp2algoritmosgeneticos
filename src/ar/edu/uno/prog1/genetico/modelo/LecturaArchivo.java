package ar.edu.uno.prog1.genetico.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaArchivo {

	public static void main(String[] args) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String linea = null;
		Poblacion poblacion = new Poblacion();
		try {
			fileReader = new FileReader(
					"src//ar//edu//uno//prog1//genetico//resources//poblacion.txt");
			bufferedReader = new BufferedReader(fileReader);
			while ((linea = bufferedReader.readLine()) != null) {
				poblacion.agregarCromosoma(new Cromosoma(linea));
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		try {
			if (bufferedReader != null)
				bufferedReader.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		for (Cromosoma c : poblacion.getPoblacion())
			System.out.println(c);
		System.out.println(poblacion.mutar());
	}

}