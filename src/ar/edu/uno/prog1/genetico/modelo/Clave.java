package ar.edu.uno.prog1.genetico.modelo;

public class Clave {
	
	private static Clave clave; 
	private Cromosoma cromosomaTP;
	
	private Clave(String representacionHexadecimal) {
		this.setCromosoma(new Cromosoma(representacionHexadecimal, 0));
	}
	
	public static Clave getClave() {
		return clave;
	}

	public static void setClave(String representacionHexadecimal) {
		Clave.clave = new Clave(representacionHexadecimal);;
	}

	public Cromosoma getCromosoma() {
		return cromosomaTP;
	}

	public void setCromosoma(Cromosoma cromosoma) {
		this.cromosomaTP = cromosoma;
	}
	
	

}
