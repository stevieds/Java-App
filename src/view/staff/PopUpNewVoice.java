package view.staff;

import Controller.StaffController;
import db.DbStaff;
import model.Doppiatore;
import model.Genere;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpNewVoice extends PopUp implements ActionListener {
    JTextField jFname;
    JTextField jLname;
    JTextField jContact;
    JComboBox jGender;
    JLabel lFname;
    JLabel lLname;
    JLabel lContact;
    JLabel lGender;
    private final String[] G = {"","M", "F"};




    public PopUpNewVoice() {
        super();
        this.setTitle("Aggiungi nuova voce");



        this.data.setLayout(new GridLayout(4, 2));

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

        this.jGender = new JComboBox(this.G);
        this.data.add(this.jGender);
        this.lGender = new JLabel("Genere");
        this.data.add(this.lGender);



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
            String gender = (String)this.jGender.getSelectedItem();

            Doppiatore dopp = new Doppiatore(fname, lname, contact, Genere.valueOf(gender));

            result = StaffController.insertVoice(dopp);

            if (result) {
                PopUpSuccess popUpSuccess = new PopUpSuccess();
                popUpSuccess.setVisible(true);
                this.jFname.setText("");
                this.jLname.setText("");
                this.jContact.setText("");
                this.jGender.setSelectedItem(null);
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (command.equals("Reset")) {
            this.jFname.setText("");
            this.jLname.setText("");
            this.jContact.setText("");
            this.jGender.setSelectedItem(null);

        }
    }
}
