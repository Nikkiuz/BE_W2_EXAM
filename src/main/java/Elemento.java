//creo una classe astratta che vada bene per le interfacce Libri e Riviste

public abstract class Elemento {
	protected long codiceIsbn;
	protected String titolo;
	protected int anno;
	protected int numeroPagine;

	//costruttore
	public Elemento(long codiceIsbn, String titolo, int anno, int numeroPagine) {
		this.codiceIsbn = codiceIsbn;
		this.titolo = titolo;
		this.anno = anno;
		this.numeroPagine = numeroPagine;
	}

	//creo i metodi getter e setter

	public long getCodiceIsbn() {
		return codiceIsbn;
	}

	public long setCodiceIsbn(long newCodiceIsbn) {
		return this.codiceIsbn = newCodiceIsbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public String setTitolo(String newTitolo) {
		return this.titolo = newTitolo;
	}

	public int getAnno() {
		return anno;
	}

	public int setAnno(int newAnno) {
		return this.anno = newAnno;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	public int setNumeroPagine(int newNumeroPagine) {
		return this.numeroPagine = newNumeroPagine;
	}
}
