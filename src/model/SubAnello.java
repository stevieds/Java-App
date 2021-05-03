package model;

import java.util.HashMap;

public class SubAnello extends Anello {
    private HashMap <Personaggio, Doppiatore> coppie;
    private Status status;

    public SubAnello(MyTime start, MyTime end) {
        super(start, end);
        this.coppie = new HashMap<Personaggio, Doppiatore>();
        this.status=Status.NEW;
    }

    public SubAnello(String stringStart, String stringEnd) {
        super(stringStart, stringEnd);
        this.coppie = new HashMap<Personaggio, Doppiatore>();
        status=Status.NEW;
    }


    public void addCoppia (Personaggio pers, Doppiatore dopp) {
        this.coppie.put(pers, dopp);
    }










}
