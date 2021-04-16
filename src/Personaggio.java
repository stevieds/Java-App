public class Personaggio {
    private String name;

    public Personaggio (String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
