package view.film;

import db.DbCastCouple;
import model.Coppia;
import model.Film;
import model.Personaggio;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpNewChar extends PopUp {
    JTextField nameField;
    JLabel nameLabel;
    Film film;

    public PopUpNewChar(Film film) {
        this.film = film;
        this.setTitle("Aggiungi personaggio");

        this.data.setLayout(new FlowLayout());
        this.nameField = new JTextField(25);
        this.nameLabel = new JLabel("Nome");
        this.data.add(nameLabel);
        this.data.add(nameField);

        this.ok.setText("Invia");
        this.ko.setText("Reset");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean result;
        String commSand = e.getActionCommand();
        if (commSand.equals("Invia")) {
            if (this.film.checkPersonaggio(this.nameField.getText())) {
                result = false;
            }
            else {
                Coppia couple = new Coppia(new Personaggio(this.nameField.getText()));
                this.film.addCouple(couple);
                result = DbCastCouple.addCast(this.film, couple);
            }


            if (result) {
                PopUpSuccess popUpSuccessS = new PopUpSuccess();
                popUpSuccessS.setVisible(true);
                this.nameField.setText("");
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (commSand.equals("Reset")) {
            this.nameField.setText("");



        }
    }




}
