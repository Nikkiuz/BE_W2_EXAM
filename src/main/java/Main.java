import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Archivio archivio = new Archivio();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nMenu operazioni:");
			System.out.println("1. Aggiungi elemento");
			System.out.println("2. Rimuovi elemento");
			System.out.println("3. Ricerca per ISBN");
			System.out.println("4. Ricerca per anno");
			System.out.println("5. Ricerca per autore");
			System.out.println("6. Aggiorna elemento");
			System.out.println("7. Mostra statistiche");
			System.out.println("0. Esci");
			System.out.print("Seleziona un'operazione: ");

			int scelta = scanner.nextInt();
			scanner.nextLine();

			try {
				switch (scelta) {
					case 1 -> {
						System.out.println("Seleziona il tipo di elemento da aggiungere:");
						System.out.println("1. Libro");
						System.out.println("2. Rivista");
						System.out.print("Scegli: ");
						int tipoElemento = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Inserisci il titolo: ");
						String titolo = scanner.nextLine();
						System.out.print("Inserisci l'anno di pubblicazione: ");
						int anno = scanner.nextInt();
						System.out.print("Inserisci il numero di pagine: ");
						int numeroPagine = scanner.nextInt();
						System.out.print("Inserisci il codice ISBN: ");
						long codiceIsbn = scanner.nextLong();
						scanner.nextLine();

						if (tipoElemento == 1) {
							System.out.print("Inserisci l'autore: ");
							String autore = scanner.nextLine();
							System.out.print("Inserisci il genere: ");
							String genere = scanner.nextLine();

							Libro libro = new Libro(codiceIsbn, titolo, anno, numeroPagine, autore, genere);
							archivio.aggiungiElemento(libro);
							System.out.println("Libro aggiunto con successo!");
						} else if (tipoElemento == 2) {
							System.out.print("Inserisci la periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
							String periodicita = scanner.nextLine().toUpperCase();

							try {
								Periodicita periodicitaEnum = Periodicita.valueOf(periodicita);
								Rivista rivista = new Rivista(codiceIsbn, titolo, anno, numeroPagine, periodicitaEnum);
								archivio.aggiungiElemento(rivista);
								System.out.println("Rivista aggiunta con successo!");
							} catch (IllegalArgumentException e) {
								System.out.println("Periodicità non valida.");
							}
						} else {
							System.out.println("Tipo di elemento non valido.");
						}
					}
					case 2 -> {
						System.out.print("Inserisci ISBN: ");
						long codiceIsbn = scanner.nextLong();
						archivio.rimuoviElemento(codiceIsbn);
					}
					case 3 -> {
						System.out.print("Inserisci ISBN: ");
						long codiceIsbn = scanner.nextLong();
						Elemento elemento = archivio.ricercaPerIsbn(codiceIsbn);
						System.out.println("Elemento trovato: " + elemento.getTitolo());
					}
					case 4 -> {
						System.out.print("Inserisci anno di pubblicazione: ");
						int anno = scanner.nextInt();
						scanner.nextLine();
						List<Elemento> risultati = archivio.ricercaPerAnno(anno);
						if (risultati.isEmpty()) {
							System.out.println("Nessun elemento trovato con l'anno " + anno);
						} else {
							System.out.println("Elementi trovati con l'anno " + anno + ":");
							for (Elemento elemento : risultati) {
								System.out.println(elemento.getTitolo());
							}
						}
					}
					case 5 -> {
						System.out.print("Inserisci autore: ");
						String autore = scanner.nextLine();
						List<Libro> risultati = archivio.ricercaPerAutore(autore);
						if (risultati.isEmpty()) {
							System.out.println("Nessun libro trovato con l'autore: " + autore);
						} else {
							System.out.println("Libri trovati per autore " + autore + ":");
							for (Libro libro : risultati) {
								System.out.println(libro.getTitolo());
							}
						}
					}
					case 6 -> {
						System.out.print("Inserisci ISBN dell'elemento da aggiornare: ");
						long codiceIsbn = scanner.nextLong();
						scanner.nextLine();
						Elemento elemento = archivio.ricercaPerIsbn(codiceIsbn);
						if (elemento == null) {
							System.out.println("Elemento con ISBN " + codiceIsbn + " non trovato.");
						} else {
							System.out.println("Elemento trovato: " + elemento.getTitolo());
							System.out.println("Cosa vuoi aggiornare?");
							System.out.println("1. Titolo");
							System.out.println("2. Anno");
							System.out.println("3. Numero di pagine");
							System.out.println("4. Autore (solo per libri)");
							System.out.println("5. Genere (solo per libri)");
							System.out.println("6. Periodicità (solo per riviste)");
							System.out.print("Seleziona l'attributo da aggiornare: ");
							int sceltaAggiornamento = scanner.nextInt();
							scanner.nextLine();

							switch (sceltaAggiornamento) {
								case 1 -> {
									System.out.print("Inserisci il nuovo titolo: ");
									String nuovoTitolo = scanner.nextLine();
									elemento.setTitolo(nuovoTitolo);
								}
								case 2 -> {
									System.out.print("Inserisci il nuovo anno: ");
									int nuovoAnno = scanner.nextInt();
									elemento.setAnno(nuovoAnno);
								}
								case 3 -> {
									System.out.print("Inserisci il nuovo numero di pagine: ");
									int nuovePagine = scanner.nextInt();
									elemento.setNumeroPagine(nuovePagine);
								}
								case 4 -> {
									if (elemento instanceof Libro) {
										System.out.print("Inserisci il nuovo autore: ");
										String nuovoAutore = scanner.nextLine();
										((Libro) elemento).setAutore(nuovoAutore);
									} else {
										System.out.println("L'elemento non è un libro.");
									}
								}
								case 5 -> {
									if (elemento instanceof Libro) {
										System.out.print("Inserisci il nuovo genere: ");
										String nuovoGenere = scanner.nextLine();
										((Libro) elemento).setGenere(nuovoGenere);
									} else {
										System.out.println("L'elemento non è un libro.");
									}
								}
								case 6 -> {
									if (elemento instanceof Rivista) {
										System.out.print("Inserisci la nuova periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
										String nuovaPeriodicita = scanner.nextLine().toUpperCase();
										try {
											Periodicita periodicitaEnum = Periodicita.valueOf(nuovaPeriodicita);
											((Rivista) elemento).setPeriodicita(periodicitaEnum);
										} catch (IllegalArgumentException e) {
											System.out.println("Periodicità non valida.");
										}
									} else {
										System.out.println("L'elemento non è una rivista.");
									}
								}
								default -> System.out.println("Scelta non valida.");
							}
							System.out.println("Elemento aggiornato con successo!");
						}
					}
					case 7 -> archivio.stampaStatistiche();
					case 0 -> {
						System.out.println("Uscita...");
						return;
					}
					default -> System.out.println("Scelta non valida.");
				}
			} catch (Exception e) {
				System.err.println("Errore: " + e.getMessage());
			}
		}
	}
}
