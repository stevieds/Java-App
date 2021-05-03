package view.film;

import db.DbFilm;
import model.Film;
import model.MyTime;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpNewFilm extends PopUp {
    JTextField jITitle;
    JTextField jOTitle;

    JPanel jLength;
    JSpinner hh;
    JSpinner mm;
    JSpinner ss;
    JSpinner sss;


    JLabel lITitle;
    JLabel lOTitle;
    JLabel lLength;

    public PopUpNewFilm () {
        this.setTitle("Aggiungi film");
        this.data.setLayout(new GridLayout(4, 2));
        lITitle = new JLabel("Titolo italiano");
        this.data.add(this.lITitle);
        jITitle = new JTextField();
        this.data.add(jITitle);
        lOTitle = new JLabel("Titolo originale");
        this.data.add(this.lOTitle);
        jOTitle = new JTextField();
        this.data.add(jOTitle);
        lLength = new JLabel("Durata");
        this.data.add(lLength);
        jLength = new JPanel();
        jLength.setLayout(new FlowLayout());

        this.hh = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        this.jLength.add(this.hh);
        this.mm = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jLength.add(this.mm);
        this.ss = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jLength.add(this.ss);
        this.sss = new JSpinner(new SpinnerNumberModel(000, 000, 999, 001));
        this.jLength.add(this.sss);




        this.data.add(jLength);

        this.ok.setText("Invia");
        this.ko.setText("Reset");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean result;
        String command = e.getActionCommand();
        if (command.equals("Invia")) {
            String iTitle = this.jITitle.getText();
            String oTitle = this.jOTitle.getText();
            MyTime length = new MyTime(
                    this.hh.getValue().toString() + ":" +
                            this.mm.getValue().toString() + ":" +
                            this.ss.getValue().toString() + "." +
                            this.sss.getValue().toString()
            );
            Film film = new Film(oTitle, iTitle, length);
            result = DbFilm.insert(film);
            if (result) {
                PopUpSuccess popUpSuccess = new PopUpSuccess();
                popUpSuccess.setVisible(true);
                this.jITitle.setText("");
                this.jOTitle.setText("");
                this.hh.setValue(0);
                this.mm.setValue(0);
                this.ss.setValue(0);
                this.sss.setValue(0);
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (command.equals("Reset")) {
            this.jITitle.setText("");
            this.jOTitle.setText("");
            this.hh.setValue(0);
            this.mm.setValue(0);
            this.ss.setValue(0);
            this.sss.setValue(0);

        }
    }




}
