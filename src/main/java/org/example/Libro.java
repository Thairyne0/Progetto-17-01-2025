package org.example;

public class Libro extends ElementoCatalogo{

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere){
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Autore: " + getAutore() + ", Genere: " + getGenere();
    }
}
