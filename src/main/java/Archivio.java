import java.util.ArrayList;
import java.util.List;

public class Archivio {
	private List<Elemento> elementi;

	//costruttore
	public Archivio() {
		this.elementi = new ArrayList<>();
	}

	//metodo per aggiungere un nuovo elemento
	public void aggiungiElemento(Elemento nuovoElemento) throws ElementoDuplicatoException {
		for (Elemento elemento : elementi) {
			if (elemento.getCodiceIsbn() == nuovoElemento.getCodiceIsbn()) {
				throw new ElementoDuplicatoException("Esiste già un elemento con il codice ISBN: " + nuovoElemento.getCodiceIsbn());
			}
		}

		elementi.add(nuovoElemento);
		System.out.println("Elemento aggiunto con successo: " + nuovoElemento.getTitolo());
	}

	public List<Elemento> getElementi() {
		return elementi;
	}

	//metodo di ricerca per Isbn
	public Elemento ricercaPerIsbn(long codiceIsbn) throws ElementoNonTrovatoException {
		for (Elemento elemento : elementi) {
			if (elemento.getCodiceIsbn() == codiceIsbn) {
				return elemento;
			}
		}
		throw new ElementoNonTrovatoException("Nessun elemento trovato con il codice ISBN: " + codiceIsbn);
	}

	//metodo di ricerca per anno
	public List<Elemento> ricercaPerAnno(int anno) throws ElementoNonTrovatoException {
		List<Elemento> risultati = new ArrayList<>();

		for (Elemento elemento : elementi) {
			if (elemento.getAnno() == anno) {
				risultati.add(elemento);
			}
		}
		if (risultati.isEmpty()) {
			throw new ElementoNonTrovatoException("Nessun elemento trovato con l'anno: " + anno);
		}

		return risultati;
	}

	//metodo di ricerca per autore
	public List<Elemento> ricercaPerAutore(String autore) throws ElementoNonTrovatoException {
		List<Elemento> risultati = new ArrayList<>();

		for (Elemento elemento : elementi) {
			if (elemento instanceof Libro) {
				Libro libro = (Libro) elemento;
				if (libro.getAutore().equalsIgnoreCase(autore)) {
					risultati.add(libro);
				}
				if (risultati.isEmpty()) {
					throw new ElementoNonTrovatoException("Nessun elemento trovato con l'autore: " + autore);
				}
			}
		}
		return risultati;
	}


}