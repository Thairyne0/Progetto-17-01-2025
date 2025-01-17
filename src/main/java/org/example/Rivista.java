package org.example;

public class Rivista extends ElementoCatalogo {
    public enum Periodicita {
        SETTIMANALE, MENSILE, SEMESTRALE
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + getPeriodicita();
    }
}
