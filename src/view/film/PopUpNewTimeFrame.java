package view.film;

import Controller.AnelloController;
import db.DbAnello;
import db.DbFilm;
import model.Anello;
import model.Film;
import model.MyTime;
import model.Status;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpNewTimeFrame extends PopUp {
    JTextField jITitle;
    JTextField jOTitle;

    JPanel jStartPanel;
    JPanel jEndPanel;
    JSpinner hhS;
    JSpinner mmS;
    JSpinner ssS;
    JSpinner sssS;

    JSpinner hhE;
    JSpinner mmE;
    JSpinner ssE;
    JSpinner sssE;


    JLabel lITitle;
    JLabel lOTitle;
    JLabel lLength;

    Film film;

    public PopUpNewTimeFrame(Film film) {
        this.film = film;
        this.setTitle("Aggiungi anello");

        this.data.setLayout(new GridLayout(2, 2));

        jStartPanel = new JPanel();
        jStartPanel.setLayout(new FlowLayout());

        this.hhS = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        this.jStartPanel.add(this.hhS);
        this.mmS = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jStartPanel.add(this.mmS);
        this.ssS = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jStartPanel.add(this.ssS);
        this.sssS = new JSpinner(new SpinnerNumberModel(000, 000, 999, 001));
        this.jStartPanel.add(this.sssS);

        this.data.add(new JLabel("Inizio"));
        this.data.add(jStartPanel);

        jEndPanel = new JPanel();
        jEndPanel.setLayout(new FlowLayout());

        this.hhE = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        this.jEndPanel.add(this.hhE);
        this.mmE = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jEndPanel.add(this.mmE);
        this.ssE = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        this.jEndPanel.add(this.ssE);
        this.sssE = new JSpinner(new SpinnerNumberModel(000, 000, 999, 001));
        this.jEndPanel.add(this.sssE);

        this.data.add(new JLabel("Fine"));
        this.data.add(jEndPanel);



        this.ok.setText("Invia");
        this.ko.setText("Reset");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean result;
        String commSand = e.getActionCommand();
        if (commSand.equals("Invia")) {
            MyTime start = new MyTime(
                    this.hhS.getValue().toString() + ":" +
                            this.mmS.getValue().toString() + ":" +
                            this.ssS.getValue().toString() + "." +
                            this.sssS.getValue().toString()
            );
            MyTime end = new MyTime(
                    this.hhE.getValue().toString() + ":" +
                            this.mmE.getValue().toString() + ":" +
                            this.ssE.getValue().toString() + "." +
                            this.sssE.getValue().toString()
            );

            result = AnelloController.addAnello(this.film, new Anello(start, end));

            if (result) {
                PopUpSuccess popUpSuccessS = new PopUpSuccess();
                popUpSuccessS.setVisible(true);
                this.hhS.setValue(0);
                this.mmS.setValue(0);
                this.ssS.setValue(0);
                this.sssS.setValue(0);
                this.hhE.setValue(0);
                this.mmE.setValue(0);
                this.ssE.setValue(0);
                this.sssE.setValue(0);
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (commSand.equals("Reset")) {
            this.hhS.setValue(0);
            this.mmS.setValue(0);
            this.ssS.setValue(0);
            this.sssS.setValue(0);
            this.hhE.setValue(0);
            this.mmE.setValue(0);
            this.ssE.setValue(0);
            this.sssE.setValue(0);

        }
    }




}
