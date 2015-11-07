package ar.edu.uno.prog1.genetico.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Poblacion {

	private ArrayList<Cromosoma> poblacion;
	private int cantidadCromosomas;
	
	public Poblacion() {
		this.setPoblacion(new ArrayList<Cromosoma>());
	}

	public ArrayList<Cromosoma> getPoblacion() {
		return this.poblacion;
	}

	public int getCantidadCromosomas() {
		return this.cantidadCromosomas;
	}
	
	public void setCantidadCromosomas(int cantidadCromosomas) {
		this.cantidadCromosomas = cantidadCromosomas;
	}
	
	public void setPoblacion(ArrayList<Cromosoma> poblacion) {
		this.poblacion = poblacion;
	}
	
	public void agregarCromosoma(Cromosoma cromosoma) {
		poblacion.add(cromosoma);
	}
	
	public Cromosoma mutar() {
		int k = (int) ((Math.random() * 10) % this.getPoblacion().size());
		System.out.println(k);
		Cromosoma c = this.getPoblacion().get(k);
		return c;
	}
	
public void cargarPoblacion (String nombreArchivo) {
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String linea = null;
				
		try {
			fileReader = new FileReader(nombreArchivo);
			bufferedReader = new BufferedReader(fileReader);
			
			linea = bufferedReader.readLine();
			this.setCantidadCromosomas(Integer.parseInt(linea));
			
			while ((linea = bufferedReader.readLine()) != null) {
				
				this.agregarCromosoma(new Cromosoma(linea));
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
		
		System.out.println(this.getCantidadCromosomas());
		
		for (Cromosoma c : this.poblacion)
			
			System.out.println(c);
		//System.out.println(poblacion.mutar());
	}
	
}