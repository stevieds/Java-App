package model;

public class Personaggio {
    private String name;
    private String id;

    public Personaggio (String name) {
        this.name=name;
    }

    public Personaggio (String name, String id)
    {
        this.name=name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
