public class Libro extends Elemento {
	String autore;
	String genere;

	//costruttore
	public Libro(long codiceIsbn, String titolo, int anno, int numeroPagine, String autore, String genere) {
		super(codiceIsbn, titolo, anno, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	//metodi
	public String getAutore() {
		return autore;
	}

	public String setAutore(String newAutore) {
		return this.autore = newAutore;
	}

	public String getGenere() {
		return genere;
	}

	public String setGenere(String newGenere){
		return this.genere=newGenere;
	}

	@Override
	public String toString(){
		return "Libro{" +
			"codiceIsbn=" + getCodiceIsbn() +
			", titolo='" + getTitolo() + '\'' +
			", anno=" + getAnno() +
			", numeroPagine=" + getNumeroPagine() +
			", autore=" + getAutore() +
			", genere=" + getGenere() +
			'}';
	}

}
