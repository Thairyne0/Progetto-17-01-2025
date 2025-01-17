package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private final Map<String, ElementoCatalogo> catalogo = new HashMap<>();

    // Aggiungi un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) throws IllegalArgumentException {
        if (catalogo.containsKey(elemento.getIsbn())) {
            throw new IllegalArgumentException("Elemento con ISBN già presente: " + elemento.getIsbn());
        }
        catalogo.put(elemento.getIsbn(), elemento);
    }

    // Ricerca per ISBN con eccezione
    public ElementoCatalogo cercaPerIsbn(String isbn) throws ElementoNonTrovatoEx {
        return Optional.ofNullable(catalogo.get(isbn))
                .orElseThrow(() -> new ElementoNonTrovatoEx("Elemento con ISBN " + isbn + " non trovato."));
    }

    // Rimozione elemento in base all'ISBN
    public void rimuoviElemento(String isbn) {
        catalogo.remove(isbn);
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore
    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof org.example.Libro)
                .map(e -> (org.example.Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiorna un elemento in base all'ISBN
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws ElementoNonTrovatoEx {
        if (!catalogo.containsKey(isbn)) {
            throw new ElementoNonTrovatoEx("Elemento con ISBN " + isbn + " non trovato per l'aggiornamento.");
        }

        // Recupera l'elemento esistente
        ElementoCatalogo elementoEsistente = catalogo.get(isbn);

        // Aggiorna i campi comuni
        elementoEsistente.setTitolo(nuovoElemento.getTitolo());
        elementoEsistente.setAnnoPubblicazione(nuovoElemento.getAnnoPubblicazione());
        elementoEsistente.setNumeroPagine(nuovoElemento.getNumeroPagine());

        // Controlla se è un Libro o una Rivista per aggiornare i campi specifici
        if (elementoEsistente instanceof Libro && nuovoElemento instanceof Libro) {
            Libro libroEsistente = (Libro) elementoEsistente;
            Libro nuovoLibro = (Libro) nuovoElemento;

            libroEsistente.setAutore(nuovoLibro.getAutore());
            libroEsistente.setGenere(nuovoLibro.getGenere());
        } else if (elementoEsistente instanceof Rivista && nuovoElemento instanceof Rivista) {
            Rivista rivistaEsistente = (Rivista) elementoEsistente;
            Rivista nuovaRivista = (Rivista) nuovoElemento;

            rivistaEsistente.setPeriodicita(nuovaRivista.getPeriodicita());
        }

    }

    // Statistiche del catalogo
    public void statisticheCatalogo() {
        long totaleLibri = catalogo.values().stream()
                .filter(e -> e instanceof org.example.Libro)
                .count();

        long totaleRiviste = catalogo.values().stream()
                .filter(e -> e instanceof org.example.Rivista)
                .count();

        Optional<ElementoCatalogo> elementoConPiuPagine = catalogo.values().stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine));

        double mediaPagine = catalogo.values().stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.println("Statistiche del catalogo:");
        System.out.println("Totale libri: " + totaleLibri);
        System.out.println("Totale riviste: " + totaleRiviste);
        elementoConPiuPagine.ifPresent(e -> System.out.println("Elemento con più pagine: " + e));
        System.out.println("Media delle pagine: " + mediaPagine);
    }
}
