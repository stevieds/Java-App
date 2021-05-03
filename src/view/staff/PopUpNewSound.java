package view.staff;

import Controller.StaffController;
import db.DbSound;
import model.Personale;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpNewSound extends PopUp {
    JTextField jFname;
    JTextField jLname;
    JTextField jContact;
    JLabel lFname;
    JLabel lLname;
    JLabel lContact;


    public PopUpNewSound() {
        super();
        this.setTitle("Aggiungi nuovo fonico");



        this.data.setLayout(new GridLayout(3, 2));

        this.jFname = new JTextField(50);
        this.data.add(this.jFname);
        this.lFname = new JLabel("Nome");
        this.data.add(this.lFname);

        this.jLname = new JTextField(50);
        this.data.add(this.jLname);
        this.lLname = new JLabel("Cognome");
        this.data.add(this.lLname);

        this.jContact = new JTextField(50);
        this.data.add(this.jContact);
        this.lContact = new JLabel("Recapito");
        this.data.add(this.lContact);

        this.ok.setText("Invia");
        this.ko.setText("Reset");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean result;
        String command = e.getActionCommand();
        if (command.equals("Invia")) {
            String fname = this.jFname.getText();
            String lname = this.jLname.getText();
            String contact = this.jContact.getText();


            Personale sound = new Personale(fname, lname, contact);
            result = StaffController.insert(new Personale(fname, lname, contact), "sound");

            if (result) {
                PopUpSuccess popUpSuccess = new PopUpSuccess();
                popUpSuccess.setVisible(true);
                this.jFname.setText("");
                this.jLname.setText("");
                this.jContact.setText("");
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (command.equals("Reset")) {
            this.jFname.setText("");
            this.jLname.setText("");
            this.jContact.setText("");


        }
    }
}
