package ar.edu.uno.prog1.genetico.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Poblacion {

	private ArrayList<Cromosoma> poblacion;
	private int cantidadCromosomas;
	private Cromosoma clave;

	public Poblacion(String clave) {
		this.poblacion = new ArrayList<Cromosoma>();
		this.clave = new Cromosoma(clave);
	}

	public Poblacion(String ruta, String clave) {
		this.poblacion = new ArrayList<Cromosoma>();
		this.clave = new Cromosoma(clave);
		this.cargarPoblacion(ruta);
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

//	public void setPoblacion(ArrayList<Cromosoma> poblacion) {
//		this.poblacion = poblacion;
//	}

	public void agregarCromosoma(Cromosoma cromosoma) {
		if (this.poblacion.size() < this.cantidadCromosomas)
			poblacion.add(cromosoma);
	}

//	public Cromosoma mutar() {
//		int k = (int) ((Math.random() * 10) % this.getPoblacion().size());
//		System.out.println(k);
//		Cromosoma c = this.getPoblacion().get(k);
//		return c;
//	}

	public void cargarPoblacion(String nombreArchivo) {

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

		// System.out.println(poblacion.mutar());
	}

	public void mostrarPoblacion() {

		for (Cromosoma c : this.poblacion)

			System.out.println(c);
		System.out.printf("\n\n");
	}

	public void mutarPoblacion() {

//		for (int i = 0; i < this.poblacion.size(); i++)
//			this.getPoblacion().get(i).mutar();
		
		for (int i = 0; i < 4; i++)
			this.poblacion.get((int)Math.random() * this.poblacion.size()).mutar();
		
	}

//	public void seleccionPoblacion() {
//
//		ArrayList<Cromosoma> poblacionTemporal = new ArrayList<Cromosoma>();
//
//		for (int i = 0; i < this.poblacion.size(); i++) {
//			Integer aleatorio1 = (int) ((Math.random() * 10) % 10);
//			Integer aleatorio2 = (int) ((Math.random() * 10) % 10);
//
//			poblacionTemporal.add(Cromosoma.seleccion(
//					this.poblacion.get(aleatorio1),
//					this.poblacion.get(aleatorio2)));
//		}
//
//		this.poblacion = poblacionTemporal;
//	}
	
	public void reproducir() {
	
		ArrayList<Cromosoma> temporal = new ArrayList<Cromosoma>();
		
		for (int iter = 0; iter < 5; iter++) {
			
			Cromosoma padre = this.poblacion.remove((int)(Math.random() * this.poblacion.size()));
			Cromosoma madre = this.poblacion.remove((int)(Math.random() * this.poblacion.size()));
			
			Cromosoma[] cromosomas = padre.reproducir(madre);
			
			// Ordeno los 4 cromosomas de mayor a menor segun su fitness
			for (int i = 0; i < 4; i++) {
				
				int mejor = i;
				for (int j = i; j < 4; j++) {
					
					if (cromosomas[j].calcularFitness(clave) > cromosomas[mejor].calcularFitness(clave))
						mejor = j;
				}
				Cromosoma tmp = cromosomas[i];
				cromosomas[i] = cromosomas[mejor];
				cromosomas[mejor] = tmp;

			}
			
			// Me quedo con los dos primeros
			System.out.printf("%d - %s\n", cromosomas[0].calcularFitness(clave), cromosomas[0].getRepresentacionGenesHexadecimal());
			System.out.printf("%d - %s\n", cromosomas[1].calcularFitness(clave), cromosomas[1].getRepresentacionGenesHexadecimal());
			temporal.add(cromosomas[0]);
			temporal.add(cromosomas[1]);
			
		}
		
		this.poblacion = temporal;
		
	}
	
	public boolean evaluarPoblacion () {
		
		boolean encontreSolucion = false;
		
		for (int i = 0; i < this.poblacion.size() && !encontreSolucion; i++) {
		
			if (this.poblacion.get(i).calcularFitness(clave) == 17600) {
				encontreSolucion = true;
				System.out.printf("%d - %s\n", this.poblacion.get(i).calcularFitness(clave), this.poblacion.get(i).getRepresentacionGenesHexadecimal());
			}

		}
		
		return encontreSolucion;
	}
	
	public void iniciar() {
		
		int cantIter = 0;
		boolean encontreSolucion = false;
		
		while (!encontreSolucion) {
			
			cantIter++;
			if (!(encontreSolucion = this.evaluarPoblacion())) {
				this.reproducir();
				this.mutarPoblacion();
			}
			
		}
		
		System.out.printf("Cantidad de iteraciones : %d\n", cantIter);
		
	}
	
}