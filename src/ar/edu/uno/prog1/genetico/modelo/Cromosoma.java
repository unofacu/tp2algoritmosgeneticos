package ar.edu.uno.prog1.genetico.modelo;

public class Cromosoma { 
	
	private String representacionGenesHexadecimal;
	private String representacionGenesBinaria;
	private Integer fitness;
	
	public Cromosoma(String representacionGenesHexadecimal) {
		this.setRepresentacionGenesHexadecimal(representacionGenesHexadecimal);
		this.setRepresentacionGenesBinaria(this.getConvertirHexadecimalABinario());
		this.setFitness(this.calcularFitness());
	}
	
	public Cromosoma(String representacionGenesHexadecimal, Integer fitness) {
		this.setRepresentacionGenesHexadecimal(representacionGenesHexadecimal);
		this.setRepresentacionGenesBinaria(this.getConvertirHexadecimalABinario());
		this.setFitness(fitness);
	}
	
	private String getConvertirHexadecimalABinario() {
		String temp = null;
		temp = Long.toBinaryString(Long.parseLong(this.getRepresentacionGenesHexadecimal(), 16));
		
		for (int i = temp.length(); i < 40; i++ )
			
			temp = "0" + temp;
		
		return temp;
	}
	
	private String getConvertirBinarioAHexadecimal() {
		
		String temp;
		temp = Long.toHexString(Long.parseLong(this.getRepresentacionGenesBinaria(), 2)).toUpperCase();
		
		for (int i = temp.length(); i < 10; i++ )
			
			temp = "0" + temp;
		
		return temp;
		
	}
	
	private Integer calcularFitness() {
		return (this.getCantidadHexadecimalCoincidentes() + 1) * (int) (Math.pow(this.getCantidadBinariosCoincidentes(), 2));
	}
	
	private Integer getCantidadHexadecimalCoincidentes() {
		Integer h = 0;
		for (Integer i = 0; i<this.getRepresentacionGenesHexadecimal().length(); i++)
			if (this.getRepresentacionGenesHexadecimal().charAt(i) == Clave.getClave().getCromosoma().getRepresentacionGenesHexadecimal().charAt(i))
				h++;
		return h;
	}
	
	private Integer getCantidadBinariosCoincidentes() {
		Integer b = 0;
		for (Integer i = 0; i<this.getRepresentacionGenesBinaria().length(); i++)
			if (this.getRepresentacionGenesBinaria().charAt(i) == Clave.getClave().getCromosoma().getRepresentacionGenesBinaria().charAt(i))
				b++;
		return b;
	}

	public String getRepresentacionGenesHexadecimal() {
		return representacionGenesHexadecimal;
	}

	public void setRepresentacionGenesHexadecimal(
			String representacionGenesHexadecimal) {
		this.representacionGenesHexadecimal = representacionGenesHexadecimal;
	}

	public String getRepresentacionGenesBinaria() {
		return representacionGenesBinaria;
	}

	public void setRepresentacionGenesBinaria(String representacionGenesBinaria) {
		this.representacionGenesBinaria = representacionGenesBinaria;
	}

	public Integer getFitness() {
		return fitness;
	}

	public void setFitness(Integer fitness) {
		this.fitness = fitness;
	}
	
	public String toString() {
		
		//return "Hexa= " + this.representacionGenesHexadecimal + " Binario= " + this.representacionGenesBinaria + " Fitness= " + this.getFitness();
		
		return this.getRepresentacionGenesHexadecimal().concat(" ").concat(this.getRepresentacionGenesBinaria()).concat(" ").concat(" ").concat(String.valueOf(this.getFitness()));
	}
	
	
	public void mutarCromosoma() {
		char [] genes = this.getRepresentacionGenesBinaria().toCharArray();
		for (Integer i=1; i<=4; i++) {
			Integer aleatorio = (int) ((Math.random() * 40) % this.getRepresentacionGenesBinaria().length());
			// System.out.println(aleatorio);
			genes[aleatorio] = (genes[aleatorio] == '1')?'0':'1';
		}
		this.setRepresentacionGenesBinaria(new String(genes));
		this.setRepresentacionGenesHexadecimal(this.getConvertirBinarioAHexadecimal());
		this.setFitness(this.calcularFitness());
	}
	
	public static Cromosoma[] reproducir(Cromosoma cromosoma1, Cromosoma cromosoma2) {
		return new Cromosoma[]{null, null};
	}
	
	public static Cromosoma seleccion (Cromosoma cromosoma1, Cromosoma cromosoma2) {
		
		if (cromosoma1.calcularFitness() >= cromosoma2.calcularFitness())
		
			return cromosoma1;
					
			else return cromosoma2;

		
	}
	

}
