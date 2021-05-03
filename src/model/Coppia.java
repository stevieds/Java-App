package model;

public class Coppia {
    private Doppiatore dopp;
    private Personaggio pers;
    private String coupleId;

    public Coppia (Personaggio pers, String coupleId) {
        this.pers=pers;
        this.coupleId=coupleId;
    }

    public Coppia (Personaggio pers) {
        this.pers=pers;
        this.dopp=null;
    }

    public Coppia (Doppiatore dopp, Personaggio pers) {
        this.dopp=dopp;
        this.pers=pers;
    }

    public Coppia (Doppiatore dopp, Personaggio pers, String coupleId) {
        this.dopp=dopp;
        this.pers=pers;
        this.coupleId=coupleId;
    }

    public void setDopp (Doppiatore dopp) {
        this.dopp=dopp;
    }

    public void setPers (Personaggio pers) {
        this.pers=pers;
    }

    public String getName () {
        return this.pers.getName();
    }

    public String getPersName () {
        return this.pers.getName();
    }

    public Personaggio getPers () {
        return this.pers;
    };



/*
    public String getName (Doppiatore dopp) {
        return this.dopp.getName();
    }
 */
    public Doppiatore getDopp () {
        return this.dopp;
    }

    public String getPersId () {
        return this.getPers().getId();
    }


    public String getCoupleId() {
        return coupleId;
    }

    public void setCoupleId(String coupleId) {
        this.coupleId = coupleId;
    }

    @Override
    public String toString() {
        return this.pers.toString();
    }
}
