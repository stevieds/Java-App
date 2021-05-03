package model;

import java.util.ArrayList;

public class Film implements Methods {

    private String iTitle;
    private String oTitle;
    private MyTime length;
    private ArrayList<Anello> anelli;
    private ArrayList<Coppia> persDopp;
    private Status status;
    private String filmId;


    //Costruttore
    public Film(String oTitle, String iTitle, MyTime length) {
        this.oTitle = oTitle;
        this.iTitle = iTitle;
        this.length = length;
        this.persDopp = new ArrayList<Coppia>();
        this.anelli = new ArrayList<Anello>();
        this.status = Status.NEW;
    }

    public Film(String oTitle, String iTitle, MyTime length, Status status) {
        this.oTitle = oTitle;
        this.iTitle = iTitle;
        this.length = length;
        this.persDopp = new ArrayList<Coppia>();
        this.anelli = new ArrayList<Anello>();
        this.status = status;
    }

    // Retrieve Italian title
    public String getiTitle() {
        return iTitle;
    }

    // Change Italian title
    public void setiTitle(String iTitle) {
        this.iTitle = iTitle;
    }

    // Retrieve original title
    public String getoTitle() {
        return oTitle;
    }

    // Change italian title
    public void setoTitle(String oTitle) {
        this.oTitle = oTitle;
    }

    // Get length in milliseconds
    public long lengthInMilli() {
        return this.length.milliSec();
    }

    // Get length in String
    public String lengthToString() {
        return length.toString();
    }

    // Get Status
    public String getStatus() {
        return this.status.name();
    }

    // Change length
    public void setLength(String newLength) {
        this.length.setMyTime(newLength);
    }

    // Add anello with Obj
    public void addAnello(Anello anello) {
        if (this.lengthInMilli() >= (this.durataTotAnelli(this.anelli) + anello.lengthInMilli())) {
            this.anelli.add(anello);
            long difference = this.lengthInMilli() - this.durataTotAnelli(this.anelli);
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("L'anello è stato aggiunto correttamente. La durata rimanente è di: " + n.toString());
        } else {
            long difference = (this.durataTotAnelli(this.anelli) + anello.lengthInMilli()) - this.lengthInMilli();
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("Errore. L'anello eccede la durata totale del film di " + n.toString());
        }
    }

    // Add model.Anello with model.MyTime
    public void addAnello(MyTime start, MyTime end) {
        if (this.lengthInMilli() >= (this.durataTotAnelli(this.anelli) + (end.milliSec() - start.milliSec()))) {
            this.anelli.add(new Anello(start, end));
            long difference = this.lengthInMilli() - this.durataTotAnelli(this.anelli);
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("L'anello è stato aggiunto correttamente. La durata rimanente è di: " + n.toString());
        } else {
            long difference = ((this.durataTotAnelli(this.anelli) + (end.milliSec() - start.milliSec()) - this.lengthInMilli()));
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("Errore. L'anello eccede la durata totale del film di " + n.toString());
        }
    }


    // Add model.Anello with Strings
    public void addAnello(String start, String end) {
        MyTime s = new MyTime(start);
        MyTime e = new MyTime(end);

        if (this.lengthInMilli() >= (this.durataTotAnelli(this.anelli) + (e.milliSec() - s.milliSec()))) {
            this.anelli.add(new Anello(start, end));
            long difference = this.lengthInMilli() - this.durataTotAnelli(this.anelli);
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("L'anello è stato aggiunto correttamente. La durata rimanente è di: " + n.toString());
        } else {
            long difference = ((this.durataTotAnelli(this.anelli) + (e.milliSec() - s.milliSec()) - this.lengthInMilli()));
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("Errore. L'anello eccede la durata totale del film di " + n.toString());
        }
    }


    // Retrieve list of abbinamenti
    public ArrayList<Coppia> getCoppie() {
        return this.persDopp;
    }


    // Retrieve list of anelli
    public ArrayList<Anello> getAnelli() {
        return this.anelli;
        /*
        String answer = "";
        int i = 0;
        for (Anello a : anelli) {
            answer += i + ":\n" + a.toString();
            i++;
        }
        System.out.println(answer);

         */
    }

    // Add new character
    public void setPersonaggio(String nomePers) {
        Personaggio pers = new Personaggio(nomePers);
        persDopp.add(new Coppia(pers));
    }

    public void setPersonaggio(Coppia coppia) {
        persDopp.add(coppia);
    }

    public boolean checkPersonaggio(String nomePers) {
        Personaggio pers = new Personaggio(nomePers);
        boolean ret = false;
        for (Coppia coppia : this.persDopp) {
            if (coppia.getPers().getName().equals(nomePers)) {
                ret = true;
                break;
            } else if (!coppia.getPers().equals(nomePers)) {
                ret = false;
            }
        }
        return ret;
    }

    // Add doppiatore to personaggio
    public void setDoppiatore(Doppiatore dopp, Personaggio pers) {
        for (Coppia coppia : this.persDopp) {
            if (pers.getName().equals(coppia.getName())) {
                coppia.setDopp(dopp);
            }
        }
    }

    /*
    // Replace a doppiatore with a new one
    public void replaceInCoppia (Doppiatore dopp) {
        for (Coppia coppia : this.persDopp) {
            if (dopp.getName().equals(coppia.getName(dopp))) {
                coppia.setDopp(dopp);
            }
        }
    }

     */

    // Amend character
    public void replaceInCoppia(Personaggio pers) {
        for (Coppia coppia : this.persDopp) {
            if (pers.getName().equals(coppia.getName())) {
                coppia.setPers(pers);
            }
        }
    }

    // Add couple
    public void addCouple(Coppia couple) {
        this.persDopp.add(couple);
    }


    // Check the status
    public String checkCompleted() {
        String answer = "";
        if (this.status == Status.NEW) {
            answer = "model.Film da doppiare";
        } else if (this.status == Status.Wip) {
            answer = "Il film è in lavorazione";
        } else if (this.status == Status.Done) {
            answer = "Il doppiaggio del film è stato terminato";
        }
        return answer;
    }

    // Change the status
    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }


    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String id) {
        this.filmId = id;
    }

    /*
    // Check if a character exists in model.Film
    public boolean checkPers (String pers) {
        boolean answer = false;
        for (model.Coppia p : this.personaggi) {
            if (p.getName().equals(pers)) {
                answer = true;
            }
        }
        return answer;
    }

    // Add a new couple to a subRing
    public String addCoupleToSub (model.Coppia pers, model.Doppiatore dopp, model.SubAnello sub) {
        if (this.checkPers(pers.getName())==true) {
            sub.addCoppia(pers, dopp);
            return "OK";
        } else {
            return "NoooooO";
        }
    }

     */

    @Override
    public String toString() {
        return
                "Titolo italiano: " + iTitle + '\n' +
                        "Titolo originale: '" + oTitle + '\n' +
                        "Durata: " + length + '\n' +
                        "Anelli: " + anelli + '\n' +
                        "Abbinamenti: " + persDopp + '\n' +
                        "Stato: " + this.checkCompleted();
    }
}


