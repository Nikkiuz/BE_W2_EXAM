public class Rivista extends Elemento {
	private Periodicita periodicita;

	public Rivista(long codiceIsbn, String titolo, int anno, int numeroPagine, Periodicita periodicita) {
		super(codiceIsbn, titolo, anno, numeroPagine);
		this.periodicita = periodicita;
	}


	//metodi
	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "Rivista{" +
			"codiceIsbn=" + getCodiceIsbn() +
			", titolo='" + getTitolo() + '\'' +
			", anno=" + getAnno() +
			", numeroPagine=" + getNumeroPagine() +
			", periodicita=" + periodicita +
			'}';
	}
}