import java.util.ArrayList;

public class Anello {
    private MyTime start;
    private MyTime end;
    private ArrayList<SubAnello> subAnelli;
    private Status status;

    // Costruttore con MyTime
    public Anello(MyTime start, MyTime end) {
        this.start = start;
        this.end = end;
        this.status = Status.New;
    }

    // Costruttore con String
    public Anello(String stringStart, String stringEnd) {
        this.start = new MyTime(stringStart);
        this.end = new MyTime(stringEnd);
        this.status = Status.New;
    }

    // Restituisce durata in MyTime
    public MyTime length () {
        long milli = this.end.milliSec() - this.start.milliSec();
        return MyTime.milliConvert(milli);
    }

    // Restituisce la durata in millisecondi
    public long lengthInMilli () {
        long milli = this.end.milliSec() - this.start.milliSec();
        return milli;
    }

    // Check the total length of all of the subAnelli
    public long durataTotSubAnelli () {
        long durata = 0;
        for (SubAnello sub : subAnelli) {
            durata+= sub.lengthInMilli();
        }
        return durata;
    }


    // Add SubAnello with Strings
    public void addSubAnello(String start, String end) {
        MyTime s = new MyTime(start);
        MyTime e = new MyTime(end);

        if (this.lengthInMilli()>=(this.durataTotSubAnelli()+ (e.milliSec() - s.milliSec()))) {
            this.subAnelli.add(new SubAnello (start, end));
            long difference = this.lengthInMilli() - this.durataTotSubAnelli();
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("L'anello è stato aggiunto correttamente. La durata rimanente è di: "+n.toString());
        }
        else {
            long difference = ((this.durataTotSubAnelli()+ (e.milliSec() - s.milliSec()) - this.lengthInMilli()));
            MyTime n = MyTime.milliConvert(difference);
            System.out.println("Errore. L'anello eccede la durata totale del film di "+n.toString());
        }
    }





    @Override
    public String toString() {
        String compl="";
        if (this.status==Status.Wip || this.status == Status.New) {
            compl = "No";
        } else if (this.status==Status.Done) {
            compl = "Si";
        }
        return
                "Inizio: " + start + "\n" +
                "Fine: " + end +  "\n" +
                ", subAnelli: " + subAnelli + "\n"+
                "Completato: " + compl +
                "\n";
    }
}

