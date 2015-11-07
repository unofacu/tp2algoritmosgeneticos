package ar.edu.uno.prog1.genetico.modelo;

public class Main {

	public static void main(String[] args) {
		
		Clave.setClave("1AC7D9B273");
		
		Poblacion poblacion = new Poblacion ();
		
		//Cromosoma cromosoma1 = new Cromosoma("A1");
		//Cromosoma cromosoma2 = new Cromosoma("FA");
		//System.out.println(cromosoma1);
		//System.out.println(cromosoma2);
		//cromosoma1.mutar();
		//System.out.println(cromosoma1);
		//cromosoma2.mutar();
		//System.out.println(cromosoma2);
		
		poblacion.cargarPoblacion("poblacion.in");
	}

}
