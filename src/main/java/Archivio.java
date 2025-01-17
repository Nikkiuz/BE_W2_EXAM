import java.util.ArrayList;
import java.util.Comparator;
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
		List<Elemento> elementiTrovati = elementi.stream()
			.filter(elemento -> elemento.getAnno() == anno)
			.toList();

		if (elementiTrovati.isEmpty()) {
			throw new ElementoNonTrovatoException("Nessun elemento trovato per l'anno: " + anno);
		}

		return elementiTrovati;
	}


	//metodo di ricerca per autore
	public List<Libro> ricercaPerAutore(String autore) throws ElementoNonTrovatoException {
		List<Libro> libriTrovati = elementi.stream()
			.filter(elemento -> elemento instanceof Libro)
			.map(elemento -> (Libro) elemento)
			.filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
			.toList();

		if (libriTrovati.isEmpty()) {
			throw new ElementoNonTrovatoException("Nessun libro trovato per l'autore: " + autore);
		}

		return libriTrovati;
	}

	//rimozione di un elemento dato un codice isbn
	public void rimuoviElemento(long codiceIsbn) throws ElementoNonTrovatoException{
		boolean found = false;

		for (int i=0; i<elementi.size();i++) {
			if(elementi.get(i).getCodiceIsbn() == codiceIsbn){
				elementi.remove(i);
				found = true;
				System.out.println("Elemento rimosso dall'elenco.");
				break;
			}
			if(!found){
				throw new ElementoNonTrovatoException("Nessun elemento trovato con il codice ISBN: " + codiceIsbn);
			}
		}
	}

	//modifica di un elemento dato un codice isbn
	public void aggiornaElemento(long codiceIsbn, Elemento nuovoElemento) throws ElementoNonTrovatoException {
		boolean aggiornato = false;

		for (int i = 0; i < elementi.size(); i++) {
			if (elementi.get(i).getCodiceIsbn() == codiceIsbn) {
				Elemento elementoEsistente = elementi.get(i);
				elementoEsistente.setTitolo(nuovoElemento.getTitolo());
				elementoEsistente.setAnno(nuovoElemento.getAnno());
				elementoEsistente.setNumeroPagine(nuovoElemento.getNumeroPagine());

				if (elementoEsistente instanceof Libro) {
					Libro libroEsistente = (Libro) elementoEsistente;
					libroEsistente.setAutore(((Libro) nuovoElemento).getAutore());
					libroEsistente.setGenere(((Libro) nuovoElemento).getGenere());
				}

				if (elementoEsistente instanceof Rivista) {
					Rivista rivistaEsistente = (Rivista) elementoEsistente;
					rivistaEsistente.setPeriodicita(((Rivista) nuovoElemento).getPeriodicita());
				}

				aggiornato = true;
				System.out.println("Elemento aggiornato con successo.");
				break;
			}
		}

		if (!aggiornato) {
			throw new ElementoNonTrovatoException("Nessun elemento trovato con il codice ISBN: " + codiceIsbn);
		}
	}

	//metodi per statistiche
	public void stampaStatistiche() {
		long numeroLibri = elementi.stream()
			.filter(elemento -> elemento instanceof Libro)
			.count();

		long numeroRiviste = elementi.stream()
			.filter(elemento -> !(elemento instanceof Libro))
			.count();

		Elemento elementoMaxPagine = elementi.stream()
			.max(Comparator.comparingInt(Elemento::getNumeroPagine))
			.orElse(null);

		double mediaPagine = elementi.stream()
			.mapToInt(Elemento::getNumeroPagine)
			.average()
			.orElse(0);

		System.out.println("Statistiche del catalogo:");
		System.out.println("Numero totale di libri: " + numeroLibri);
		System.out.println("Numero totale di riviste: " + numeroRiviste);
		System.out.println("Elemento con il maggior numero di pagine: "
			+ (elementoMaxPagine != null ? elementoMaxPagine.getTitolo() : "Nessuno"));
		System.out.println("Media delle pagine di tutti gli elementi: " + mediaPagine);
	}
}