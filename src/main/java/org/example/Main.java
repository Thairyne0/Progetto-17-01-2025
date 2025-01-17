package org.example;
import java.util.Scanner;

import org.example.Libro;
import org.example.Rivista;
import org.example.Rivista.Periodicita;
import org.example.Archivio;
import org.example.ElementoNonTrovatoEx;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Catalogo Biblioteca ---");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Cerca elemento per ISBN");
            System.out.println("3. Rimuovi elemento per ISBN");
            System.out.println("4. Cerca per anno pubblicazione");
            System.out.println("5. Cerca per autore");
            System.out.println("6. Aggiorna elemento per ISBN");
            System.out.println("7. Mostra statistiche catalogo");
            System.out.println("8. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Pulisce il buffer dello scanner

            try {
                switch (scelta) {
                    case 1 -> {
                        System.out.println("Aggiungi elemento: Libro (1) o Rivista (2)?");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Inserisci titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Inserisci anno di pubblicazione: ");
                        int anno = scanner.nextInt();
                        System.out.print("Inserisci numero di pagine: ");
                        int pagine = scanner.nextInt();
                        scanner.nextLine();

                        if (tipo == 1) {
                            System.out.print("Inserisci autore: ");
                            String autore = scanner.nextLine();
                            System.out.print("Inserisci genere: ");
                            String genere = scanner.nextLine();
                            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                        } else if (tipo == 2) {
                            System.out.print("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                            String periodicita = scanner.nextLine();
                            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, Rivista.Periodicita.valueOf(periodicita.toUpperCase())));
                        }
                    }
                    case 2 -> {
                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Risultato: " + archivio.cercaPerIsbn(isbn));
                    }
                    case 3 -> {
                        System.out.print("Inserisci ISBN da rimuovere: ");
                        String isbn = scanner.nextLine();
                        archivio.rimuoviElemento(isbn);
                        System.out.println("Elemento rimosso.");
                    }
                    case 4 -> {
                        System.out.print("Inserisci anno di pubblicazione: ");
                        int anno = scanner.nextInt();
                        archivio.cercaPerAnno(anno).forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.print("Inserisci autore: ");
                        String autore = scanner.nextLine();
                        archivio.cercaPerAutore(autore).forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.print("Inserisci ISBN da aggiornare: ");
                        String isbn = scanner.nextLine();
                        // Simile alla creazione di un nuovo elemento (richiedi tutti i dati)
                        System.out.println("Inserire i nuovi dati dell'elemento.");
                    }
                    case 7 -> archivio.statisticheCatalogo();
                    case 8 -> {
                        System.out.println("Uscita dal programma.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Opzione non valida.");
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }
}