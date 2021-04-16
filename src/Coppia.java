public class Coppia {
    private Doppiatore dopp;
    private Personaggio pers;

    public Coppia (Doppiatore dopp) {
        this.dopp=dopp;
        this.pers=null;
    }

    public Coppia (Personaggio pers) {
        this.pers=pers;
        this.dopp=null;
    }

    public Coppia (Doppiatore dopp, Personaggio pers) {
        this.dopp=dopp;
        this.pers=pers;
    }

    public void setDopp (Doppiatore dopp) {
        this.dopp=dopp;
    }

    public void setPers (Personaggio pers) {
        this.pers=pers;
    }

    public String getName (Personaggio pers) {
        return this.pers.getName();
    }

    public String getPers () {
        return this.pers.getName();
    }

    public String getName (Doppiatore dopp) {
        return this.dopp.getName();
    }

    public String getDopp () {
        if (this.dopp == null) {
            return "da assegnare";
        } else {
            return this.dopp.getName();
        }
    }

    @Override
    public String toString() {
        return this.pers.toString();
    }
}
