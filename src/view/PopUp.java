package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PopUp extends JDialog implements ActionListener {
    public JPanel data;
    public JPanel buttons;
    public JButton ok;
    public JButton ko;

    public PopUp () {
        this.setSize(512, 288);
        this.setLayout(new BorderLayout());

        this.data = new JPanel();

        this.add(this.data, BorderLayout.NORTH);

        this.buttons = new JPanel();
        this.buttons.setLayout(new FlowLayout());

        this.ok = new JButton("");
        ok.addActionListener(this);
        this.buttons.add(this.ok);

        this.ko = new JButton("");
        ko.addActionListener(this);
        this.buttons.add(this.ko);

        this.add(this.buttons, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {}
}
