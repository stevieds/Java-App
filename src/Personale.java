public abstract class Personale {
    private String name;
    private String recapito;

    public Personale(String name) {
        this.name=name;
    }

    public String getName () {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}


