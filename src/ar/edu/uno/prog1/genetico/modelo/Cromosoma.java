package ar.edu.uno.prog1.genetico.modelo;

public class Cromosoma { 
	
	private String representacionGenesHexadecimal;
	private String representacionGenesBinaria;
	
	public Cromosoma(String representacionGenesHexadecimal) {
		this.setRepresentacionGenesHexadecimal(representacionGenesHexadecimal);
		this.setRepresentacionGenesBinaria(this.getConvertirHexadecimalABinario());
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
	
	public Integer calcularFitness(Cromosoma clave) {
		return (this.getCantidadHexadecimalCoincidentes(clave) + 1) * (int) (Math.pow(this.getCantidadBinariosCoincidentes(clave), 2));
	}
	
	private Integer getCantidadHexadecimalCoincidentes(Cromosoma clave) {
		Integer h = 0;
		for (Integer i = 0; i<this.getRepresentacionGenesHexadecimal().length(); i++)
			if (this.getRepresentacionGenesHexadecimal().charAt(i) == clave.getRepresentacionGenesHexadecimal().charAt(i))
				h++;
		return h;
	}
	
	private Integer getCantidadBinariosCoincidentes(Cromosoma clave) {
		Integer b = 0;
		for (Integer i = 0; i<this.getRepresentacionGenesBinaria().length(); i++)
			if (this.getRepresentacionGenesBinaria().charAt(i) == clave.getRepresentacionGenesBinaria().charAt(i))
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
		this.representacionGenesHexadecimal = Long.toHexString(Long.parseUnsignedLong(this.representacionGenesBinaria, 2)).toUpperCase();
	}

//	public String toString() {
//		
//		//return "Hexa= " + this.representacionGenesHexadecimal + " Binario= " + this.representacionGenesBinaria + " Fitness= " + this.getFitness();
//		
//		return this.getRepresentacionGenesHexadecimal().concat(" ").concat(this.getRepresentacionGenesBinaria()).concat(" ").concat(" ").concat(String.valueOf(this.getFitness()));
//	}
	
	
	public void mutar() {
		char [] genes = this.getRepresentacionGenesBinaria().toCharArray();
		for (Integer i=1; i<=4; i++) {
			Integer aleatorio = (int)(Math.random() * 40);
			// System.out.println(aleatorio);
			genes[aleatorio] = (genes[aleatorio] == '1')?'0':'1';
		}
		this.setRepresentacionGenesBinaria(new String(genes));
		this.setRepresentacionGenesHexadecimal(this.getConvertirBinarioAHexadecimal());
	}
	
	public Cromosoma[] reproducir(Cromosoma pareja) {

		Cromosoma[] cromosomas = new Cromosoma[4];
		
		cromosomas[0] = this;
		cromosomas[1] = pareja;
		Cromosoma hijo1 = new Cromosoma(this.getRepresentacionGenesHexadecimal());
		Cromosoma hijo2 = new Cromosoma(pareja.getRepresentacionGenesHexadecimal());
		
		int gen = (int)(Math.random() * 40);
		
		char[] genes1 = hijo1.getRepresentacionGenesBinaria().toCharArray();
		char[] genes2 = hijo2.getRepresentacionGenesBinaria().toCharArray();
		
		for (;gen < 40; gen++) {
			
			char genTmp = genes1[gen];
			genes1[gen] = genes2[gen];
			genes2[gen] = genTmp;
		}
		
		hijo1.setRepresentacionGenesBinaria(new String(genes1));
		hijo2.setRepresentacionGenesBinaria(new String(genes2));
		
		cromosomas[2] = hijo1;
		cromosomas[3] = hijo2;
		
		return cromosomas;
		
	}
	
//	public static Cromosoma seleccion (Cromosoma cromosoma1, Cromosoma cromosoma2) {
//		
//		if (cromosoma1.calcularFitness() >= cromosoma2.calcularFitness())
//		
//			return cromosoma1;
//					
//			else return cromosoma2;
//
//		
//	}
	

}
