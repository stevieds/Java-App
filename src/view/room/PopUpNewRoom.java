package view.room;

import Controller.RoomController;
import db.DbRoom;
import model.Room;
import view.PopUp;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpNewRoom extends PopUp implements ActionListener {
    JTextField jName;
    JLabel lName;

    public PopUpNewRoom() {
        super();
        this.setTitle("Aggiungi nuova sala");

        this.data.setLayout(new GridLayout(1, 2));

        this.lName = new JLabel("Nome");
        this.data.add(this.lName);
        this.jName = new JTextField(50);
        this.data.add(this.jName);

        this.ok.setText("Invia");
        this.ko.setText("Reset");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean result;
        String command = e.getActionCommand();
        if (command.equals("Invia")) {

            result = RoomController.addRoom(new Room (this.jName.getText()));

            if (result) {
                PopUpSuccess popUpSuccess = new PopUpSuccess();
                popUpSuccess.setVisible(true);
                this.jName.setText("");
            } else if (!result) {
                PopUpFail popUpFail = new PopUpFail();
                popUpFail.setVisible(true);
            }
        }
        else if (command.equals("Reset")) {
            this.jName.setText("");
        }
    }
}
