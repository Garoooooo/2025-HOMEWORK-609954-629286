package it.uniroma3.diadia.ambienti;

public enum Direzione {
    NORD, SUD, EST, OVEST;

    public static Direzione fromString(String s) {
        return Direzione.valueOf(s.trim().toUpperCase());
    }
}
