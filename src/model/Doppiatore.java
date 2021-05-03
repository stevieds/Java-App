package model;

public class Doppiatore extends Personale{
    private Genere genere;

    public Doppiatore(String fname, String lname, String recapito, Genere genere) {
        super (fname, lname, recapito);
        this.genere = genere;
    }


    public Genere getGenere() {
        return this.genere;
    }
}
