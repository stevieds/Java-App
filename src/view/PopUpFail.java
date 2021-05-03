package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpFail extends JFrame implements ActionListener {
    JButton btn;
    JLabel text;

    public PopUpFail() {
        this.setSize(512, 288);
        this.setTitle("Errore");
        this.setLayout(new GridLayout(2, 1));
        text = new JLabel("Attenzione! Si è verificato un errore");
        this.add(text);
        this.btn=new JButton("OK");
        this.btn.addActionListener(this);
        this.add(this.btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("OK")) {
            this.dispose();
        }

    }
}
