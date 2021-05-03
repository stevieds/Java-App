package model;

public class Personale {
    private String fname;
    private String lname;
    private String recapito;



    private String staffId;

    public Personale(String fname, String lname, String recapito) {
        this.fname=fname;
        this.lname=lname;
        this.recapito=recapito;
    }

    public String getFName () {
        return this.fname;
    }
    public String getLName () {
        return this.lname;
    }
    public String getFullName () {return this.fname + " " + this.lname;}
    public String getRecapito () {
        return this.recapito;
    }

    @Override
    public String toString() {
        return this.fname+" "+this.lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}


